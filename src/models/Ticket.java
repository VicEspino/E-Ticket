package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Time;

public class Ticket extends RecursiveTreeObject<Ticket> {

    private int idTicket;
    private int idCliente;
    private Date fecha;
    private Time hora;
    private float totalTicket;
    ObservableList<ResumenArticulo> listProductosComprados;

    public Ticket(int idTicket, int idCliente, Date fecha, Time hora, float totalTicket, ObservableList<ResumenArticulo> listProductosComprados) {
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

    public String getFecha() {
        return fecha.toString();
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora.toString();
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

    public ObservableList<ResumenArticulo> getListProductosComprados() {
        return listProductosComprados;
    }

    public void setListProductosComprados(ObservableList<ResumenArticulo> listProductosComprados) {
        this.listProductosComprados = listProductosComprados;
    }
}
