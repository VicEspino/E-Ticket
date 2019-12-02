package models_tablas;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import models.Cliente;

public class ClienteT extends RecursiveTreeObject<ClienteT> {

    Cliente cliente;



    public ClienteT(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdCliente() {
        return this.cliente.getIdCliente();
    }

    public void setIdCliente(int idCliente) {
        this.cliente.setIdCliente(idCliente);
    }

    public String getNombre() {
        return this.cliente.getNombre();
    }

    public void setNombre(String nombre) {
        this.cliente.setNombre(nombre);
    }

    public String getEmail() {
        return cliente.getEmail();

    }

    public void setEmail(String email) {
        this.cliente.setEmail(email);

    }

    public String getContrasena() {
        return this.cliente.getContrasena();
    }

    public void setContrasena(String contrasena) {
        this.cliente.setContrasena(contrasena);
    }

}
