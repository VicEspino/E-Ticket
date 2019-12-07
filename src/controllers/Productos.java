package controllers;

import SQLServer.SQLServerProducto;
import SQLServer.ConexionSQLServer;
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
import models_tablas.ProductoT;

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
    private JFXTreeTableView<ProductoT> table_productos;

    @FXML
    private TreeTableColumn<ProductoT, String> column_IDArticulo;

    @FXML
    private TreeTableColumn<ProductoT, String> column_nombre;

    @FXML
    private TreeTableColumn<ProductoT, String> column_individual;

    //ConexionMySQL conexionMySQL;
    private ConexionSQLServer conexionSQLServer;
    private ObservableList<ProductoT> listaProductos;
    private SQLServerProducto sqlServerProducto = new SQLServerProducto();

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        column_IDArticulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("IdProducto"));
        column_nombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("NombreProducto"));
        column_individual.setCellValueFactory(new TreeItemPropertyValueFactory<>("Precio"));

    }

    @FXML
    void btnArticulo_OnAction(ActionEvent event) {
        ProductoT productoNuevo =new ProductoT(
                new Producto(sqlServerProducto.getLastIndexTable() + 1, txtNombreProducto.getText(), Float.parseFloat(txtPrecioProducto.getText()))
        );

        if (sqlServerProducto.anadirProducto(productoNuevo)) {
            listaProductos.add(productoNuevo);
            this.txtNombreProducto.clear();
            this.txtPrecioProducto.clear();
        }
    }

    @FXML
    void btnEliminar_Clic(ActionEvent event) {
        ProductoT productoSnapshot =  table_productos.getSelectionModel().getSelectedItem().getValue();
        int idSnapshotSelected = productoSnapshot.getIdProducto();

        if (sqlServerProducto.borrarProducto(idSnapshotSelected))
            listaProductos.remove(productoSnapshot);

    }

    public void setProductos(ObservableList<ProductoT> listaProductos){
        this.listaProductos = listaProductos;
        TreeItem<ProductoT> root = new RecursiveTreeItem<>(listaProductos, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_productos.setRoot(root);
        //probar true
        table_productos.setShowRoot(false);

    }


}
