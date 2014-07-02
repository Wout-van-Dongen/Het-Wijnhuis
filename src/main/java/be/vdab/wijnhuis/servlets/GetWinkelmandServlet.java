package be.vdab.wijnhuis.servlets;

//Imports
import be.vdab.wijnhuis.entities.Wijn;
import be.vdab.wijnhuis.services.WijnenService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/wijnen/winkelmandje.html")
public class GetWinkelmandServlet extends HttpServlet {

    //Fouten
    private ArrayList<String> fouten;

    //Services
    private final WijnenService WIJNSVC = new WijnenService();

    //VIEWS
    private static final String VIEW = "/WEB-INF/jsp/pages/winkelmand.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean specificError = false;

        //Getting the  fouten lijst
        String foutenAsString = getParameter(request, "fouten");
      
        String[] foutenAsArray;
        if (foutenAsString != null) {
             foutenAsString =   foutenAsString.substring(1, foutenAsString.length()-1);
            foutenAsArray = foutenAsString.split(",");
               fouten =  new ArrayList<>(Arrays.asList(foutenAsArray));
        } else {
            fouten = new ArrayList<>();
        }

        //Checks for specific errors
        String naamFout = getParameter(request, "naamFout"),
                straatFout = getParameter(request, "straatFout"),
                huisnrFout = getParameter(request, "huisnrFout"),
                gemeenteFout = getParameter(request, "gemeenteFout"),
                postcodeFout = getParameter(request, "postcodeFout");

        //Gets the filled in values
        String naam = getParameter(request, "naam"),
                straat = getParameter(request, "straat"),
                huisnr = getParameter(request, "huisnr"),
                gemeente = getParameter(request, "gemeente"),
                postcode = getParameter(request, "postcode");

        //If specific errors occure
        if (naamFout != null && !naamFout.equals("null")) {
            setAttribute(request, "naamFout", naamFout);
        } else if (naam != null) {
            setAttribute(request, "naam", naam);
        }
        if (straatFout != null && !straatFout.equals("null")) {
            setAttribute(request, "straatFout", straatFout);
        } else if (straat != null) {
            setAttribute(request, "straat", straat);
        }
        if (huisnrFout != null && !huisnrFout.equals("null")) {
            setAttribute(request, "huisnrFout", huisnrFout);
        } else if (huisnr != null) {
            setAttribute(request, "huisnr", huisnr);
        }
        if (postcodeFout != null && !postcodeFout.equals("null")) {
            setAttribute(request, "postcodeFout", postcodeFout);
        } else if (postcode != null) {
            setAttribute(request, "postcode", postcode);
        }
        if (gemeenteFout != null && !gemeenteFout.equals("null")) {
            setAttribute(request, "gemeenteFout", gemeenteFout);
        } else if (gemeente != null) {
            setAttribute(request, "gemeente", gemeente);
        }

        //Winkelmandje
        Map<Long, Integer> sessionMandje;
        Map<Wijn, Integer> weergaveMandje;

        weergaveMandje = new LinkedHashMap<>();
        sessionMandje = (Map<Long, Integer>) getSessionAttribute(request, "winkelmandje");
        if (sessionMandje != null) {
            for (Map.Entry<Long, Integer> entry : sessionMandje.entrySet()) {
                Wijn wijn = WIJNSVC.read(entry.getKey());
                if (wijn != null) {
                    weergaveMandje.put(wijn, entry.getValue());
                } else {
                    fouten.add("Kan de wijn met het nummer " + entry.getKey() + " niet terugvinden in ons assortiment");
                }
            }
        }
        if (!fouten.isEmpty()) {
            setAttribute(request, "fouten", fouten);
        }

        setAttribute(request, "winkelmandje", weergaveMandje);
        forwardRequest(request, response, VIEW);
    }

//======================================================================================================================================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //Gets the request Attribute
    private Object getAttribute(HttpServletRequest request, String name) {
        return request.getAttribute(name);
    }

    //Gets the request Parameter
    private String getParameter(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    //Sets the request Attribute
    private void setAttribute(HttpServletRequest request, String name, Object attribute) {
        request.setAttribute(name, attribute);
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
