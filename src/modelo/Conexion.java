/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sergio
 */
public class Conexion {
     public Connection miconexion;
    private static final String URL = "jdbc:mysql://localhost:3306/guatemala2";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection getMiconexion() {
        return miconexion;
    }
    public void setMiconexion(Connection miconexion) {
        this.miconexion = miconexion;
    }
    public void Conectar() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            miconexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
          System.out.println("Error en conexion" + ex);
        }
    }
    public void cerrarConex() {
        try {
            if (miconexion != null) {
                if (miconexion.isClosed() == false) {
                    miconexion.close();
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar: " + ex);
        }
    }  
    
}
