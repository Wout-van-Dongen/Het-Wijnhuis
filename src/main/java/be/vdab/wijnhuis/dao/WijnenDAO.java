package be.vdab.wijnhuis.dao;

//Imports
import be.vdab.wijnhuis.entities.Wijn;
import java.util.List;
import javax.persistence.TypedQuery;

//Inherits getManager, beginTransaction, commit & rollback from Super Class DataAccesObject
public class WijnenDAO extends DataAccesObject {

    //Constructor
    public WijnenDAO() {

    }

    //=Writing Data============================
    //=Reading Data============================
    public Wijn read(long wijnNr) {
        return getEntityManager().find(Wijn.class, wijnNr);
    }

    public List<Wijn> findAllBySoort(long soortNr) {
        TypedQuery<Wijn> wijnQuery = getEntityManager().createQuery("SELECT w FROM Wijn JOIN FETCH w.soort", Wijn.class);
        wijnQuery.setParameter("soortnr", soortNr);
        return wijnQuery.getResultList();
    }
    
    
    public boolean exists(long wijnNr){
         return (getEntityManager().createQuery("SELECT w.wijnNr FROM Wijn", Long.class)  != null);
    }

}
