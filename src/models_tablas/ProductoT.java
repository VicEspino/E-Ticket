package models_tablas;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import models.Producto;

/**
 * Las clases de este paquete con intermediarios con el model real, ya que para compartir
 * objetos model por socket, se necesitan los mismos tipos.
 * Y android no soporta RecursiveTreeObject (herencia nescesaria de las tablas).
 */
public class ProductoT extends RecursiveTreeObject<ProductoT> {

    Producto producto;

    public ProductoT(Producto producto) {
        this.producto = producto;
    }

    public int getIdProducto() {
        return this.producto.getIdProducto();
    }

    public void setIdProducto(int idProducto) {
        this.producto.setIdProducto(idProducto);
    }

    public String getNombreProducto() {
        return this.producto.getNombreProducto();
    }

    public void setNombreProducto(String nombreProducto) {
        this.producto.setNombreProducto(nombreProducto);
    }

    public float getPrecio() {
        return producto.getPrecio();
    }

    public void setPrecio(float precio) {
        this.producto.setPrecio(precio);
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
