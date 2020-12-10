/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seas.multinacional.controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import seas.multinacional.gestionbd.GestionSql;
import seas.multinacional.modelo.Cliente;
import seas.multinacional.vistas.PanelAltaClientes;
import seas.multinacional.vistas.PanelBajaClientes;
import seas.multinacional.vistas.PanelEditaClientes;
import seas.multinacional.vistas.PanelListarClientes;

/**
 * Clase que realiza las acciones asociadas al panel de alta, de edición, de eliminación y de listar de los clientes.
 * 
 * @author maria
 */
public class ServiceCliente {

    
    private PanelAltaClientes panel;
    private PanelEditaClientes panelEdita;
    private PanelBajaClientes panelBaja;
    private PanelListarClientes panelListar;
    ArrayList posIdCliente = new ArrayList();
    private List clientes = new ArrayList();


    public ArrayList getPosIdCliente() {
        return posIdCliente;
    }

    public ServiceCliente() {
    }

    /**
     * 
     * @param posIdCliente 
     */
    public void setPosIdCliente(ArrayList posIdCliente) {
        this.posIdCliente = posIdCliente;
    }
    
    /**
     * Constructor de la clase.
     *
     * @param panel PanelAltaClientes del que deberá manejar los eventos.
     */
    public ServiceCliente(PanelAltaClientes panel) {
        this.panel = panel;
    }
    
    /**
     * Constructor de la clase.
     *
     * @param panelEdita PanelEditaClientes del que deberá manejar los eventos.
     */
    public ServiceCliente(PanelEditaClientes panelEdita) {
        this.panelEdita = panelEdita;
    }
    
        /**
     * Constructor de la clase.
     *
     * @param panelBaja PanelBajaClientes del que deberá manejar los eventos.
     */
    public ServiceCliente(PanelBajaClientes panelBaja) {
        this.panelBaja = panelBaja;
    }
    
     /**
     * Constructor de la clase.
     *
     * @param panelListar PanelListarClientes del que deberá manejar los eventos.
     */
    public ServiceCliente(PanelListarClientes panelListar) {
        this.panelListar = panelListar;
    }
    
    /**
     * Guarda en la base de datos el Cliente con los datos ingresados como parámetros
     * 
     * @param nombre del cliente
     * @param apellido1 del cliente
     * @param apellido2 del cliente
     * @param clave del cliente
     * @param i del cliente
     */
    public void saveCliente(String nombre, String apellido1, String apellido2, String clave, int i) {
        GestionSql db = new GestionSql();
        int id = db.siguienteId("clientes");
        insertaPreparedStatement(id, nombre, apellido1, apellido2, clave, 0, db);
        db.closeConnection();
    }

     /**
     * Método que elimina el cliente seleccionado de la lista de clientes
     *
     * @param id indice del elemento de la lista de clientes seleccionado
     */  
    public void deleteCliente(int id) {
        String SQL_DELETE_CLIENTE = "DELETE FROM clientes WHERE id = " + id;
         try {
            GestionSql db = new GestionSql();
            Statement stmt = db.conn.createStatement();
            stmt.executeUpdate(SQL_DELETE_CLIENTE);
            db.closeConnection();
            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");   
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }
    
    /**
     * Método que modifica el cliente
     * 
     * @param cliente 
     */
    public void updateCliente(Cliente cliente) {
        if(!cliente.getNombre().equals("") && !cliente.getApellido1().equals("")){
            String nombre = "nombre = \"" + cliente.getNombre() + "\", ";
            String apellido1 = "apellido1 = \"" + cliente.getApellido1() + "\", ";
            String apellido2 = "apellido2 = \"" + cliente.getApellido2() + "\", ";
            String clave = "clave = \"" + cliente.getClave() + "\", ";
            String descuento = "descuento = " + cliente.getDescuento();
            String SQL_UPDATE_CLIENTE;
            SQL_UPDATE_CLIENTE = "UPDATE clientes SET "+ nombre + apellido1 + apellido2 + clave + descuento +" WHERE id="+ cliente.getId();
             try {
                GestionSql db = new GestionSql();
                Statement stmt = db.conn.createStatement();
                stmt.executeUpdate(SQL_UPDATE_CLIENTE);
                db.closeConnection();
                JOptionPane.showMessageDialog(null, "Cliente modificado correctamente.");   
            }catch(SQLException e){
                e.printStackTrace();
            } 
        } else {
            JOptionPane.showMessageDialog(null, "No puedes dejar vacio el campo de nombre o el primer apellido");  
        }   
    }

    public void getCliente() {

    }

    /**
     * Método que genera una sentencia SQL precompilada
     * 
     * @param id
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param clave
     * @param descuento
     * @param db 
     */
    public void insertaPreparedStatement(int id, String nombre, String apellido1, String apellido2, String clave, float descuento, GestionSql db) {
        try {
        PreparedStatement ps = db.conn.prepareStatement("INSERT INTO clientes VALUES (?,?,?,?,?,?)");
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setString(3, apellido1);
        ps.setString(4, apellido2);
        ps.setString(5, clave);
        ps.setFloat(6,descuento);
        ps.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
       }
    }
    
     /**
     * Método que devuelve una lista de los clientes existentes en la bbdd sin su clave
     *
     * @return ListModel de todos los clientes de la base de datos
     */  
    public ListModel getClientesList() {
        DefaultListModel listModel = new DefaultListModel();
        String SQL_SELECT_CLIENTES ="SELECT id, nombre, apellido1, apellido2 FROM clientes";
        try {
           GestionSql db = new GestionSql();
           Statement stmt = db.conn.createStatement();
           ResultSet rs = stmt.executeQuery(SQL_SELECT_CLIENTES);
           int i = 0;
           while(rs.next()){
                posIdCliente.add(rs.getInt(1));
                String aux = rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4);
                listModel.add(i, aux);
                i++;
           }
           db.closeConnection();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listModel;
    }
    
     /**
     * Método que rellena los cuadros de texto conn el cliente seleccionado de la lista.
     *
     * @param index indice del cliente seleccionado de la lista.
     * @return Cliente seleccionado de la lista.
     */    
    public Cliente setClienteText(int index) {
         Cliente cliente = new Cliente(0,"","","","",0);
         String SQL_SELECT_CLIENTE ="SELECT id, nombre, apellido1, apellido2, clave, descuento FROM clientes WHERE id=" + posIdCliente.get(index);
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
        if(cliente.getId()!=0) {
            return cliente;
        } else {
            return null;
        }
    }
    
     /**
     * Método que devuelve el id del cliente seleccionado de la lista.
     *
     * @param index indice del cliente seleccionado de la lista.
     * @return int id del cliente seleccionado de la lista.
     */
    public int getIdSelected(int index) {
        return (int) posIdCliente.get(index);
    }
    
    /**
    * Método que rellena la lista de clientes en orden alfabético
    *
    * @return ListModel de todos los clientes de la base de datos
    */  
    public ListModel setClientesInList() {
        DefaultListModel listModel = new DefaultListModel();
        String SQL_SELECT_CLIENTES ="SELECT id, nombre, apellido1, apellido2, clave, descuento FROM clientes";
        try {
            
            GestionSql db = new GestionSql();
            Statement stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_CLIENTES);
            while(rs.next()) {
                String aux = rs.getString(3) + " " + rs.getString(4) + ", " + rs.getString(2);
                clientes.add(aux);
            }
            Collections.sort(clientes);
            // Obtenemos un Iterador y recorremos la lista.
            Iterator iter = clientes.iterator();
            int i = 0;
            while (iter.hasNext()) {
                listModel.add(i, iter.next());
                i++;
            }
            db.closeConnection();
        }catch(SQLException e){
             e.printStackTrace();
        }
        return listModel;
    }
  


}
