package be.vdab.wijnhuis.services;

//Imports
import be.vdab.wijnhuis.dao.LandenDAO;
import be.vdab.wijnhuis.entities.Land;
import java.util.List;

public class LandenService {
    
    //DataAccesObjects
    private final LandenDAO LANDDAO = new LandenDAO();

    
    //Constructor
    public LandenService() {

    }

    //=Reading Data============================
    
        //Returns the 'land' with the corresponding 'landNr''
    public Land read(long landNr) {
        return LANDDAO.read(landNr);
    }
    
    //Returns a list with all 'landen''
    public List<Land> findAll() {
        return LANDDAO.findAll();
    }

}
