package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class ResumenArticulo extends RecursiveTreeObject<ResumenArticulo> {


    private int cantidad;
    private float totalProducto;

    public ResumenArticulo(int cantidad, float totalProducto) {
        this.cantidad = cantidad;
        this.totalProducto = totalProducto;
    }

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
}
