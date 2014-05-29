package Controlador;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JEOVANY
 */
@WebServlet(name = "ValidarIngreso", urlPatterns = {"/ValidarIngreso"})
public class ValidarIngreso extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         
               
       String accion = request.getParameter("accion");
        if (accion != null) {
            response.sendRedirect("http://www.unac.edu.co");
        } else {
            String user = request.getParameter("user");
            String pass = request.getParameter("password");
            
            RequestDispatcher vista = null;
            System.out.println("Intentando conectar a BD ....");
         
          // Datos de la conexion
          String driver = "com.mysql.jdbc.Driver";
            String urlDB = "jdbc:mysql://localhost:3307/db_comunidad_alpha";
            String userBD = "root";
            String passBD = "root";
            
             //Objetos para manipular la conexion y los datos
            Connection conexion = null;//Objeto para la conexion
            Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
            ResultSet resultado = null;//Objeto para obtener los resultados de las consultas sql
            String sql = "";
          
            try {
                //CARGAR DRIVER
                Class.forName(driver);

                //ESTABLECER CONEXION
                conexion = DriverManager.getConnection(urlDB, userBD, passBD);
                System.out.println("Conectado ...");

                //Definición de Sentencia SQL
                
                sql = "SELECT * FROM usuarios WHERE usuario = '" + user + "' AND "
                        + "password = '" + pass + "'";

                //Ejecutar sentencia
                sentencia = conexion.createStatement();
                resultado = sentencia.executeQuery(sql);

                //si el resultado tiene un dato el usuario con el login y pass existe, autenticación valida.
                if (resultado.next())                     
                {
                   
                    System.out.println("autenticado ...");
                   
                     request.setAttribute("usuario", resultado.getString ("nombre"));                    
                   
                    vista = request.getRequestDispatcher("Home.jsp");   
                 

                } else {
                    System.out.println("Error usuario no existe ...");
                    vista = request.getRequestDispatcher("index.jsp");                    
                }

                vista.forward(request, response);
            } catch (ClassNotFoundException ex) {
                System.out.println("No se ha podido cargar el Driver de MYSQL");
                //request.getRequestDispatcher("/Error.jsp").include(request, response);
                //request.getRequestDispatcher("/Error.jsp").forward(request, response);
                response.sendRedirect("Error.jsp");
            } catch (SQLException ex) {
                System.out.println("No se ha podido establecer la conexión, o el SQL esta mal formado " + sql);
                request.getRequestDispatcher("/Error.jsp").forward(request, response);
            } finally {
                try {
                    //Liberar recursos
                    if (sentencia != null) {
                        sentencia.close();
                    }
                    //cerrar conexion
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException ex) {
                    request.getRequestDispatcher("/Error.jsp").include(request, response);
                }
            }
        
        
    }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

