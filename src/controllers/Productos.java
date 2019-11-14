package controllers;

import SQL.ConexionSQL;
import SQL.SQLProducto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import models.Producto;

import java.net.URL;
import java.util.ResourceBundle;

public class Productos implements Initializable {

    @FXML
    private JFXTextField txtNombreProducto;

    @FXML
    private JFXTextField txtPrecioProducto;

    @FXML
    private JFXButton btnArticulo;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private JFXTreeTableView<Producto> table_productos;

    @FXML
    private TreeTableColumn<Producto, String> column_IDArticulo;

    @FXML
    private TreeTableColumn<Producto, String> column_nombre;

    @FXML
    private TreeTableColumn<Producto, String> column_individual;

    ConexionSQL conexionSQL;
    private ObservableList<Producto> listaProductos;
    private SQLProducto sqlProducto = new SQLProducto();

    @FXML
    void btnArticulo_OnAction(ActionEvent event) {
        Producto productoNuevo =
                new Producto(sqlProducto.getLastIndexTable()+1, txtNombreProducto.getText(), Float.parseFloat(txtPrecioProducto.getText()));

        if(sqlProducto.anadirProducto(productoNuevo)) {
            listaProductos.add(productoNuevo);
            this.txtNombreProducto.clear();
            this.txtPrecioProducto.clear();
        }
    }

    @FXML
    void btnEliminar_Clic(ActionEvent event) {
        Producto productoSnapshot =  table_productos.getSelectionModel().getSelectedItem().getValue();
        int idSnapshotSelected = productoSnapshot.getIdProducto();

        if(sqlProducto.borrarProducto(idSnapshotSelected))
            listaProductos.remove(productoSnapshot);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        column_IDArticulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("IdProducto"));
        column_nombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("NombreProducto"));
        column_individual.setCellValueFactory(new TreeItemPropertyValueFactory<>("Precio"));

    }

    public void setProductos(ObservableList<Producto> listaProductos){
        this.listaProductos = listaProductos;
        TreeItem<Producto> root = new RecursiveTreeItem<>(listaProductos, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_productos.setRoot(root);
        //probar true
        table_productos.setShowRoot(false);

    }


}
