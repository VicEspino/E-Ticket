package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
//Compra has Producto
public class ResumenArticulo extends RecursiveTreeObject<ResumenArticulo> {


    private Producto producto;
    private int idTicket;


    private int cantidad;
    private float totalProducto;

    public ResumenArticulo(int idTicket, Producto producto,int cantidad) {
        this.producto = producto;
        this.idTicket = idTicket;
        this.cantidad = cantidad;
        this.totalProducto = cantidad * getPrecioIndividualProducto();
    }

    //el setCellFactory, llama al get correspondiente al String del nombre que se le dió
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotalProducto() {
        return totalProducto;
    }

    public void setTotalProducto(float totalProducto) {
        this.totalProducto = totalProducto;
    }

    public float getPrecioIndividualProducto(){
        return this.producto.getPrecio();
    }

    public String getNombreProducto(){
        return this.producto.getNombreProducto();
    }

    public int getIDArticulo(){
        return this.producto.getIdProducto();
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
