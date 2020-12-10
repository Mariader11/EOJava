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
import seas.multinacional.modelo.Vendedor;
import seas.multinacional.vistas.PanelAltaVendedores;
import seas.multinacional.vistas.PanelBajaVendedores;
import seas.multinacional.vistas.PanelEditaVendedores;
import seas.multinacional.vistas.PanelListarVendedores;

/**
 * Clase que realiza las acciones asociadas al panel de alta, de edición, de eliminación y de listar de los clientes.
 * 
 * @author maria
 */
public class ServiceVendedor {

    
    private PanelAltaVendedores panel;
    private PanelEditaVendedores panelEdita;
    private PanelBajaVendedores panelBaja;
    private PanelListarVendedores panelListar;
    ArrayList posIdVendedor = new ArrayList();
    private List vendedores = new ArrayList();


    public ArrayList getPosIdVendedor() {
        return posIdVendedor;
    }

    /**
     * 
     * @param posIdVendedor 
     */
    public void setPosIdVendedor(ArrayList posIdVendedor) {
        this.posIdVendedor = posIdVendedor;
    }
    
    /**
     * Constructor de la clase.
     *
     * @param panel PanelAltaVendedores del que deberá manejar los eventos.
     */
    public ServiceVendedor(PanelAltaVendedores panel) {
        this.panel = panel;
    }
    
    /**
     * Constructor de la clase.
     *
     * @param panelEdita PanelEditaVendedores del que deberá manejar los eventos.
     */
    public ServiceVendedor(PanelEditaVendedores panelEdita) {
        this.panelEdita = panelEdita;
    }
    
        /**
     * Constructor de la clase.
     *
     * @param panelBaja PanelBajaVendedores del que deberá manejar los eventos.
     */
    public ServiceVendedor(PanelBajaVendedores panelBaja) {
        this.panelBaja = panelBaja;
    }
    
     /**
     * Constructor de la clase.
     *
     * @param panelListar PanelListarVendedores del que deberá manejar los eventos.
     */
    public ServiceVendedor(PanelListarVendedores panelListar) {
        this.panelListar = panelListar;
    }
    
    
     /**
     * Método que recupera los valores del formulario y los guarda en la
     * estructura de almacenamiento propuesta y existente en el frame.
     */
    public void saveVendedor() {
        if(!panel.getTxtNombre().getText().isEmpty() && !panel.getTxtApellido1().getText().isEmpty()){
            String nombre = panel.getTxtNombre().getText().trim(); 
            String apellido1 = panel.getTxtApellido1().getText().trim();
            String apellido2 = panel.getTxtApellido2().getText().trim();
            String clave = panel.getTxtClave().getText().trim();
            GestionSql db = new GestionSql();
            int id = db.siguienteId("vendedor");
            insertaPreparedStatement(id, nombre, apellido1, apellido2, clave, db);
            db.closeConnection();
            panel.getTxtNombre().setText("");
            panel.getTxtApellido1().setText("");
            panel.getTxtApellido2().setText("");
            panel.getTxtClave().setText("");
            JOptionPane.showMessageDialog(null, "Datos ingresados correctamente.");    
        }   else {
            JOptionPane.showMessageDialog(null, "No puedes dejar vacío el campo de nombre o el primer apellido");
        }
    }

     /**
     * Método que elimina el vendedor seleccionado de la lista de vendedores
     *
     * @param id indice del elemento de la lista de vendedores seleccionado
     */  
    public void deleteVendedor(int id) {
        String SQL_DELETE_VENDEDOR = "DELETE FROM vendedores WHERE id = " + id;
         try {
            GestionSql db = new GestionSql();
            Statement stmt = db.conn.createStatement();
            stmt.executeUpdate(SQL_DELETE_VENDEDOR);
            db.closeConnection();
            JOptionPane.showMessageDialog(null, "Vendedor eliminado correctamente.");   
        }catch(SQLException e){
            e.printStackTrace();
        } 
    }

     /**
     * Método que modifica el vendedor seleccionado de la lista.
     *
     * @param vendedor vendedor ya modificado.
     */
    public void updateVendedor(Vendedor vendedor) {
        if(!vendedor.getNombre().equals("") && !vendedor.getApellido1().equals("")){
            String nombre = "nombre = \"" + vendedor.getNombre() + "\", ";
            String apellido1 = "apellido1 = \"" + vendedor.getApellido1() + "\", ";
            String apellido2 = "apellido2 = \"" + vendedor.getApellido2() + "\", ";
            String clave = "clave = \"" + vendedor.getClave() + "\"";
            String SQL_UPDATE_VENDEDOR;
            SQL_UPDATE_VENDEDOR = "UPDATE vendedores SET "+ nombre + apellido1 + apellido2 + clave +" WHERE id="+ vendedor.getId();
             try {
                GestionSql db = new GestionSql();
                Statement stmt = db.conn.createStatement();
                stmt.executeUpdate(SQL_UPDATE_VENDEDOR);
                db.closeConnection();
                JOptionPane.showMessageDialog(null, "Vendedor modificado correctamente.");   
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
     * @param db 
     */
    public void insertaPreparedStatement(int id, String nombre, String apellido1, String apellido2, String clave, GestionSql db) {
        try {
        PreparedStatement ps = db.conn.prepareStatement("INSERT INTO vendedores VALUES (?,?,?,?,?)");
        ps.setInt(1, id);
        ps.setString(2, nombre);
        ps.setString(3, apellido1);
        ps.setString(4, apellido2);
        ps.setString(5, clave);
        ps.executeUpdate();
        } catch (SQLException e) {
        e.printStackTrace();
       }
    }
    
     /**
     * Método que devuelve una lista de los vendedores existentes en la bbdd sin su clave
     *
     * @return ListModel de todos los vendedores de la base de datos
     */  
    public ListModel getVendedoresList() {
        DefaultListModel listModel = new DefaultListModel();
        String SQL_SELECT_VENDEDORES ="SELECT id, nombre, apellido1, apellido2 FROM vendedores";
        try {
           GestionSql db = new GestionSql();
           Statement stmt = db.conn.createStatement();
           ResultSet rs = stmt.executeQuery(SQL_SELECT_VENDEDORES);
           int i = 0;
           while(rs.next()){
                posIdVendedor.add(rs.getInt(1));
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
     * Método que rellena los cuadros de texto con el vendedor seleccionado de la lista.
     *
     * @param index indice del vendedor seleccionado de la lista.
     * @return Vendedor seleccionado de la lista.
     */    
    public Vendedor setVendedorText(int index) {
         Vendedor vendedor = new Vendedor(0,"","","","");
         String SQL_SELECT_VENDEDOR ="SELECT id, nombre, apellido1, apellido2, clave FROM vendedores WHERE id=" + posIdVendedor.get(index);
         try {
         GestionSql db = new GestionSql();
         Statement stmt = db.conn.createStatement();
         ResultSet rs = stmt.executeQuery(SQL_SELECT_VENDEDOR);
         while(rs.next()){
         vendedor.setId(rs.getInt(1));
         vendedor.setNombre(rs.getString(2));
         vendedor.setApellido1(rs.getString(3));
         vendedor.setApellido2(rs.getString(4));
         vendedor.setClave(rs.getString(5));
         }
         db.closeConnection();
        }catch(SQLException e){
            e.printStackTrace();
        } 
        if(vendedor.getId()!=0) {
            return vendedor;
        } else {
            return null;
        }
    }
    
     /**
     * Método que devuelve el id del vendedor seleccionado de la lista.
     *
     * @param index indice del vendedor seleccionado de la lista.
     * @return int id del vendedor seleccionado de la lista.
     */
    public int getIdSelected(int index) {
        return (int) posIdVendedor.get(index);
    }
    
    /**
    * Método que rellena la lista de vendedores en orden alfabético
    *
    * @return ListModel de todos los vendedores de la base de datos
    */  
    public ListModel setVendedoresInList() {
        DefaultListModel listModel = new DefaultListModel();
        String SQL_SELECT_VENDEDORES ="SELECT id, nombre, apellido1, apellido2, clave FROM vendedores";
        try {
            GestionSql db = new GestionSql();
            Statement stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECT_VENDEDORES);
            while(rs.next()) {
                String aux = rs.getString(3) + " " + rs.getString(4) + ", " + rs.getString(2) + " (id: " + rs.getInt(1) + ")";
                vendedores.add(aux);
            }
            Collections.sort(vendedores);
            // Obtenemos un Iterador y recorremos la lista.
            Iterator iter = vendedores.iterator();
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
