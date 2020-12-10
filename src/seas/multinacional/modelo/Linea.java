/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seas.multinacional.modelo;

/**
 *
 * @author maria
 */
public class Linea {
    private int id, idTicket, idProducto, unidades;
    private float precioUnitario, descuento;

    public Linea(int id, int idTicket, int idProducto, int unidades, float precioUnitario, float descuento) {
        this.id = id;
        this.idTicket = idTicket;
        this.idProducto = idProducto;
        this.unidades = unidades;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    
    
}
