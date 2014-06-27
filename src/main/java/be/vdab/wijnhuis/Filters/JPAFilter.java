package be.vdab.wijnhuis.Filters;

//Imports
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter("*.html")

public class JPAFilter implements Filter {

    //EntityManager creation
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("wijnhuis");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        entityManagerFactory.close();
    }
}
