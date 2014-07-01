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
import javax.servlet.http.HttpSession;

@WebServlet("/wijnen/bevestiging.html")
public class BevestigBestellingServlet extends HttpServlet {

    //Services
    private final BestelBonService BBONSVC = new BestelBonService();

    //Fouten
    private ArrayList<String> fouten;
    private String attributeList = "";

    //Views
    private final static String REDIRECT_CONFIRM = "%s/WEB-INF/jsp/pages/bevestinging.jsp?bonNr=%s",
            VIEW = "/wijnen/bestellen.html",
            TEST = "/WEB-INF/jsp/pages/bevestiging.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

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

        //Winkelmandje
        Map<Long, Integer> mandje;

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
            mandje = (Map<Long, Integer>) getSessionAttribute(request, "mandje");
        } catch (ClassCastException exc) {
            mandje = null;
        }

        //Validate the attributes
        validateAttribute(request, naam, "Naam", foutenAanwezig);
        validateAttribute(request, straat, "Straat", foutenAanwezig);
        validateAttribute(request, huisnr, "Huisnr", foutenAanwezig);
        validateAttribute(request, gemeente, "Gemeente", foutenAanwezig);
        validateAttribute(request, postcode, "Postcode", foutenAanwezig);

        //Validates mandje
        if (mandje == null) {
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
            //Assamble the order
            BestelBon bon = new BestelBon(new Date(), naam, straat, huisnr, postcode, gemeente, leveringAsInteger);
            //write the order to the db
            BBONSVC.create(bon, mandje);

            //Remove the basket
            request.getSession().removeAttribute("mandje");
            request.getSession().invalidate();

            //Get the bonNr
            Long bonNr = bon.getBonNr();

            //Redirect to the confirmation view
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + TEST + "?bonNr=" + bonNr));
            //String.format(REDIRECT_CONFIRM, request.getContextPath(), bonNr

        } else {
            //If fouten did occure
            //If the array fouten is not empty add them as attribute
           
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + VIEW + "?" + attributeList +  "fouten="+fouten ));
        }

    }

    //Validates 
    private void validateAttribute(HttpServletRequest request, String attribute, String attributeName, Boolean foutenAanwezig) {
        if (attribute == null) {
            attributeList = String.format("%sFout", attributeName.toLowerCase()) + "=" + String.format("%s moet ingevuld zijn!", attributeName) + "&";
            foutenAanwezig = true;
        }
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
