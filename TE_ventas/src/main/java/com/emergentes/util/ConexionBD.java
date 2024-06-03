

package com.emergentes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    static public String driver = "com.mysql.cj.jdbc.Driver"; // Updated to latest driver class name
    static public String url = "jdbc:mysql://localhost:3306/bd_ventas";
    static public String usuario = "root";
    static public String password = "";
    
    protected Connection conn = null;
    
    public ConexionBD() {
        try {
            Class.forName(driver); // Corrected driver class loading
            conn = DriverManager.getConnection(url,usuario, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en el driver: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error al conectar: " + ex.getMessage());
        }
    }
    
    public Connection conectar() {
        return conn;
    }
    
    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) { // Added check to avoid NullPointerException
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion: " + ex.getMessage());
        }
    }
}

