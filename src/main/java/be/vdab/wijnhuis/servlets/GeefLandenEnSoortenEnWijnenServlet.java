package be.vdab.wijnhuis.servlets;

//Imports
import be.vdab.wijnhuis.entities.Land;
import be.vdab.wijnhuis.entities.Soort;
import be.vdab.wijnhuis.services.LandenService;
import be.vdab.wijnhuis.services.SoortenService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Using the WebServlet to  let the server refere to this servlet as the index.html
@WebServlet("/index.html")

public class GeefLandenEnSoortenEnWijnenServlet extends HttpServlet {

    //Version ID for Serializable
    private static final long serialVersionUID = 1L;

    //Redirection
    private static final String VIEW = "/WEB-INF/jsp/pages/selection.jsp";

    //Fouten
    private ArrayList<String> fouten;

    //Services
    private final LandenService LANDSVC = new LandenService();
    private final SoortenService SOORTSVC = new SoortenService();

    
//The doGet method
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Resettting the errorArray
        fouten = new ArrayList<>();

        //=Getting the 'landen'====================================================================
        List<Land> landen = LANDSVC.findAll();
        if (landen == null || landen.isEmpty()) {
            fouten.add("Geen landen gevonden!");
        }

        //=Getting the selected 'land'  incl. 'soorten' (only if a landNr is given)================================================
        Land landSelect = null;

        String landNr = request.getParameter("landNr");
        if (landNr != null) {
            try {
                     landSelect = LANDSVC.read( Long.parseLong(landNr));
            } catch (NumberFormatException numExc) {
            }
        }

        //=Getting the selected 'soort'  incl. 'wijnen' (only when a soortnr is given)=============================================
        Soort soortSelect = null;
        
         String soortNr = request.getParameter("soortNr");
        if (soortNr != null) {
            try {
                     soortSelect = SOORTSVC.read( Long.parseLong(soortNr));
            } catch (NumberFormatException numExc) {
            }
        }

        //Add the errors as attribute (only when there are any)
        if (!fouten.isEmpty()) {
            request.setAttribute("fouten", fouten);
        }

        //Adding the object Attributes
        request.setAttribute("landen", landen);
        request.setAttribute("selectedLand", landSelect);
        request.setAttribute("selectedSoort", soortSelect);
   

    //Forwarding to the webpage
    request.getRequestDispatcher(VIEW).forward(request, response);
    }

}
