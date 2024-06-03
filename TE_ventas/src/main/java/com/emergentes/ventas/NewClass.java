
package com.emergentes.ventas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewClass {
     static public String driver = "com.mysql.cj.jdbc.Driver"; // Updated to latest driver class name
    static public String url = "jdbc:mysql://localhost:3306/bd_ventas";
    static public String usuario = "root";
    static public String password = "";
    
    protected Connection conn =null;

    public NewClass() {
         try {
             Class.forName(driver);
             conn=DriverManager.getConnection(url, usuario, password);
             if(conn!=null){
                 System.out.println("CONEXION OK"+conn);            
             }
         } catch (ClassNotFoundException ex) {
             System.out.println("ERROR EN DRIVE"+ ex.getMessage());   
         } catch (SQLException ex) {
             System.out.println("ERROR AL CONECTAR"+ex.getMessage());
         }
    
    
    }
   
    //creacion metodo 
    public Connection Conectar(){
    return conn; //devuelve la conexion
    }
    public void desconectar(){
         try {
             conn.close();
         } catch (SQLException ex) {
             System.out.println("ERROR AL CERRAR"+ex.getMessage());
         }
    }
    
}
