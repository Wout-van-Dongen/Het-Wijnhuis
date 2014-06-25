package be.vdab.wijnhuis.services;

//Imports
import be.vdab.wijnhuis.dao.SoortenDAO;
import be.vdab.wijnhuis.entities.Soort;
import java.util.List;

public class SoortenService {
    //DataAccesObject
    private final SoortenDAO SOORTDAO = new SoortenDAO();
    

    //Constructor
    public SoortenService() {

    }

    //=Reading Data============================
    public Soort read(long soortNr) {
        return SOORTDAO.read(soortNr);
    }

    public List<Soort> findAllByLand(Long landNr) {
        return SOORTDAO.findAllByLand(landNr);
    }

}
