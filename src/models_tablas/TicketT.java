package models_tablas;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import models.Ticket;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class TicketT extends RecursiveTreeObject<TicketT> {

    private Ticket ticket;

    public TicketT(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getIdTicket() {
        return this.ticket.getIdTicket();
    }

    public void setIdTicket(int idTicket) {
        this.ticket.setIdTicket(idTicket);
    }

    public int getIdCliente() {
        return this.ticket.getIdCliente();
    }

    public void setIdCliente(int idCliente) {
        this.ticket.setIdCliente(idCliente);
    }

    public Date getFecha() {
        return this.ticket.getFecha();
    }

    public void setFecha(Date fecha) {
        this.ticket.setFecha(fecha);
    }

    public Time getHora() {
        return this.ticket.getHora();
    }

    public void setHora(Time hora) {
        this.ticket.setHora(hora);
    }

    public float getTotalTicket() {
        return this.ticket.getTotalTicket();
    }

    public void setTotalTicket(float totalTicket) {
        this.ticket.setTotalTicket(totalTicket);
    }

    public ArrayList<ResumenArticuloT> getListProductosComprados() {
        return this.ticket.getListProductosComprados();
    }

    public void setListProductosComprados(ArrayList<ResumenArticuloT> listProductosComprados) {
        this.ticket.setListProductosComprados(listProductosComprados);
    }
}
