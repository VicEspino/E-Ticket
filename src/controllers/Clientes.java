package  controllers;


import SQL.SQLCliente;
import SQLServer.SQLServerCliente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import models.Cliente;
import models_tablas.ClienteT;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


public class Clientes implements Initializable {

    @FXML
    private JFXButton btnAddCliente;

    @FXML
    private JFXButton btnEliminarCliente;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTreeTableView<ClienteT> table_productos;

    @FXML
    private TreeTableColumn<ClienteT, String> column_IDCliente;

    @FXML
    private TreeTableColumn<ClienteT, String> column_nombre;

    @FXML
    private TreeTableColumn<ClienteT, String> column_Email;

    @FXML
    private TreeTableColumn<ClienteT, String> column_Contrasena;

    @FXML
    private JFXTextField txtContra;

    @FXML
    private JFXTextField txtIdCliente;

    private ObservableList<ClienteT> listClientes;

    //private SQLCliente sqlCliente;
    private SQLServerCliente sqlServerCliente;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sqlServerCliente = new SQLServerCliente();
        //listClientes = FXCollections.observableArrayList();
        listClientes = FXCollections.observableArrayList(sqlServerCliente.getClientes());
        column_IDCliente.setCellValueFactory(new TreeItemPropertyValueFactory<>("IdCliente"));
        column_nombre.setCellValueFactory(new TreeItemPropertyValueFactory<>("Nombre"));
        column_Email.setCellValueFactory(new TreeItemPropertyValueFactory<>("Email"));
        column_Contrasena.setCellValueFactory(new TreeItemPropertyValueFactory<>("Contrasena"));

        TreeItem<ClienteT> root = new RecursiveTreeItem<>(listClientes, (recursiveTreeObject) -> recursiveTreeObject.getChildren());
        table_productos.setRoot(root);
        table_productos.setShowRoot(false);

        //le da valor al siguiente registro a registrar.
        setNewIndexCliente();

    }

    @FXML
    void btnAddCliente_OnAction(ActionEvent event) {

       // ConexionCliente conexionCliente = new ConexionCliente();
        if (sqlServerCliente.anadirCliente(getClienteVentana())) {
            listClientes.add(getClienteVentana());
            this.txtNombre.clear();
            this.txtEmail.clear();
            this.txtContra.clear();
            setNewIndexCliente();
        }


    }

    @FXML
    void btnEliminarCliente_OnAction(ActionEvent event) {

        int idABorrar = 0;


        ClienteT m = table_productos.getSelectionModel().getSelectedItem().getValue();

        if (m != null) {
            idABorrar = m.getIdCliente();


            if (this.sqlServerCliente.borrarProducto(idABorrar)) {
                listClientes.remove(table_productos.getSelectionModel().getSelectedItem().getValue());
                setNewIndexCliente();
            }
        }



    }

    private ClienteT getClienteVentana(){
        Cliente clienteNuevo =
                new Cliente(
                        Integer.parseInt(this.txtIdCliente.getText()),
                        this.txtNombre.getText(),
                        this.txtEmail.getText(),
                        this.txtContra.getText());

        return  new ClienteT(clienteNuevo);

    }

    private void setNewIndexCliente(){
        int idNuevoReg = sqlServerCliente.getLastIndex();
        //this.txtIdCliente.setText((sqlCliente.getLastIndex()+1)+"");
        this.txtIdCliente.setText((idNuevoReg==-1?1:idNuevoReg+1 )+ "");
        this.txtIdCliente.getText();
    }


}

