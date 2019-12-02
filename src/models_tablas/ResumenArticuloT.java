package models_tablas;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import models.Producto;
import models.ResumenArticulo;

public class ResumenArticuloT extends RecursiveTreeObject<ResumenArticuloT> {

    ResumenArticulo resumenArticulo;

    public ResumenArticuloT(ResumenArticulo resumenArticulo) {
        this.resumenArticulo = resumenArticulo;
    }

    //el setCellFactory, llama al get correspondiente al String del nombre que se le dió
    public int getCantidad() {
        return this.resumenArticulo.getCantidad();
    }

    public void setCantidad(int cantidad) {
        this.resumenArticulo.setCantidad(cantidad);
    }

    public float getTotalProducto() {
        return this.resumenArticulo.getTotalProducto();
    }

    public void setTotalProducto(float totalProducto) {
        this.resumenArticulo.setTotalProducto(totalProducto);
    }

    public float getPrecioIndividualProducto(){
        return this.resumenArticulo.getProducto().getPrecio();
    }

    public String getNombreProducto(){
        return this.resumenArticulo.getProducto().getNombreProducto();
    }

    public int getIDArticulo(){
        return this.resumenArticulo.getProducto().getIdProducto();
    }

    public int getIdTicket() {
        return this.resumenArticulo.getIdTicket();
    }

    public void setIdTicket(int idTicket) {
        this.resumenArticulo.setIdTicket(idTicket);
    }

    public Producto getProducto() {
        return this.resumenArticulo.getProducto();
    }

    public void setProducto(Producto producto) {
        this.resumenArticulo.setProducto(producto);
    }
}
