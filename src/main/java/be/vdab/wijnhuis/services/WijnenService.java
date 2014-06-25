package be.vdab.wijnhuis.services;

//Imports
import be.vdab.wijnhuis.dao.WijnenDAO;
import be.vdab.wijnhuis.entities.Wijn;
import java.util.List;

public class WijnenService {
    //DataAccesObject
    private final WijnenDAO WIJNENDAO = new WijnenDAO();
    

    //Constructor
    public WijnenService() {

    }

    //=Reading Data============================
    public Wijn read(long wijnNr) {
        return WIJNENDAO.read(wijnNr);
    }

    public List<Wijn> findAllByLand(Long soortNr) {
        return WIJNENDAO.findAllBySoort(soortNr);
    }

    public boolean exists(long wijnNr) {
        return (WIJNENDAO.read(wijnNr) != null);
    }

}
