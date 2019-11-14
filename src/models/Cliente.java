package models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Cliente extends RecursiveTreeObject<Cliente> {

    private int idCliente;
    private String nombre;
    private String email;
    private String contrasena;

    public Cliente(int idCliente, String nombre, String email, String contrasena) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
