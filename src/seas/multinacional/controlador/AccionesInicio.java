/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seas.multinacional.controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import seas.multinacional.gestionbd.GestionSql;
import seas.multinacional.modelo.Cliente;
import seas.multinacional.vistas.PanelInicio;
import seas.multinacional.controlador.ServiceCliente;

/**
 *
 * @author maria
 */
public class AccionesInicio {
 
    private final PanelInicio panel;
    private Cliente cliente = new Cliente();

   /**
     * Constructor de la clase.
     *
     * @param panel PanelInicio del que deberá manejar los eventos.
     */
    public AccionesInicio(PanelInicio panel) {
        this.panel = panel;
    }
    
    public int setTicket() {
        GestionSql db = new GestionSql();
        int ticketId = db.siguienteId("tickets");
        return ticketId;
    }
    
    /**
     * Método que devuelve un array con todos los vendedores de la bbdd
     * 
     * @return String[] Array con todos los vendedores de la bbdd
     */
    
    public String[] getVendedores() {
            List vendedores = new ArrayList();
            String SQL_SELECT_VENDEDORES ="SELECT id, nombre FROM vendedores";
            try {
               GestionSql db = new GestionSql();
               Statement stmt = db.conn.createStatement();
               ResultSet rs = stmt.executeQuery(SQL_SELECT_VENDEDORES);
               while(rs.next()){
                    String aux = rs.getInt(1) + ". " + rs.getString(2);
                    vendedores.add(aux);
               }
               db.closeConnection();
               Collections.sort(vendedores);
            }catch(SQLException e){
                e.printStackTrace();
            }
            String[] vendedoresArray = new String[vendedores.size()];
            vendedoresArray = (String[]) vendedores.toArray(vendedoresArray);
            return vendedoresArray;
      }
    
    public String[] getClientes() {
            List clientes = new ArrayList();
            String SQL_SELECT_CLIENTES ="SELECT id, nombre, apellido1, apellido2 FROM clientes";
            try {
               GestionSql db = new GestionSql();
               Statement stmt = db.conn.createStatement();
               ResultSet rs = stmt.executeQuery(SQL_SELECT_CLIENTES);
               while(rs.next()){
                    String aux = rs.getString(3) + " " + rs.getString(4) + ", " + rs.getString(2) + " (id: " + rs.getInt(1) + ")";
                    clientes.add(aux);
               }
               db.closeConnection();
               Collections.sort(clientes);
            }catch(SQLException e){
                e.printStackTrace();
            }
            String[] clientesArray = new String[clientes.size()];
            clientesArray = (String[]) clientes.toArray(clientesArray);
            return clientesArray;
      }
    


    public String[] setClientInTextBoxes(String inputValue) {
        String nombre, apellidos, id;
        String[] text = new String[2];
   
        String[] parts = inputValue.split(",");
        String[] parts2 = parts[1].split("\\(");
        id = parts2[1].substring(4);
        
        id = id.substring(0, id.length() - 1);
        nombre = parts2[0].trim();
        apellidos = parts[0];
        
        text[0]=id;
        text[1]= nombre + " " + apellidos;
        return text;
    }
    
    public Cliente getClienteById(int id) {
        String SQL_SELECT_CLIENTE ="SELECT id, nombre, apellido1, apellido2, clave, descuento FROM clientes"+" WHERE id="+ id;
        try {
            GestionSql db = new GestionSql();
            Statement stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_CLIENTE);
            while(rs.next()){
                cliente.setId(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellido1(rs.getString(3));
                cliente.setApellido2(rs.getString(4));
                cliente.setClave(rs.getString(5));
                cliente.setDescuento(rs.getFloat(6));
            }
            db.closeConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }
    
    public void updateCliente(Cliente cliente) {
        ServiceCliente accion = new ServiceCliente();
        accion.updateCliente(cliente);
    }

    public void cobrarPedido(boolean descuentoAplicado, float precioTotal) {
        if (descuentoAplicado == true) {
            cliente.setDescuento(0);
            updateCliente(cliente);
        } else {
            int cont = 0;
            while(precioTotal > 10){
                precioTotal=precioTotal-10;
                cont++;
            }
            if (cont != 0) {
                if ((float) (cliente.getDescuento()+cont*0.05) <= 0.5) {
                    cliente.setDescuento((float) (cliente.getDescuento()+cont*0.05));
                } else {
                    cliente.setDescuento((float) 0.5);
                }
                updateCliente(cliente);
            }
        }
    }
    
    
}
