package be.vdab.wijnhuis.servlets;

//Imports
import be.vdab.wijnhuis.entities.Wijn;
import be.vdab.wijnhuis.services.WijnenService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/wijnen/bestellen.html")
public class BestelWijnServlet extends HttpServlet {

    //Version ID for Serializable
    private static final long serialVersionUID = 1L;

    //Redirection
    private static final String VIEW = "/WEB-INF/jsp/pages/bestellen.jsp";
    private static final String REDIRECT = "/wijnen/winkelmandje.html";

    //Fouten
    private ArrayList<String> fouten;
    private final static String NAN = "%s is geen getal!",
            NEG = "%s moet een positief getal bedragen!",
            NUL = "%s heeft geen waarde meegekregen! ";

    //Services
    private final WijnenService WIJNSVC = new WijnenService();

    //Sends the wijn data to the order form ================================================================================================================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Wijn wijn = null;

            fouten = new ArrayList<>();
                //Reading in the 'wijn'
                try {
                    wijn = WIJNSVC.read(Long.parseLong(request.getParameter("wijnNr")));
                    if (wijn == null) {
                        fouten.add("De door u gekozen wijn kan niet worden teruggevonden!");
                    }
                } catch (NumberFormatException numExc) {
                    fouten.add("De meegegeven referentie is van een ongeldig type");
                }
            
        

        //Voegt de fouten toe aan de request (wanneer er fouten zijn)
        if (!fouten.isEmpty()) {
            setAttribute(request, "fouten", fouten);
            forwardRequest(request, response, "/");
        } else {
            if (wijn != null) {
                setAttribute(request, "wijn", wijn);
            }
            forwardRequest(request, response, VIEW);
        }

    }

    //Handles the order data ===========================================================================================================================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Reseting de fouten lijst

        fouten = new ArrayList<>();

        //Numbers
        int aantal = 0;
        long wijnNr = 0;

        //Bool
        boolean specificError = false;

        //Winkelmandje
        Map<Long, Integer> winkelmandje;

        //Getting Attributes
        String aantalAsString = request.getParameter("aantal");
        String wijnNrAsString = request.getParameter("wijnNr");

        winkelmandje = (Map<Long, Integer>) getSessionAttribute(request, "winkelmandje");
        if (winkelmandje == null) {
            winkelmandje = new LinkedHashMap<>();
        }

        //Validating AANTAL
        aantal = validateInput(aantalAsString, "Het aantal").intValue();
        if (aantal == 0) {
            fouten.add("Een bestelling moet minimum uit één artikel bestaan!");
        }

        //Validating WIJNNR
        {
            wijnNr = validateInput(wijnNrAsString, "Het wijnnr");
            if (!wijnExists(wijnNr)) {
                fouten.add("De door u gekozen wijn kan niet worden teruggevonden in ons assortiment!");
            }
        }

        //If there are errors return them to the 'bestellen' page
        if (!fouten.isEmpty()) {
            setAttribute(request, "fouten", fouten);
            forwardRequest(request, response, VIEW);

            //Als er geen fouten zijn
        } else {

            //checks if the wijn is already present in the order
            //if it is then add the ammount to the current one
            try {
                if (winkelmandje.containsKey(wijnNr)) {
                    winkelmandje.put(wijnNr, winkelmandje.get(wijnNr) + aantal);
                    //else add the wijn with the current ammount
                } else {
                    winkelmandje.put(wijnNr, aantal);
                }
            } catch (NullPointerException nullExc) {
                System.err.println(nullExc);
            }

        }
        setSessionAttribute(request, "winkelmandje", winkelmandje);
        response.sendRedirect(request.getContextPath() + response.encodeRedirectURL(REDIRECT));

    }

    //Wijn exists
    private boolean wijnExists(long wijnNr) {
        return !(WIJNSVC.read(wijnNr) == null);
    }

    //Validating input
    private Long validateInput(String waarde, String naam) {
        long num = 0;
        if (waarde != null) {
            try {
                num = Long.parseLong(waarde);
                //Wanneer het aantal kleiner is dan 1
                if (num < 0) {
                    fouten.add(String.format(NEG, naam));
                    //Wanneer de variable geen getal is
                }
            } catch (NumberFormatException numExc) {
                fouten.add(String.format(NAN, naam));
            }
            //Wanneer de variable leeg is
        } else {
            fouten.add(String.format(NUL, naam));
        }
        return num;
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

    //Sets the session Attribute
    private void setAttribute(HttpSession session, String name, Object attribute) {
        session.setAttribute(name, attribute);
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
