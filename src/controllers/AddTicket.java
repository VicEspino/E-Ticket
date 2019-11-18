package controllers;

import SQL.SQLProducto;
import SQL.SQLTicket;
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
    private Label lblTotalCompra;

    @FXML
    private JFXButton btn_Add;

    @FXML
    private JFXButton btn_Cancel;

    @FXML
    private JFXTextField txtIDCliente;

    private ITransferirObjeto objetoTransferido;

    private ObservableList<Producto> list_productos ;

    private ObservableList<ResumenArticulo> listProductosComprados;

    private int IDCompra;

    private int IDCliente;

    private SQLProducto sqlProducto = new SQLProducto();
    private SQLTicket sqlTicket = new SQLTicket();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listProductosComprados = FXCollections.observableArrayList();
        TreeItem<ResumenArticulo> root = new RecursiveTreeItem<>(listProductosComprados, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_resumen_ticket.setRoot(root);
        table_resumen_ticket.setShowRoot(false);

       // busca el metodo get, con ese nombre.<3
        column_IDArticulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("IDArticulo"));
        column_cantidad.setCellValueFactory(new TreeItemPropertyValueFactory<>("cantidad"));
        column_nombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("NombreProducto"));
        column_individual.setCellValueFactory(new TreeItemPropertyValueFactory<>("PrecioIndividualProducto"));
        column_total_articulo.setCellValueFactory(new TreeItemPropertyValueFactory<>("TotalProducto"));




        this.list_productos =  sqlProducto.getProductos();//FXCollections.observableArrayList();
        //this.list_productos.add(new Producto(1, "Sincronizada", 25f));
        //this.list_productos.add(new Producto(2, "Sincronizada carne Hamburguesa", 35f));
        //this.list_productos.add(new Producto(3, "Birria de la esquina", 45f));

        this.cb_Producto.setItems(crearStrings());
        this.IDCompra = sqlTicket.getLasIndexTicket()+1;
        this.lblNumeroCompra.setText(IDCompra+"");

    }

    private ObservableList<String> crearStrings() {
        ObservableList<String> listaIDNombre = FXCollections.observableArrayList();

        for(Producto producto : list_productos){
            listaIDNombre.add(producto.getIdProducto() + " " + producto.getNombreProducto() );
        }

        return listaIDNombre;
    }

    @FXML
    void btnAddArticulo_OnAction(ActionEvent event) {


        int cantidad = Integer.parseInt( txtCantidad.getText() );
        int idProducto = cb_Producto.getSelectionModel().getSelectedIndex();
        Producto productoSelected = list_productos.get(idProducto);


        ResumenArticulo resumenArticulo = new ResumenArticulo(this.IDCompra,productoSelected ,cantidad);
        listProductosComprados.add(resumenArticulo);

        txtCantidad.clear();
        cb_Producto.getSelectionModel().clearSelection();


        this.lblTotalCompra.setText(calcularTotalCompra()  + "");
    }


    @FXML
    void btnEliminar_Clic(ActionEvent event) {

        listProductosComprados.remove(table_resumen_ticket.getSelectionModel().getSelectedItem().getValue());
        this.lblTotalCompra.setText(calcularTotalCompra()  + "");
        //listProductosComprados.get(0).setCantidad(999);
    }


    @FXML
    void btnAdd_OnAction(ActionEvent event) {
        if(objetoTransferido!=null){
            int idCliente = Integer.parseInt(txtIDCliente.getText());
            objetoTransferido.tranferirObjeto(
                    this.IDCompra,idCliente,this.calcularTotalCompra(),this.listProductosComprados
            );
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

    public float calcularTotalCompra(){
        float total = 0;
        for(ResumenArticulo resumenArticulo : listProductosComprados){
            total += resumenArticulo.getTotalProducto();
        }

        return total;
    }
}
