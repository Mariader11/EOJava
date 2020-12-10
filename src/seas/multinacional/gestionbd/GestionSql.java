/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seas.multinacional.gestionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author maria
 */
public class GestionSql {
    
    static String DRIVER_JDBC = "com.mysql.jdbc.Driver";;
    static String DB_URL = "jdbc:mysql://localhost:3306/elpiscolabis";
    static String USER = "root";
    static String PASS = null;
    static public Connection conn = null;

    public GestionSql() {    
      openConnection();
    }
    
    /**
     *  Establece la conexión con la base de datos
     */
    public static void openConnection() {
        try {
        //PASO 0: Registraríamos el driver con JDBC 3.0
        Class.forName(DRIVER_JDBC);
        //PASO 1: Open a connection
        System.out.println("Conectando con la BBDD...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        System.out.println("Conexión extablecida OK...");
        } catch (SQLException se) {
        se.printStackTrace();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    
    /**
    *
    * Cierra la conexión con la base de datos
    */
    public static void closeConnection() {
        try {
        if (conn != null) {
        conn.close();
        System.out.println("Hasta pronto!");
        }
        } catch (SQLException se) {
        se.printStackTrace();
        }
    }
    
    
        /**
         * Método que encuentra el id siguiente al último existente en la base de datos
         * 
         * @param aux String que corresponde a la tabla a consultar en la base de datos
         * @return id siguiente al último id existente en la base de datos
         */
    
        public int siguienteId(String aux) {
        int id = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            if (aux.equals("clientes")) {
              ps = conn.prepareStatement("SELECT MAX(id) FROM clientes");
            } else if (aux.equals("vendedores")){
              ps = conn.prepareStatement("SELECT MAX(id) FROM vendedores");
            } else if (aux.equals("tickets")){
              ps = conn.prepareStatement("SELECT MAX(id) FROM tickets");
            } else if (aux.equals("lineas")){
              ps = conn.prepareStatement("SELECT MAX(id) FROM lineas");
            }
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1) + 1;
            }
        } catch (Exception ex) {
            System.out.println("Error"+ex.getMessage());
        }
        finally{
            try{
                ps.close();
                rs.close();
            }catch(Exception ex){    
            }
        }
        return id;
        }
        
}
