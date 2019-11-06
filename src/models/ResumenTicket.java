package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class ResumenTicket extends RecursiveTreeObject<ResumenTicket> {

    int idArticulo;
    String nombre;
    int cantidad;
    float precioIndividual;
    float totalTicket;


    public ResumenTicket(int idArticulo, String nombre, int cantidad, float precioIndividual, float totalTicket) {
        this.idArticulo = idArticulo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioIndividual = precioIndividual;
        this.totalTicket = totalTicket;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioIndividual() {
        return precioIndividual;
    }

    public void setPrecioIndividual(float precioIndividual) {
        this.precioIndividual = precioIndividual;
    }

    public float getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(float totalTicket) {
        this.totalTicket = totalTicket;
    }
}
