/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seas.multinacional.controlador;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import javax.swing.JOptionPane;
import seas.multinacional.gestionbd.GestionSql;
import seas.multinacional.modelo.Ticket;

/**
 *
 * @author maria
 */
public class ServiceTicket {

    public ServiceTicket() {
    }
    
    /**
     * Guarda en la base de datos el Ticket con los datos ingresados como parámetros 
     * 
     * @param idVendedor
     * @param idCliente
     * @param precio
     * @param fecha
     * @return id
     */
    public int saveTicket(int idVendedor, int idCliente, float precio, Date fecha) {
        GestionSql db = new GestionSql();
        int id = db.siguienteId("tickets");
        insertaPreparedStatement(id, idVendedor, idCliente, precio, fecha, db);
        db.closeConnection();
        return id;
    }
    
    /**
     * Método que elimina el Ticket correspondiente al id pasado como parámetro
     *
     * @param id indice del elemento seleccionado de la lista de tickets
     */  
    public void deleteTicket(int id) {
        String SQL_DELETE_TICKET = "DELETE FROM tickets WHERE id = " + id;
         try {
            GestionSql db = new GestionSql();
            Statement stmt = db.conn.createStatement();
            stmt.executeUpdate(SQL_DELETE_TICKET);
            db.closeConnection();
            JOptionPane.showMessageDialog(null, "Ticket eliminada correctamente.");   
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    
     /**
     * Método que modifica el ticket recibido como parámetro
     * 
     * @param ticket 
     */
    public void updateTicket(Ticket ticket) {
        String idVendedor = "idVendedor = \"" + ticket.getIdVendedor() + "\", ";
        String idCliente = "idCliente = \"" + ticket.getIdCliente() + "\", ";
        String precio = "precio = \"" + ticket.getPrecio() + "\", ";
        String fecha = "fecha = " + ticket.getFecha();
        String SQL_UPDATE_TICKET;
        SQL_UPDATE_TICKET = "UPDATE tickets SET "+ idVendedor + idCliente + precio + fecha +" WHERE id="+ ticket.getId();
        try {
            GestionSql db = new GestionSql();
            Statement stmt = db.conn.createStatement();
            stmt.executeUpdate(SQL_UPDATE_TICKET);
            db.closeConnection();
            JOptionPane.showMessageDialog(null, "Ticket modificado correctamente.");   
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    
    
     /**
      * Método que genera una sentencia SQL precompilada
      * 
      * @param id
      * @param idVendedor
      * @param idCliente
      * @param precio
      * @param fecha
      * @param db 
      */
    public void insertaPreparedStatement(int id, int idVendedor, int idCliente, float precio, Date fecha, GestionSql db) {
        try {
        PreparedStatement ps = db.conn.prepareStatement("INSERT INTO tickets VALUES (?,?,?,?,?)");
        ps.setInt(1, id);
        ps.setInt(2, idVendedor);
        ps.setInt(3, idCliente);
        ps.setFloat(4, precio);
        ps.setDate(5, fecha);
        ps.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
       }
    } 
}
