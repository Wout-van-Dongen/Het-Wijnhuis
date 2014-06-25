package be.vdab.wijnhuis.dao;

import be.vdab.wijnhuis.entities.Land;
import java.util.List;

//Inherits all methods defined in Super Class DataAccesObject
public class LandenDAO extends DataAccesObject {

    //Constructor
    public LandenDAO() {

    }

          //=Writing Data============================
    
    //=Reading Data============================
    public Land read(long landNr) {
        return getEntityManager().find(Land.class, landNr);
    }

    public List<Land> findAll() {
        return getEntityManager().createQuery("SELECT L FROM Land L", Land.class).getResultList();
    }

}
