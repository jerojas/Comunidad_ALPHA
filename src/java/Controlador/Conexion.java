package Controlador;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jeovany
 */
/*
 * Conexion.java
 *
 * Created on 25 de mayo de 2006, 08:03 PM
 *
 * To change this template, choose Tools | Template Manager and open the
 * template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private Connection cConexion = null;
    private Statement sSentencia = null;
    private String urlDB;
    private String userBD;
    private String passBD;
    private String driver;

    /**
     * Creates a new instance of Conexion
     */
    public Conexion() {
        //Datos de la conexion
        this.driver = "com.mysql.jdbc.Driver";
        this.urlDB = "jdbc:mysql://localhost/dbportalunac";
        this.userBD = "root";
        this.passBD = "root";
    }

    public Conexion(String stringConexion, String usuario, String password) {
        this.urlDB = stringConexion;
        this.userBD = usuario;
        this.passBD = password;
        this.driver = "com.mysql.jdbc.Driver";
    }

    public void crearConexion() {
        try {
            Class.forName(driver).newInstance();

            this.setCConexion(DriverManager.getConnection(this.urlDB, this.userBD,
                    this.passBD));
            this.setSSentencia(this.getCConexion().createStatement());
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("No se pudo abrir la base de datos");
        }
    }

    public void cerrarConexion() throws SQLException {
        if (this.cConexion != null) {
            this.cConexion.close();
        }
        if (this.sSentencia != null) {
            this.sSentencia.close();
        }
    }

    public Connection getCConexion() {
        return cConexion;
    }

    public void setCConexion(Connection cConexion) {
        this.cConexion = cConexion;
    }

    public Statement getSSentencia() {
        return sSentencia;
    }

    public void setSSentencia(Statement sSentencia) {
        this.sSentencia = sSentencia;
    }

    /*public static void main(String[] args) {
        Conexion con = new Conexion();
        con.crearConexion();
    }*/
}
