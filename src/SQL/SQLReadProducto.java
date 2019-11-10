package SQL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Producto;
import resources.RecursosStatics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLReadProducto {

    PreparedStatement ps;
    String query="";
    ResultSet rs;
    boolean key;

    public boolean anadirProducto(Producto producto){
        int affectedRows = 0;
        query = "INSERT INTO producto VALUES (?,?,?)";

        try {
            ps = RecursosStatics.connection.prepareStatement(query);
            ps.setInt(1,producto.getIdProducto());
            ps.setString(2,producto.getNombreProducto());
            ps.setFloat(3,producto.getPrecio());
            affectedRows = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public boolean borrarProducto(int IDProducto){
        query = "DELETE FROM producto WHERE producto.IdProducto = " + IDProducto;

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



    public ObservableList<Producto> getProductos()
    {
        ObservableList<Producto> listProductos =  FXCollections.observableArrayList();
        query="SELECT * from producto ORDER by IdProducto ASC";
        String direccion;
        try
        {
            Producto producto;
            ps = RecursosStatics.connection.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                producto = new Producto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3)
                );
                listProductos.add(producto);


            }

            ps.close();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            System.out.println("error :V");
        }

        return listProductos;
    }

    public int getLastIndexTable (){
        query = " SELECT producto.IdProducto FROM producto ORDER by IdProducto DESC LIMIT 1";
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
