/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Entidades.Seminario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jeovany
 */
public class ControladorSeminarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
    
    Conexion conBD;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         request.setCharacterEncoding("UTF-8");
       
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
        } else if (accion.equals("modificar")) {
            //modificar los datos de un usuario
            modificar(request, response);
        } else if (accion.equals("nuevo")) {
            nuevo(request, response);//atender peticion para nuevo usuario
        }else if (accion.equals("salir")) { //salir de la aplicacion
            salir(request, response);//
        }  else {
            request.getRequestDispatcher("/Error.jsp").include(request, response);
        }
        
           
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

 private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        String sql = "";

        ResultSet resultado = null;//Objeto para obtener los resultados de las consultas sql
        con = conBD.getCConexion();
        System.out.println("Conectado ...");
        request.getRequestDispatcher("/NewEditSeminario.jsp").forward(request, response);
    
    
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
         String hora_inicio = request.getParameter("horainicio");
          String hora_fin = request.getParameter("horafin");
           String observ = request.getParameter("observ");
            String predicador = request.getParameter("predicador");
             String  fecha_creacion = fecha();
             
        
        RequestDispatcher vista;
        
        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        int resultado = 0;//resultado de las inserción sql
        String sql = "";

        try {
            
            con = conBD.getCConexion();
            System.out.println("Conectado ...");

            //Definición de Sentencia SQL
            sql = "INSERT INTO seminarios  VALUES (null, '" + nombre + "','" + fecha + "','" + hora_inicio + "','" + hora_fin + "','" + observ + "','" + predicador + "','" + fecha_creacion + "')";

            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeUpdate(sql);

            
           
            //si fue inserción del administrador o por un visitante en el portal redirigir a la pagina correcta
           
                request.setAttribute("guardoOK", resultado);
                vista = request.getRequestDispatcher("NewEditSeminario.jsp");
                vista.forward(request, response);
            
               // request.setAttribute("mensaje", "Registro creado exitosamente !");
              // todos(request, response);
            

        //   response.sendRedirect("Error.jsp");
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
        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        ResultSet resultado = null;//Objeto para obtener los resultados de las consultas sql
        String sql = "";
        try {
            con = conBD.getCConexion();
            //Definición de Sentencia SQL
            
            sql = "SELECT * FROM seminarios";

            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(sql);

            //arreglo donde se gurdaran los usuarios encontrados en la BD
            ArrayList Seminarios = new ArrayList();
            while (resultado.next()) //si el resultado tiene datos empezar a guardarlos.
            {
                Seminario e = new Seminario(resultado.getInt(1), resultado.getString(2),resultado.getString(3),resultado.getString(4),resultado.getString(5),resultado.getString(6),resultado.getInt(7),resultado.getString(8));
              //Agregamos el producto al arrelo
                Seminarios.add(e);
            }
            // Agregar el arreglo de productos  a la solicitud
            request.setAttribute("seminarios", Seminarios);
            //redirigir la solicitu a la página JSP
            request.getRequestDispatcher("/ListaSeminarios.jsp").include(request, response);
            //cerrar la conexion
            //con.close();
        } catch (SQLException ex) {
            System.out.println("No se ha podido establecer la conexión, o el SQL esta mal formado " + sql);
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        int resultado = 0;//resultado de las filas borradas sql
        String sql = "";
        try {

            //ESTABLECER CONEXION
            con = conBD.getCConexion();
            System.out.println("Conectado a BD...");

            //OBTENER EL DATO A ELIMINAR
            String codigo = request.getParameter("IDs");

            //Definición de Sentencia SQL
            sql = "DELETE FROM seminarios WHERE idseminarios='" + codigo + "'";

            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeUpdate(sql);
            System.out.println("Borrado exitoso !");
            request.setAttribute("mensaje", "Registro borrado exitosamente !");
            //cerrar la conexion
            //con.close();

            //listar de nuevo los datos
            todos(request, response);

        } catch (SQLException ex) {
            System.out.println("No se ha podido establecer la conexión, o el SQL esta mal formado " + sql);
            request.getRequestDispatcher("/Error.jsp").forward(request, response);
        }
    }

    /*
     * Método que busca la información de un usuario que será editada.
     */
    private void buscar_for_editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Datos de la conexion
       

        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        ResultSet resultado = null;//Objeto para obtener los resultados de las consultas sql
        String sql = "";

        //Objeto Usuario, donde se guardará la información del registro a editar
        Seminario e = null;
        try {
            
            con = conBD.getCConexion();
            System.out.println("Conectado ...");
            //OBTENER EL DATO A CONSULTAR
            String codigo = request.getParameter("ID");

            //Definición de Sentencia SQL
            sql = "SELECT * FROM seminarios WHERE idseminarios ='" + codigo + "'";
            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(sql);

            // VER SI HAY RESULTADODOS
            while (resultado.next()) {
                e = new Seminario(resultado.getInt(1), resultado.getString(2),resultado.getString(3),resultado.getString(4),resultado.getString(5),resultado.getString(6),resultado.getInt(7),resultado.getString(8));
                break; //debe haber un solo registro.
            }
            // Agregar el producto a la solicitud
            request.setAttribute("seminario", e);

            

            //Ejecutar sentencia
            sentencia = con.createStatement();
            resultado = sentencia.executeQuery(sql);

           

            //redirigir la solicitud a la página JSP
            request.getRequestDispatcher("/NewEditSeminario.jsp").include(request, response);
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

    private void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener los datos a modificar.
       
      String codigo = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
         String hora_inicio = request.getParameter("horainicio");
          String hora_fin = request.getParameter("horafin");
           String observ = request.getParameter("observ");
            String predicador = request.getParameter("predicador");
           
       
        RequestDispatcher vista;
        
        //Objetos para manipular la conexion y los datos
        Connection con = null;//Objeto para la conexion
        Statement sentencia = null;//Objeto para definir y ejecutar las consultas sql
        String sql = "";

       
        try {
            //ESTABLECER CONEXION
            //con = DriverManager.getConnection(urlDB, userBD, passBD);
            con = conBD.getCConexion();
            System.out.println("Conectado ...");

            //Definición de Sentencia SQL
            sql = "UPDATE seminarios SET nombre='" + nombre + "', "
                    + "fecha= '" + fecha + "',"
                     + "hora_inicio='" + hora_inicio + "',"
                     + "hora_fin='" + hora_fin + "',"
                     + "Observaciones='" + observ + "',"
                     + "predicador='" + predicador + "'"
                    + "WHERE idseminarios='" + codigo + "'";

            //Ejecutar sentencia
            sentencia = con.createStatement();
            int filasafectadas = sentencia.executeUpdate(sql);

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
                //con.close();
            } catch (SQLException ex) {
                request.getRequestDispatcher("/Error.jsp").include(request, response);
            }
        }
    }

    private void salir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //destruir la sesion
        HttpSession sesionOk = request.getSession();//se obtiene la sesion creada
        sesionOk.invalidate();//se destruye la sesión
        response.sendRedirect("index.jsp");
    }
    
    public String fecha(){
                String fecha="";
                Calendar c1 = new GregorianCalendar();
                String dia = Integer.toString(c1.get(Calendar.DATE));
                String mes = Integer.toString(c1.get(Calendar.MONTH)+1);
                String anio= Integer.toString(c1.get(Calendar.YEAR));
                if(c1.get(Calendar.DATE)<10){
                        dia="0".concat(dia);
                }
                if(c1.get(Calendar.MONTH)<10){
                        mes="0".concat(mes);
                }
                //String[] fecha={dia,mes,anio};
                return dia.concat("/").concat(mes).concat("/").concat(anio);
        }

    /*
     * Metodo en el cual se buscan los perfiles en BD y se envían a la pagina
     * NewEditUsuario.jsp
     */
   

    @Override
    public void init() throws ServletException {
        super.init();
        conBD = new Conexion();
        conBD.crearConexion();
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            conBD.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println("Error cerrando conexión... " + ex);
        }
    }

   }
