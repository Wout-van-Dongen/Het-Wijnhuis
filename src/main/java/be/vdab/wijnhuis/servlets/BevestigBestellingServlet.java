package be.vdab.wijnhuis.servlets;

import be.vdab.wijnhuis.entities.BestelBon;
import be.vdab.wijnhuis.services.BestelBonService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/wijnen/bevestiging.html")
public class BevestigBestellingServlet extends HttpServlet {

    //Services
    private final BestelBonService BBONSVC = new BestelBonService();

    //Fouten
    private ArrayList<String> fouten;

    //Views
    private final static String REDIRECT_CONFIRM = "/wijnen/bevestiging.html",
            FOUTEN = "/wijnen/winkelmandje.html",
            VIEW = "/WEB-INF/jsp/pages/bevestiging.jsp";
/*===================================================================================================================
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
====================================================================================================================*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long bonNr = Long.parseLong(getParameter(request, "bonNr"));
            setAttribute(request, "bonNr", bonNr);
        } catch (NumberFormatException numExc) {
            setAttribute(request, "bonNr", "NaN");
        }

        forwardRequest(request, response, VIEW);

    }
/*===================================================================================================================
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
====================================================================================================================*/
    //When the order has been confirmed
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Resetting fouten
        fouten = new ArrayList<>();

        //Parameters
        String naam,
                straat,
                huisnr,
                gemeente,
                postcode,
                leveringAsString;

        int leveringAsInteger;

        //Fouten
        String naamFout = null,
                straatFout = null,
                huisnrFout = null,
                gemeenteFout = null,
                postcodeFout = null;

        //Winkelmandje
        Map<Long, Integer> winkelmandje;

        //Fouten
        Boolean foutenAanwezig = false;

        //Reading in the attributes
        naam = getParameter(request, "naam");
        straat = getParameter(request, "straat");
        huisnr = getParameter(request, "huisnr");
        gemeente = getParameter(request, "gemeente");
        postcode = getParameter(request, "postcode");
        leveringAsString = getParameter(request, "levering");

        leveringAsInteger = 0;
        //Reading in the basket from the session

        try {
            winkelmandje = (Map<Long, Integer>) getSessionAttribute(request, "winkelmandje");
        } catch (ClassCastException exc) {
            winkelmandje = null;
        }

        //Validate the attributes
        naamFout = validateAttribute(request, naam, "Naam");
        straatFout = validateAttribute(request, straat, "Straat");
        huisnrFout = validateAttribute(request, huisnr, "Huisnr");
        gemeenteFout = validateAttribute(request, gemeente, "Gemeente");
        postcodeFout = validateAttribute(request, postcode, "Postcode");

        //Validates mandje
        if (winkelmandje == null) {
            fouten.add("U heeft geen producten besteld!");
        }

        //Checks if the levering has a valid value
        try {
            leveringAsInteger = Integer.parseInt(leveringAsString);
        } catch (NumberFormatException | NullPointerException exc) {
            fouten.add("Er is een fout opgetreden bij het verwerken van uw bestelling!");
        }

        //If there are no fouten
        if (fouten.isEmpty() && foutenAanwezig == false) {
            //Assamble the order ==============================================================================================
            BestelBon bon = new BestelBon(new Date(), naam, straat, huisnr, postcode, gemeente, leveringAsInteger);
            //write the order to the db
            BBONSVC.create(bon, winkelmandje);

            //Remove the basket
            request.getSession().removeAttribute("mandje");
            request.getSession().invalidate();

            //Get the bonNr ====================================================================================================
            Long bonNr = bon.getBonNr();

            //Redirect to the confirmation view
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_CONFIRM + "?bonNr=" + bonNr));

        } else {
            //If fouten did occure
            //If the array fouten is not empty add them as attribute

            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + FOUTEN + "?"
                    + (naamFout == null ? "naam=" + naam : "naamFout=" + naamFout)
                    + (straatFout == null ? "&straat=" + straat : "&straatFout=" + straatFout)
                    + (huisnrFout == null ? "&huisnr=" + huisnr : "&huisnrFout=" + huisnrFout)
                    + (postcodeFout == null ? "&postcode=" + postcode : "&postcodeFout=" + postcodeFout)
                    + (gemeenteFout == null ? "&gemeente=" + gemeente : "&gemeenteFout=" + gemeenteFout)
                    + (fouten == null ? "" : "&fouten=" + fouten)
            ));
        }

    }
/*===================================================================================================================
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
====================================================================================================================*/
    //Validates 
    private String validateAttribute(HttpServletRequest request, String attribute, String attributeName) {
        if (attribute == null || attribute.equals("")) {
            return String.format("%s moet ingevuld zijn!", attributeName);
        }
        return null;
    }

    //Sets the request Attribute
    private void setAttribute(HttpServletRequest request, String name, Object attribute) {
        request.setAttribute(name, attribute);
    }

    //Gets the request Attribute
    private Object getAttribute(HttpServletRequest request, String name) {
        return request.getAttribute(name);
    }

    //Gets the request Parameter
    private String getParameter(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    //Gets the session Attribute
    private Object getSessionAttribute(HttpServletRequest request, String name) {
        return request.getSession().getAttribute(name);
    }

    //Gets the session Attribute
    private void setSessionAttribute(HttpServletRequest request, String name, Object attribute) {
        request.getSession().setAttribute(name, attribute);
    }

    //Forwards the request
    private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        request.getRequestDispatcher(view).forward(request, response);
    }

}
