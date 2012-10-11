/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/ControladorUsuarios"})
public class ControladorUsuarios extends HttpServlet {

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
        //El parametro acción determina que se debe hacer, 
        //debe ser enviado desde el JSP
        String accion = request.getParameter("accion");
        //registrar nuevo usuario en el portal
        if (accion.equals("insertar")) {
            insertar(request, response);
        } else if (accion.equals("listar")) {
            //ver todos los usuarios del portal
            todos(request, response);
        } else if (accion.equals("eliminar")) {
            //eliminar un usuario
            eliminar(request, response);
        } else if (accion.equals("editar")) {
            //Solicita pagina para editar
            editar(request, response);
        } else if (accion.equals("modificar")) {
            //modificar los datos de un usuario
            modificar(request, response);
        } else {
            request.getRequestDispatcher("/Error.jsp").include(request, response);
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

private void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
            String ape = request.getParameter("ape");
            String correoo = request.getParameter("correoo");
            String pass = request.getParameter("passwor");
            String tel = request.getParameter("tel");
            String genero = request.getParameter("genero");
            String perfil = "4"; //visitante

        RequestDispatcher vista;
        //Datos de la conexion
        String driver = "com.mysql.jdbc.Driver";
        String urlDB = "jdbc:mysql://localhost/dbportalunac";
        String userBD = "PortalUNAC";
        String passBD = "root";

        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        int resultado = 0;//resultado de las inserción sql
        ResultSet  resultado2 = null;
        String sql = "";
        
        try {
            //CARGAR DRIVER
            Class.forName(driver);

            //ESTABLECER CONEXION
            con = DriverManager.getConnection(urlDB, userBD, passBD);
            System.out.println("Conectado ...");

            //Definición de Sentencia SQL
             sql = "SELECT login FROM usuarios WHERE login = '" + correoo +"'";
             
             sentencia = con.createStatement();
           
            resultado2 = sentencia.executeQuery(sql);
            
            if(resultado2.next()){
           
            request.setAttribute("login", resultado2.getString("login") );
            
            vista = request.getRequestDispatcher("index.jsp");
            vista.forward(request, response);
            }else{
               
              sql = "INSERT INTO usuarios(login,clave,estado,nombre,apellidos,telefono,genero,"
                    + "perfil) VALUES ('" + correoo + "','" + pass + "',0,'" + nombre + "','" + ape
                    + "','" + tel + "'," + genero + "," + perfil + ")";    
                 
                 sentencia = con.createStatement();
            resultado = sentencia.executeUpdate(sql);
            System.out.println(resultado);//numero de filas afectadas
            request.setAttribute("guardoOK", resultado);
            
           vista = request.getRequestDispatcher("index.jsp");
           vista.forward(request, response);
            
             }
                        
            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeUpdate(sql);

            System.out.println(resultado);//numero de filas afectadas
            request.setAttribute("guardoOK", resultado);
            vista = request.getRequestDispatcher("index.jsp");
            vista.forward(request, response);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se ha podido cargar el Driver de MySQL");
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
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                request.getRequestDispatcher("/Error.jsp").include(request, response);
            }
        }

    }


private void todos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Datos de la conexion
        
        String driver = "com.mysql.jdbc.Driver";
        String urlDB = "jdbc:mysql://localhost/dbportalunac";
        String userBD = "PortalUNAC";
        String passBD = "root";

        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        ResultSet resultado = null;//resultado de las inserción sql
        String sql = "";
        try {
            //CARGAR DRIVER
            Class.forName(driver);

            //ESTABLECER CONEXION
            con = DriverManager.getConnection(urlDB, userBD, passBD);
            System.out.println("Conectado ...");

            //Definición de Sentencia SQL
            sql = "SELECT login,clave,estado,nombre,apellidos,telefono,genero,perfil FROM usuarios";

            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(sql);

            //arreglo donde se gurdaran los usuarios encontrados en la BD
            ArrayList Usuarios = new ArrayList();
            while (resultado.next()) //si el resultado tiene datos empezar a guardarlos.
            {
                Usuario e = new Usuario(resultado.getString(1), resultado.getString(2),
                        resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),
                        resultado.getInt(8),resultado.getInt(3));
                //Agregamos el estudiante al arrelo
                Usuarios.add(e);
            }
            // Agregar el arreglo de estudiantes a la solicitud
            request.setAttribute("usuarios", Usuarios);
            //redirigir la solicitud a la página JSP
            request.getRequestDispatcher("/ListaUsuarios.jsp").include(request, response);
            //cerrar la conexion
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("No se ha podido cargar el Driver de MySql");
            //request.getRequestDispatcher("/Error.jsp").include(request, response);
            //request.getRequestDispatcher("/Error.jsp").forward(request, response);
            response.sendRedirect("Error.jsp");
        } catch (SQLException ex) {
            System.out.println("No se ha podido establecer la conexión, o el SQL esta mal formado " + sql);
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }
    }
    
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void modificar(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void nuevo(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void salir(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
  }
