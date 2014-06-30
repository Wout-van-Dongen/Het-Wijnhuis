package be.vdab.wijnhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/wijnen/bevestigen.html")
public class BevestigBestellingServlet extends HttpServlet {

    //Fouten
    private ArrayList<String> fouten;

    //Views
    private final static String REDIRECT = "";

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
                levering;

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
        levering = getParameter(request, "levering");

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
        if (!levering.equalsIgnoreCase("afhalen") && !levering.equalsIgnoreCase("opsturen")) {
            fouten.add("Er is een fout opgetreden bij het registreren van uw bestelling!");
        }

        //Assamble the order
        //write the order to the db
    }

    //Validates 
    private void validateAttribute(HttpServletRequest request, String attribute, String attributeName, Boolean foutenAanwezig) {
        if (attribute == null) {
            setAttribute(request, String.format("%sFout", attributeName.toLowerCase()), String.format("%s moet ingevuld zijn!", attributeName));
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
