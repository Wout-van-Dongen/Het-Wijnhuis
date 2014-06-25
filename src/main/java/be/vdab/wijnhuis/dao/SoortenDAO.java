package be.vdab.wijnhuis.dao;

//Imports
import be.vdab.wijnhuis.entities.Soort;
import java.util.List;
import javax.persistence.TypedQuery;

//Inherits getManager, beginTransaction, commit & rollback from Super Class DataAccesObject
public class SoortenDAO extends DataAccesObject {

    //Constructor
    public SoortenDAO() {

    }

    //=Writing Data============================
    //=Reading Data============================
    public Soort read(long soortNr) {
        return getEntityManager().find(Soort.class, soortNr);
    }

    public List<Soort> findAllByLand(long landNr) {
        TypedQuery<Soort> soortQuery = getEntityManager().createQuery("SELECT S FROM Soort S WHERE S.landNr = :landnr", Soort.class);
        soortQuery.setParameter("landnr", landNr);
        return soortQuery.getResultList();
    }

}
