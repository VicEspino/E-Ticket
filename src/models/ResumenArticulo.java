package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class ResumenArticulo extends RecursiveTreeObject<ResumenArticulo> {

    private int idArticulo;
    private String nombreArticulo;
    private int cantidad;
    private float precioIndividual;
    private float totalTicket;


    public ResumenArticulo(int idArticulo, String nombreArticulo, int cantidad, float precioIndividual, float totalTicket) {
        this.idArticulo = idArticulo;
        this.nombreArticulo = nombreArticulo;
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

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
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
