package controllers;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ResumenArticulo;
import models.Ticket;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
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

    private final ObservableList<Ticket> listaTickets = FXCollections.observableArrayList();
    private final ObservableList<ResumenArticulo> listaArticulos = FXCollections.observableArrayList();

    @FXML
    private JFXButton btn_Config;

    @FXML
    private JFXButton btn_Add;

    @FXML
    private JFXButton btn_Cancel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        column_noTicket.setCellValueFactory(new TreeItemPropertyValueFactory<>("idTicket"));
        column_IDCliente.setCellValueFactory(new TreeItemPropertyValueFactory<>("idCliente"));
        column_Fecha.setCellValueFactory(new TreeItemPropertyValueFactory<>("fecha"));
        column_hora.setCellValueFactory(new TreeItemPropertyValueFactory<>("hora"));
        column_total.setCellValueFactory(new TreeItemPropertyValueFactory<>("totalTicket"));


        Ticket ticket = new Ticket(1,12,new Date(156189), new Time(12315), 485.12f);
        listaTickets.add(ticket);
        TreeItem<Ticket> root = new RecursiveTreeItem<>(listaTickets, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_tickets.setRoot(root);
        table_tickets.setShowRoot(false);



        column_IDArticulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("idArticulo"));
        column_nombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("nombreArticulo"));
        column_cantidad.setCellValueFactory(new TreeItemPropertyValueFactory<>("cantidad"));
        column_individual.setCellValueFactory(new TreeItemPropertyValueFactory<>("precioIndividual"));
        column_total_articulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("totalTicket"));

        ResumenArticulo resumenArticulo = new ResumenArticulo(123, "Sabritas", 12, 12.3f, 1234.13f);
        ResumenArticulo resumenArticulo2 = new ResumenArticulo(12, "Sincronizada con carne de hamburger.", 2, 36.0f, 1234.13f);

        listaArticulos.addAll(resumenArticulo,resumenArticulo2);
        TreeItem<ResumenArticulo> root2 = new RecursiveTreeItem<>(listaArticulos, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_resumen_ticket.setRoot(root2);
        table_resumen_ticket.setShowRoot(false);

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
                public void tranferirObjeto(/*TODO Debe llegar el resumen de ticket*/) {
                    Ticket ticketw = new Ticket(981,589,new Date(156456), new Time(1315), 45.12f);
                    listaTickets.add(ticketw);
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



}