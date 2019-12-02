package SQL;

import com.mysql.fabric.xmlrpc.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cliente;
import models.Producto;
import resources.RecursosStatics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLCliente {

    PreparedStatement ps;
    String query="";
    ResultSet rs;
    boolean key;

    public boolean anadirCliente(Cliente cliente ){
        int affectedRows = 0;
        query = "INSERT INTO cliente VALUES (?,?,?,?)";

        try {
            ps = RecursosStatics.connection.prepareStatement(query);
            ps.setInt(1,cliente.getIdCliente());
            ps.setString(2,cliente.getNombre());
            ps.setString(3,cliente.getEmail());
            ps.setString(4,cliente.getContrasena());


            affectedRows = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public boolean borrarProducto(int IdCliente){
        query = "DELETE FROM cliente WHERE cliente.IdCliente = " + IdCliente;

        try {
            ps = RecursosStatics.connection.prepareStatement(query);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }


    public ArrayList<Cliente> getClientes(){

        ArrayList<Cliente> listCliente =  new ArrayList<>();//FXCollections.observableArrayList();
        query="SELECT * from cliente ORDER by IdCliente ASC";
        String direccion;
        try
        {
            Cliente cliente;
            ps = RecursosStatics.connection.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                listCliente.add(cliente);


            }

            ps.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.out.println("error :V");
        }

        return listCliente;
    }

    public int getLastIndex() {

        query = " SELECT cliente.IdCliente FROM cliente ORDER by IdCliente DESC LIMIT 1";
        try
        {
            ps = RecursosStatics.connection.prepareStatement(query);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }

            ps.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.out.println("error :V");
        }

        return -1;
    }
}
