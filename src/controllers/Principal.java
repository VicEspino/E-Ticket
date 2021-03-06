package controllers;

import ConexionRed.ConexionServidor;
import SQL.ConexionMySQL;
import SQL.SQLProducto;
import SQL.SQLTicket;
import SQLServer.ConexionSQLServer;
import SQLServer.SQLServerTicket;
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
import models_tablas.ResumenArticuloT;
import models_tablas.TicketT;
import resources.RecursosStatics;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Principal implements Initializable {


    @FXML
    private JFXTreeTableView<TicketT> table_tickets;

    @FXML
    private TreeTableColumn<TicketT, String> column_noTicket;

    @FXML
    private TreeTableColumn<TicketT, String> column_IDCliente;

    @FXML
    private TreeTableColumn<TicketT, String> column_Fecha;

    @FXML
    private TreeTableColumn<TicketT, String> column_hora;

    @FXML
    private TreeTableColumn<TicketT, String> column_total;

    @FXML
    private JFXTreeTableView<ResumenArticuloT> table_resumen_ticket;

    @FXML
    private TreeTableColumn<ResumenArticuloT, String> column_IDArticulo;

    @FXML
    private TreeTableColumn<ResumenArticuloT, String> column_nombre;

    @FXML
    private TreeTableColumn<ResumenArticuloT, String> column_cantidad;

    @FXML
    private TreeTableColumn<ResumenArticuloT, String> column_individual;

    @FXML
    private TreeTableColumn<ResumenArticuloT, String> column_total_articulo;

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

    private ConexionMySQL conexionMySQL;//= new ConexionMySQL();
    private ConexionSQLServer conexionSQLServer;
    // private SQLTicket sqlTicket = new SQLTicket();
    SQLServerTicket sqlServerTicket = new SQLServerTicket();

    private ObservableList<TicketT> listaTickets;
    private  ObservableList<ResumenArticuloT> listaArticulos ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //inicializa conexion a DB para todo el programa
        //this.conexionMySQL = new ConexionMySQL();
        this.conexionSQLServer = new ConexionSQLServer();
        //RecursosStatics.connection = conexionMySQL.getConexion();
        RecursosStatics.connection = conexionSQLServer.getConexion();
        //listaTickets = sqlTicket.listTickets();

        listaTickets = FXCollections.observableArrayList(sqlServerTicket.listTickets());
        ;
        //si da null, controlar.
        listaArticulos = FXCollections.observableArrayList();
      //este hilo espera a que el celular le mande su usuario
        ConexionServidor conexionServidor = new ConexionServidor(listaTickets);



        column_noTicket.setCellValueFactory(new TreeItemPropertyValueFactory<>("IdTicket"));
        column_IDCliente.setCellValueFactory(new TreeItemPropertyValueFactory<>("IdCliente"));
        column_Fecha.setCellValueFactory(new TreeItemPropertyValueFactory<>("fecha"));
        column_hora.setCellValueFactory(new TreeItemPropertyValueFactory<>("hora"));
        column_total.setCellValueFactory(new TreeItemPropertyValueFactory<>("totalTicket"));


        //Ticket ticket = new Ticket(1,12,new Date(156189), new Time(12315), 485.12f);
        //listaTickets.add(ticket);
        TreeItem<TicketT> root = new RecursiveTreeItem<>(listaTickets, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_tickets.setRoot(root);
        table_tickets.setShowRoot(false);



        column_IDArticulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("IDArticulo"));
        column_cantidad.setCellValueFactory(new TreeItemPropertyValueFactory<>("cantidad"));
        column_nombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("NombreProducto"));
        column_individual.setCellValueFactory(new TreeItemPropertyValueFactory<>("PrecioIndividualProducto"));
        column_total_articulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("TotalProducto"));


        TreeItem<ResumenArticuloT> root2 = new RecursiveTreeItem<>(listaArticulos, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_resumen_ticket.setRoot(root2);
        table_resumen_ticket.setShowRoot(false);



        table_tickets.setRowFactory((param -> {

            JFXTreeTableRow<TicketT> row = new JFXTreeTableRow<>();

            row.setOnMouseClicked(event -> {
                if(! row.isEmpty() && event.getButton()== MouseButton.PRIMARY /*&& event.getClickCount() == 2*/) {
                    TreeItem<ResumenArticuloT> raizRow = new RecursiveTreeItem<>(
                            FXCollections.observableArrayList(
                                    row.getTreeItem().getValue().getListProductosComprados()
                            ),
                            (recursiveTreeObject) -> recursiveTreeObject.getChildren());
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
                public void tranferirObjeto(int IDCompra,int IDCliente,float totalCompra,ArrayList<ResumenArticuloT> listProductosComprados) {
                    Calendar calendario = Calendar.getInstance();

                    ArrayList<ResumenArticulo> listaTemp = new ArrayList<>();
                    for(ResumenArticuloT resumenArticuloTActual : listProductosComprados){
                        listaTemp.add(resumenArticuloTActual.getResumenArticulo());
                    }

                    Ticket ticketNuevo = new Ticket(
                            IDCompra,IDCliente,
                            new Date(System.currentTimeMillis()),new Time(calendario.getTime().getTime()),
                            totalCompra,listaTemp);

                    if (sqlServerTicket.anadirTicket(ticketNuevo))
                        // Ticket ticketw = new Ticket(981,589,new Date(156456), new Time(1315), 45.12f);
                        listaTickets.add(new TicketT(ticketNuevo));
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
        Stage secundary = new Stage();
        Parent root ;
      //  ClientePrueba clientePrueba = new ClientePrueba();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Config_ventana.fxml"));
        AnchorPane vent = null;
        try {
            vent = loader.load();
            secundary.setScene(new Scene(vent));
          //  controllers.Productos controller = loader.getController();
            //controller.setProductos(new SQLProducto().getProductos());

            secundary.setTitle("Configuración");
            secundary.initOwner(this.btn_Add.getScene().getWindow());
            secundary.initModality(Modality.WINDOW_MODAL);
            secundary.resizableProperty().set(false);
            secundary.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            controller.setProductos(
                    FXCollections.observableArrayList( new SQLProducto().getProductos()));

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