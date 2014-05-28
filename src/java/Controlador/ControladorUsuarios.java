package Controlador;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Entidades.Usuario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * 
     * 
     */
    
    Conexion conBD = null;//objeto conexión
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
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
            buscar_for_editar(request, response);
        
        }else if (accion.equals("modificar")) {
            //modificar los datos de un usuario
            modificar(request, response);
        
       } else if (accion.equals("nuevo")) {
            //modificar los datos de un usuario
            nuevo(request, response);
        
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
      
        String nombre = request.getParameter("nombre");
        String ape = request.getParameter("ape");
        String usuario = request.getParameter("user");
        String pass = request.getParameter("passwor");
        String cedula = request.getParameter("cedula");
        
      

        RequestDispatcher vista;
        //Datos de la conexion
        String driver = "com.mysql.jdbc.Driver";
        String urlDB = "jdbc:mysql://localhost:3307/db_comunidad_alpha";
        String userBD = "root";
        String passBD = "root";

        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        int resultado = 0;//resultado de las inserción sql
      String sql = "";

        try {
            //CARGAR DRIVER
            Class.forName(driver);

            //ESTABLECER CONEXION
            con = DriverManager.getConnection(urlDB, userBD, passBD);
            System.out.println("Conectado ...");


                sql = "INSERT INTO usuarios VALUES (" + cedula + ",'" + nombre + "','" + ape + "','" + usuario + "','" + pass + "')";
              

                sentencia = con.createStatement();
                resultado = sentencia.executeUpdate(sql);
                
                
                request.setAttribute("guardoOK", resultado);
               vista = request.getRequestDispatcher("/NewEditUsuario.jsp");
               vista.forward(request, response);
               
            } catch (SQLException ex) {
            System.out.println("No se ha podido establecer la conexión, o el SQL esta mal formado " + sql);
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        } finally {
            try {
                //Liberar recursos
                if (sentencia != null) {
                    sentencia.close();
                }
//                
            } catch (SQLException ex) {
                request.getRequestDispatcher("/Error.jsp").include(request, response);
            }
        }

    }

    private void todos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Datos de la conexion

            String driver = "com.mysql.jdbc.Driver";
            String urlDB = "jdbc:mysql://localhost:3307/db_comunidad_alpha";
            String userBD = "root";
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
            sql = "SELECT * FROM usuarios";

            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(sql);

            //arreglo donde se gurdaran los usuarios encontrados en la BD
            ArrayList Usuarios = new ArrayList();
            while (resultado.next()) //si el resultado tiene datos empezar a guardarlos.
            {
                Usuario e = new Usuario(resultado.getInt(1), resultado.getString(2),
                        resultado.getString(3), resultado.getString(4), resultado.getString(5));
                       
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

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher vista;
        //Datos de la conexion
        String driver = "com.mysql.jdbc.Driver";
        String urlDB = "jdbc:mysql://localhost:3307/db_comunidad_alpha";
        String userBD = "root";
        String passBD = "root";

        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        int resultado = 0;//resultado de las inserción sql
        String sql = "";

        try {
            //CARGAR DRIVER
            Class.forName(driver);

            //ESTABLECER CONEXION
            con = DriverManager.getConnection(urlDB, userBD, passBD);
            System.out.println("Conectado ...");

            //OBTENER EL DATO A ELIMINAR
            String usuario = request.getParameter("IDs");
            
            
            if (usuario == "jerojas"){
                
            request.setAttribute("mensaje", "Imposible borrar Administrador !");
            
            }else{
                
           //Definición de Sentencia SQL
            sql = "DELETE FROM USUARIOS WHERE usuario ='" + usuario + "'";

            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeUpdate(sql);
            System.out.println("Borrado exitoso !");
            request.setAttribute("mensaje", "Registro borrado exitosamente !");
            }
            //cerrar la conexion
            con.close();

            //listar de nuevo los datos
            todos(request, response);

        } catch (ClassNotFoundException ex) {
            System.out.println("No se ha podido cargar el Driver de MySql");
            //request.getRequestDispatcher("/Error.jsp").forward(request, response);
            response.sendRedirect("Error.jsp");
        } catch (SQLException ex) {
            System.out.println("No se ha podido establecer la conexión, o el SQL esta mal formado " + sql);
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }
    }
    
     private void buscar_for_editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        //Datos de la conexion
       

        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        ResultSet resultado = null;//Objeto para obtener los resultados de las consultas sql
        String sql = "";
        
        
         String driver = "com.mysql.jdbc.Driver";
        String urlDB = "jdbc:mysql://localhost:3307/db_comunidad_alpha";
        String userBD = "root";
        String passBD = "root";

        //Objeto Usuario, donde se guardará la información del registro a editar
        Usuario e = null;
        try {
            
           Class.forName(driver);

            //ESTABLECER CONEXION
            con = DriverManager.getConnection(urlDB, userBD, passBD);
            System.out.println("Conectado ...");
           
            //OBTENER EL DATO A CONSULTAR
            String codigo = request.getParameter("ID");

            //Definición de Sentencia SQL
            sql = "SELECT * FROM usuarios  WHERE usuario = '" + codigo + "'";
            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(sql);

            // VER SI HAY RESULTADOS
            while (resultado.next()) {
                e = new Usuario(resultado.getInt(1), resultado.getString(2), resultado.getString(3), resultado.getString(4),resultado.getString(5));
                break; //debe haber un solo registro.
            }
            // Agregar el producto a la solicitud
            request.setAttribute("usuario", e);

            

            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(sql);

           

            //redirigir la solicitud a la página JSP
            request.getRequestDispatcher("/NewEditUsuario.jsp").include(request, response);
            //cerrar la conexion
            //con.close();
        /*
             * } catch (ClassNotFoundException ex) { System.out.println("No se
             * ha podido cargar el Driver de MySQL");
             * //request.getRequestDispatcher("/Error.jsp").forward(request,
             * response); response.sendRedirect("Error.jsp");
             */
        } catch (SQLException ex) {
            System.out.println("No se ha podido establecer la conexión, o el SQL esta mal formado " + sql);
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }
    }

    
    private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        String sql = "";

        ResultSet resultado = null;//Objeto para obtener los resultados de las consultas sql
        con = conBD.getCConexion();
        System.out.println("Conectado ...");
        request.getRequestDispatcher("/NewEditUsuario.jsp").forward(request, response);
    
    
    }

    private void salir(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

   

    private void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {

        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String ape = request.getParameter("ape");
        
        String usuario = request.getParameter("id");
        String pass = request.getParameter("passwor");
        
        

        RequestDispatcher vista;
        //Datos de la conexion
        String driver = "com.mysql.jdbc.Driver";
        String urlDB = "jdbc:mysql://localhost:3307/db_comunidad_alpha";
        String userBD = "root";
        String passBD = "root";

        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        String sql = "";
        int filasafectadas = 0;

        try {

            //CARGAR DRIVER
            Class.forName(driver);

            //ESTABLECER CONEXION
            con = DriverManager.getConnection(urlDB, userBD, passBD);
            System.out.println("Conectado ...");

            //Definición de Sentencia SQL
            sql = "UPDATE usuarios SET cedula =" + cedula + ", "
                    + "nombre='" + nombre + "', "
                    + "apellidos='" + ape + "', "
                    + "password='" + pass + "' "
                    + "WHERE usuario ='" + usuario + "'";


            //Ejecutar sentencia
            sentencia = con.createStatement();
            filasafectadas = sentencia.executeUpdate(sql);

            System.out.println("Actualizacion exitosa ! ...");
            request.setAttribute("mensaje", "Registro modificado exitosamente !");
            todos(request, response);
            //request.getRequestDispatcher("/DatosIngresados.jsp").include(request, response);

        } catch (SQLException ex) {
            request.getRequestDispatcher("/Error.jsp").include(request, response);
            System.out.println("No se ha podido Insertar, o el SQL esta mal formado " + sql);
        } finally {
            try {
                //Liberar recursos
                sentencia.close();
                //cerrar conexion
                con.close();
            } catch (SQLException ex) {
                request.getRequestDispatcher("/Error.jsp").include(request, response);
            }
        }
    }

    
}
