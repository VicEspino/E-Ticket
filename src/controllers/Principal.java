package controllers;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import models.ResumenTicket;
import models.Ticket;

import java.net.URL;
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
    private JFXTreeTableView<ResumenTicket> table_resumen_ticket;

    @FXML
    private TreeTableColumn<ResumenTicket, String> column_IDArticulo;

    @FXML
    private TreeTableColumn<ResumenTicket, String> column_nombre;

    @FXML
    private TreeTableColumn<ResumenTicket, String> column_cantidad;

    @FXML
    private TreeTableColumn<ResumenTicket, String> column_individual;

    @FXML
    private TreeTableColumn<ResumenTicket, String> column_total_ticket;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



}