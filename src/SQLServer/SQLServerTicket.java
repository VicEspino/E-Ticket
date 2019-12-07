package SQLServer;

import models.Producto;
import models.ResumenArticulo;
import models.Ticket;
import models_tablas.ResumenArticuloT;
import models_tablas.TicketT;
import resources.RecursosStatics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLServerTicket {

    private PreparedStatement ps;
    private String query = "";
    private ResultSet rs;
    private boolean key;

    public boolean anadirTicket(Ticket ticket) {
        int affectedRows = 0;
        //query = "INSERT INTO compra VALUES (null,?,?,?,?)";
        int ultimoIndex = getLasIndexTicket();

        query = "INSERT INTO compra VALUES (?,?,?,?,?)";
        try {
            ps = RecursosStatics.connection.prepareStatement(query);
            ps.setInt(1, ultimoIndex + 1);
            ps.setDate(2, ticket.getFecha());
            ps.setTime(3, ticket.getHora());
            ps.setFloat(4, ticket.getTotalTicket());
            ps.setInt(5, ticket.getIdCliente());


            affectedRows = ps.executeUpdate();
            ps.close();
            //last_insert_id()
            query = "INSERT INTO compra_has_producto VALUES (?,?,?,?)";
            for (ResumenArticulo resumenArticulo : ticket.getListProductosComprados()) {
                ps = RecursosStatics.connection.prepareStatement(query);

                ps.setInt(1, resumenArticulo.getIdTicket());
                ps.setInt(2, resumenArticulo.getIDArticulo());
                ps.setInt(3, resumenArticulo.getCantidad());
                ps.setFloat(4, resumenArticulo.getTotalProducto());
                ps.executeUpdate();
            }
            ps.close();


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }

    public ArrayList<TicketT> listTickets() {

        ArrayList<TicketT> listaTickets = new ArrayList<>();// FXCollections.observableArrayList();


        try {
            query = "SELECT * FROM compra";
            ps = RecursosStatics.connection.prepareStatement(query);
            rs = ps.executeQuery();
            Ticket ticketDB;

            while (rs.next()) {
                ticketDB = new Ticket(
                        rs.getInt(1),
                        rs.getInt(5),
                        rs.getDate(2),
                        rs.getTime(3),
                        rs.getFloat(4),
                        null
                );
                listaTickets.add(new TicketT(ticketDB));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        ArrayList<ResumenArticuloT> listaResumenArticulo;

        try {
            query = "SELECT * FROM compra_has_producto,producto WHERE compra_has_producto.IdProducto = producto.IdProducto AND IdCompra =? ";
            ResumenArticulo resumenArticulo;
            for (TicketT ticketActualDB : listaTickets) {
                //se hará la consulta segun el numero de ticket existentes
                ps = RecursosStatics.connection.prepareStatement(query);
                ps.setInt(1, ticketActualDB.getIdTicket());
                rs = ps.executeQuery();

                //listaResumenArticulo = FXCollections.observableArrayList();
                listaResumenArticulo = new ArrayList<>();
                //lee los N articulos
                while (rs.next()) {

                    resumenArticulo = new ResumenArticulo(
                            rs.getInt(1),
                            new Producto(rs.getInt(5), rs.getString(6), rs.getInt(7)),
                            rs.getInt(3)
                    );
                    listaResumenArticulo.add(new ResumenArticuloT(resumenArticulo));
                }
                //añade la lista con los articulos a ese ID de Ticket
                ticketActualDB.setListProductosComprados(listaResumenArticulo);

            }


            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error :V");
        }


        return listaTickets;
    }

    public int getLasIndexTicket() {

        //query = " SELECT compra.IdCompra FROM compra ORDER by IdCompra DESC LIMIT 1";
        query = "SELECT ISNULL(MAX(Compra.IdCompra),0) FROM dbo.Compra WITH(SERIALIZABLE, UPDLOCK)";
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
