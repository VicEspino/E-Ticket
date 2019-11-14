package controllers;

import SQL.ConexionSQL;
import SQL.SQLProducto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableRow;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import interfaces.ITransferirObjeto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ResumenArticulo;
import models.Ticket;
import resources.RecursosStatics;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Principal implements Initializable {


    @FXML
    private JFXTreeTableView<Ticket> table_tickets;

    @FXML
    private TreeTableColumn<Ticket, String> column_noTicket;

    @FXML
    private TreeTableColumn<Ticket, String> column_IDCliente;

    @FXML
    private TreeTableColumn<Ticket, String> column_Fecha;

    @FXML
    private TreeTableColumn<Ticket, String> column_hora;

    @FXML
    private TreeTableColumn<Ticket, String> column_total;

    @FXML
    private JFXTreeTableView<ResumenArticulo> table_resumen_ticket;

    @FXML
    private TreeTableColumn<ResumenArticulo, String> column_IDArticulo;

    @FXML
    private TreeTableColumn<ResumenArticulo, String> column_nombre;

    @FXML
    private TreeTableColumn<ResumenArticulo, String> column_cantidad;

    @FXML
    private TreeTableColumn<ResumenArticulo, String> column_individual;

    @FXML
    private TreeTableColumn<ResumenArticulo, String> column_total_articulo;

    @FXML
    private JFXButton btn_Config;

    @FXML
    private JFXButton btn_Add;

    @FXML
    private JFXButton btn_Cancel;

    @FXML
    private JFXButton btn_Productos;

    @FXML
    private JFXButton btn_Clientes;

    private ConexionSQL conexionSQL = new ConexionSQL();

    private final ObservableList<Ticket> listaTickets = FXCollections.observableArrayList();
    private final ObservableList<ResumenArticulo> listaArticulos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //si da null, controlar.

        RecursosStatics.connection = conexionSQL.getConexion();

        column_noTicket.setCellValueFactory(new TreeItemPropertyValueFactory<>("IdTicket"));
        column_IDCliente.setCellValueFactory(new TreeItemPropertyValueFactory<>("IdCliente"));
        column_Fecha.setCellValueFactory(new TreeItemPropertyValueFactory<>("fecha"));
        column_hora.setCellValueFactory(new TreeItemPropertyValueFactory<>("hora"));
        column_total.setCellValueFactory(new TreeItemPropertyValueFactory<>("totalTicket"));


        //Ticket ticket = new Ticket(1,12,new Date(156189), new Time(12315), 485.12f);
        //listaTickets.add(ticket);
        TreeItem<Ticket> root = new RecursiveTreeItem<>(listaTickets, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_tickets.setRoot(root);
        table_tickets.setShowRoot(false);



        column_IDArticulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("IDArticulo"));
        column_cantidad.setCellValueFactory(new TreeItemPropertyValueFactory<>("cantidad"));
        column_nombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("NombreProducto"));
        column_individual.setCellValueFactory(new TreeItemPropertyValueFactory<>("PrecioIndividualProducto"));
        column_total_articulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("TotalProducto"));


        TreeItem<ResumenArticulo> root2 = new RecursiveTreeItem<>(listaArticulos, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_resumen_ticket.setRoot(root2);
        table_resumen_ticket.setShowRoot(false);



        table_tickets.setRowFactory((param -> {

            JFXTreeTableRow<Ticket> row = new JFXTreeTableRow<>();

            row.setOnMouseClicked(event -> {
                if(! row.isEmpty() && event.getButton()== MouseButton.PRIMARY /*&& event.getClickCount() == 2*/) {
                    TreeItem<ResumenArticulo> raizRow = new RecursiveTreeItem<>(row.getTreeItem().getValue().getListProductosComprados(), (recursiveTreeObject) -> recursiveTreeObject.getChildren());
                    table_resumen_ticket.setRoot(raizRow);
                }
            });

            return row;
        }

        ));


    }

    @FXML
    void btnAdd_OnAction(ActionEvent event) {
        Stage secundary = new Stage();
        Parent root ;
//no puedes obetenr el controller (creo).
           /* root = FXMLLoader.load(getClass().getResource("/views/AddTicket_ventana.fxml"));
            secundary.setTitle("Nueva venta");
            secundary.setScene(new Scene(root));
            secundary.initOwner(this.btn_Add.getScene().getWindow());
            secundary.initModality(Modality.WINDOW_MODAL);
            secundary.resizableProperty().set(false);
            secundary.show();
*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddTicket_ventana.fxml"));
        try {
            AnchorPane vent = loader.load();
            secundary.setScene(new Scene(vent));
            controllers.AddTicket controller = loader.getController();

            controller.setTransferirObjeto(new ITransferirObjeto() {
                @Override
                public void tranferirObjeto(int IDCompra,int IDCliente,float totalCompra,ObservableList<ResumenArticulo> listProductosComprados) {
                    Calendar calendario = Calendar.getInstance();


                    Ticket ticketNuevo = new Ticket(
                            IDCompra,IDCliente,
                            new Date(System.currentTimeMillis()),new Time(calendario.getTime().getTime()),
                            totalCompra,listProductosComprados);
                   // Ticket ticketw = new Ticket(981,589,new Date(156456), new Time(1315), 45.12f);
                    listaTickets.add(ticketNuevo);
                }
            });

            secundary.setTitle("Nueva venta");
            secundary.initOwner(this.btn_Add.getScene().getWindow());
            secundary.initModality(Modality.WINDOW_MODAL);
            secundary.resizableProperty().set(false);
            secundary.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void btnCancel_OnAction(ActionEvent event) {

    }

    @FXML
    void btnConfig_OnAction(ActionEvent event) {

    }

    @FXML
    void btnProductos_OnAction(ActionEvent event) {
        Stage secundary = new Stage();
        Parent root ;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Productos_ventana.fxml"));
        try {
            AnchorPane vent = loader.load();
            secundary.setScene(new Scene(vent));
            controllers.Productos controller = loader.getController();
            controller.setProductos(new SQLProducto().getProductos());

            secundary.setTitle("Productos");
            secundary.initOwner(this.btn_Add.getScene().getWindow());
            secundary.initModality(Modality.WINDOW_MODAL);
            secundary.resizableProperty().set(false);
            secundary.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void btnClientes_OnAction(ActionEvent event) {

        Stage secundary = new Stage();
        Parent root ;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Clientes_ventana.fxml"));
        try {
            AnchorPane vent = loader.load();
            secundary.setScene(new Scene(vent));
//            controllers.Productos controller = loader.getController();
  //          controller.setProductos(new SQLProducto().getProductos());

            secundary.setTitle("Productos");
            secundary.initOwner(this.btn_Add.getScene().getWindow());
            secundary.initModality(Modality.WINDOW_MODAL);
            secundary.resizableProperty().set(false);
            secundary.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}