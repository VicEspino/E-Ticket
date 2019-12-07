package SQLServer;

import models.Producto;
import models_tablas.ProductoT;
import resources.RecursosStatics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLServerProducto {

    PreparedStatement ps;
    String query = "";
    ResultSet rs;
    boolean key;

    public boolean anadirProducto(ProductoT productoT) {
        int affectedRows = 0;
        query = "INSERT INTO producto  VALUES (?,?,?)";
       /* query = "insert into Producto (IdProducto,nombre,precio) " +
                "VALUES(" +
                "   (SELECT ISNULL (MAX(IdProducto)+1,0) FROM Producto WITH(SERIALIZABLE, UPDLOCK) )" +
                "   , ?, ?)";
        */
        Producto producto = productoT.getProducto();

        try {
            ps = RecursosStatics.connection.prepareStatement(query);
            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombreProducto());
            ps.setFloat(3, producto.getPrecio());
            affectedRows = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean borrarProducto(int IDProducto) {
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


    public ArrayList<ProductoT> getProductos() {
        ArrayList<ProductoT> listProductos = new ArrayList<>();// FXCollections.observableArrayList();
        query = "SELECT * from producto ORDER by IdProducto ASC";
        String direccion;
        try {
            Producto producto;
            ps = RecursosStatics.connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto = new Producto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3)
                );
                listProductos.add(new ProductoT(producto));


            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error :V");
        }

        return listProductos;
    }

    public int getLastIndexTable() {
        query = "SELECT ISNULL(MAX(producto.IdProducto),0) FROM dbo.Producto WITH(SERIALIZABLE, UPDLOCK)";
        try {
            ps = RecursosStatics.connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error :V");
        }
        return -1;
    }


}
