package ots.modells;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Ticket implements Serializable {

    private int idTicket;
    private int idCliente;
    private Date fecha;
    private Time hora;
    private float totalTicket;
    private ArrayList<ResumenArticulo> listProductosComprados;

    public Ticket(int idTicket, int idCliente, Date fecha, Time hora, float totalTicket, ArrayList<ResumenArticulo> listProductosComprados) {
        this.idTicket = idTicket;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.hora = hora;
        this.totalTicket = totalTicket;
        this.listProductosComprados = listProductosComprados;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public float getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(float totalTicket) {
        this.totalTicket = totalTicket;
    }

    public ArrayList<ResumenArticulo> getListProductosComprados() {
        return listProductosComprados;
    }

    public void setListProductosComprados(ArrayList<ResumenArticulo> listProductosComprados) {
        this.listProductosComprados = listProductosComprados;
    }
}
