package controllers;

import com.jfoenix.controls.*;
import interfaces.ITransferirObjeto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;
import models.Producto;
import models.ResumenArticulo;

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
    private JFXTreeTableView<ResumenArticulo> table_resumen_ticket;

    @FXML
    private TreeTableColumn<Producto, String> column_IDArticulo;

    @FXML
    private TreeTableColumn<Producto, String> column_nombre;

    @FXML
    private TreeTableColumn<Producto, String> column_cantidad;

    @FXML
    private TreeTableColumn<Producto, String> column_individual;

    @FXML
    private TreeTableColumn<Producto, String> column_total_articulo;

    @FXML
    private JFXButton btn_Add;

    @FXML
    private JFXButton btn_Cancel;

    private ITransferirObjeto objetoTransferido;

    private ObservableList<Producto> list_productos ;

    private ObservableList<ResumenArticulo> listProductosComprados;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listProductosComprados = FXCollections.observableArrayList();
        TreeItem<ResumenArticulo> root = new RecursiveTreeItem<>(listProductosComprados, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_resumen_ticket.setRoot(root);
        table_resumen_ticket.setShowRoot(false);

       // column_nombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("nombre"));
        column_cantidad.setCellValueFactory(new TreeItemPropertyValueFactory<>("cantidad"));

        this.list_productos =  FXCollections.observableArrayList();
        this.list_productos.add(new Producto(1, "quesadilla", 45.6f));
        this.list_productos.add(new Producto(2, "quesadilla 2", 21.6f));

        this.cb_Producto.setItems(crearStrings());
    }

    private ObservableList<String> crearStrings() {
        ObservableList<String> listaIDNombre = FXCollections.observableArrayList();

        for(Producto producto : list_productos){
            listaIDNombre.add(producto.getIdProducto() + " " + producto.getNombreProducto() );
        }

        return listaIDNombre;
    }

    @FXML
    void btnArticulo_OnAction(ActionEvent event) {

        int cantidad = Integer.parseInt( txtCantidad.getText() );
        int idProducto = cb_Producto.getSelectionModel().getSelectedIndex();
        Producto productoSelected = list_productos.get(idProducto);


        ResumenArticulo resumenArticulo = new ResumenArticulo(cantidad,cantidad * productoSelected.getPrecio() );
        listProductosComprados.add(resumenArticulo);

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
