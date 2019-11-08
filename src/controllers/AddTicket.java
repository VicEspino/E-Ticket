package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import interfaces.ITransferirObjeto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTicket implements Initializable {

    @FXML
    private Label lblNumeroCompra;

    @FXML
    private JFXComboBox<String> cb_Producto;

    @FXML
    private JFXTextField txtCantidad;

    @FXML
    private JFXButton btnArticulo;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private JFXTreeTableView<?> table_resumen_ticket;

    @FXML
    private TreeTableColumn<?, ?> column_IDArticulo;

    @FXML
    private TreeTableColumn<?, ?> column_nombre;

    @FXML
    private TreeTableColumn<?, ?> column_cantidad;

    @FXML
    private TreeTableColumn<?, ?> column_individual;

    @FXML
    private TreeTableColumn<?, ?> column_total_articulo;

    @FXML
    private JFXButton btn_Add;

    @FXML
    private JFXButton btn_Cancel;
    private ITransferirObjeto objetoTransferido;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void btnArticulo_OnAction(ActionEvent event) {

    }


    @FXML
    void btnEliminar_Clic(ActionEvent event) {

    }
    @FXML
    void btnAdd_OnAction(ActionEvent event) {
        if(objetoTransferido!=null){
            objetoTransferido.tranferirObjeto();
            btn_Cancel.fire();
        }

    }

    @FXML
    void btnCancel_OnAction(ActionEvent event) {
        ((Stage)this.btn_Add.getScene().getWindow()).close();
    }

    public void setTransferirObjeto(ITransferirObjeto objetoTransferido){
        this.objetoTransferido = objetoTransferido;

    }


}
