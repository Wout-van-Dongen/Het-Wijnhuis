package be.vdab.wijnhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/wijnen/bevestigen.html")
public class BevestigBestellingServlet extends HttpServlet {
    //Fouten
    private ArrayList<String> fouten;
    
    //Views
    private final static String REDIRECT = "";
    

 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
//Resetting fouten
        fouten = new ArrayList<>();
        
        //Read in the attributes
        
        //Validate the attributes
        
        //Check
        //Begin transaction
            //write the order to the db

        
        
        
        
        
    }

}
