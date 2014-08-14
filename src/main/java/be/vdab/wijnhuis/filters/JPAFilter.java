package be.vdab.wijnhuis.filters;

//Imports
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("*.html")
public class JPAFilter implements Filter {

    private static EntityManagerFactory entityManagerFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("wijnhuis");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        entityManagerFactory.close();
    }

    public static EntityManager getManager() {
        if(entityManagerFactory == null){
            throw new RuntimeException("entityFactoryManager has not been initialised (yet)");
        }else{
        return entityManagerFactory.createEntityManager();
        }
    }
}
