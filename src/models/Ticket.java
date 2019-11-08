package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.sql.Date;
import java.sql.Time;

public class Ticket extends RecursiveTreeObject<Ticket> {

    private int idTicket;
    private int idCliente;
    private Date fecha;
    private Time hora;
    private float totalTicket;

    public Ticket(int idTicket, int idCliente, Date fecha, Time hora, float totalTicket) {
        this.idTicket = idTicket;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.hora = hora;
        this.totalTicket = totalTicket;
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
}
