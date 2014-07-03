package be.vdab.wijnhuis.dao;

//Imports
import be.vdab.wijnhuis.Filters.JPAFilter;
import javax.persistence.EntityManager;

//Abstract class
public abstract class DataAccesObject {

    protected EntityManager getEntityManager() {
        return JPAFilter.getManager();
    }

    public void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public void commit() {
        getEntityManager().getTransaction().commit();
    }

    public void rollback() {
        getEntityManager().getTransaction().rollback();
    }

    public void create(Object obj) {
        getEntityManager().persist(obj);
    }

}
