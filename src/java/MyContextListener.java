
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guillermo
 */
public class MyContextListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc=sce.getServletContext();
        Mensajes m=new Mensajes(sc.getInitParameter("Desarrollador"));
        sc.setAttribute("OMensaje", m);//agregar el objeto al contexto de la aplicacion.        
        System.out.println("inicio de la aplicación...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {        
        System.out.println("fin de la aplicación...");
    }
    
}
