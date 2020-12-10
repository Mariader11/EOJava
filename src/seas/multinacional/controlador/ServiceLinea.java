/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seas.multinacional.controlador;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import seas.multinacional.gestionbd.GestionSql;
import seas.multinacional.modelo.Linea;

/**
 *
 * @author maria
 */
public class ServiceLinea {
    
    /**
     * Guarda en la base de datos la Línea con los datos ingresados como parámetros 
     * 
     * @param idTicket
     * @param idProducto
     * @param unidades
     * @param precioUnitario
     * @param descuento 
     */
    public void saveLinea(int idTicket, int idProducto, int unidades, float precioUnitario, float descuento) {
        GestionSql db = new GestionSql();
        int id = db.siguienteId("lineas");
        insertaPreparedStatement(id, idTicket, idProducto, unidades, precioUnitario, descuento, db);
        db.closeConnection();
    }
    
     /**
     * Método que elimina la Línea correspondiente al id pasado como parámetro
     *
     * @param id indice del elemento seleccionado de la lista de líneas
     */  
    public void deleteLinea(int id) {
        String SQL_DELETE_LINEA = "DELETE FROM lineas WHERE id = " + id;
         try {
            GestionSql db = new GestionSql();
            Statement stmt = db.conn.createStatement();
            stmt.executeUpdate(SQL_DELETE_LINEA);
            db.closeConnection();
            JOptionPane.showMessageDialog(null, "Línea eliminada correctamente.");   
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    
    /**
     * Método que modifica la línea recibida como parámetro
     * 
     * @param linea 
     */
    public void updateCliente(Linea linea) {
        String idTicket = "idTicket = \"" + linea.getIdTicket() + "\", ";
        String idProducto = "idProducto = \"" + linea.getIdProducto() + "\", ";
        String unidades = "unidades = \"" + linea.getUnidades() + "\", ";
        String precioUnitario = "precioUnitario = \"" + linea.getPrecioUnitario() + "\", ";
        String descuento = "descuento = " + linea.getDescuento();
        String SQL_UPDATE_LINEA;
        SQL_UPDATE_LINEA = "UPDATE lineas SET "+ idTicket + idProducto + unidades + precioUnitario + descuento +" WHERE id="+ linea.getId();
        try {
            GestionSql db = new GestionSql();
            Statement stmt = db.conn.createStatement();
            stmt.executeUpdate(SQL_UPDATE_LINEA);
            db.closeConnection();
            JOptionPane.showMessageDialog(null, "Linea modificada correctamente.");   
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    
    /**
     * Método que genera una sentencia SQL precompilada
     * 
     * @param id
     * @param idTicket
     * @param idProducto
     * @param unidades
     * @param precioUnitario
     * @param descuento
     * @param db 
     */
    public void insertaPreparedStatement(int id, int idTicket, int idProducto, int unidades, float precioUnitario, float descuento, GestionSql db) {
        try {
        PreparedStatement ps = db.conn.prepareStatement("INSERT INTO lineas VALUES (?,?,?,?,?,?)");
        ps.setInt(1, id);
        ps.setInt(2, idTicket);
        ps.setInt(3, idProducto);
        ps.setInt(4, unidades);
        ps.setFloat(5, precioUnitario);
        ps.setFloat(6,descuento);
        ps.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
       }
    }
    
}

