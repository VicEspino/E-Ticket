package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.ObservableList;
import models_tablas.ResumenArticuloT;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Ticket {

    private int idTicket;
    private int idCliente;
    private Date fecha;
    private Time hora;
    private float totalTicket;
    private ArrayList<ResumenArticuloT> listProductosComprados;

    public Ticket(int idTicket, int idCliente, Date fecha, Time hora, float totalTicket, ArrayList<ResumenArticuloT> listProductosComprados) {
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

    public ArrayList<ResumenArticuloT> getListProductosComprados() {
        return listProductosComprados;
    }

    public void setListProductosComprados(ArrayList<ResumenArticuloT> listProductosComprados) {
        this.listProductosComprados = listProductosComprados;
    }
}
