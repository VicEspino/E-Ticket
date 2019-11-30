package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import resources.RecursosStatics;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Configuracion implements Initializable {

    @FXML
    private JFXTextField txt_IPSERVER;

    @FXML
    private JFXTextField txt_user;

    @FXML
    private JFXTextField txt_Contra;

    @FXML
    private JFXTextField txt_Puerto;

    @FXML
    private JFXTextField txt_NombreDB;

    @FXML
    private JFXButton btn_ProbarConexion;

    @FXML
    private JFXButton btn_Aceptar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.txt_user.setText(RecursosStatics.USER);
        this.txt_Contra.setText(RecursosStatics.PASS);
        this.txt_IPSERVER.setText(RecursosStatics.IP);
        this.txt_Puerto.setText( RecursosStatics.PUERTO );
        this.txt_NombreDB.setText(RecursosStatics.NOMBRE_DB);

    }

    private  final String Name = "jdbc:mysql://";
    @FXML
    void btnAceptar_OnAction(ActionEvent event) {


        RecursosStatics.USER = this.txt_user.getText();
        RecursosStatics.PASS = this.txt_Contra.getText();
        RecursosStatics.IP = this.txt_IPSERVER.getText();;
        RecursosStatics.PUERTO = this.txt_Puerto.getText();
        RecursosStatics.NOMBRE_DB = this.txt_NombreDB.getText();

        //txt_NombreDB
        RecursosStatics.PATH =
                this.Name + this.txt_IPSERVER.getText() + ":" + this.txt_Puerto.getText() + "/"  + this.txt_NombreDB.getText();
        ((Stage)this.btn_ProbarConexion.getScene().getWindow()).close();
        if(this.connection == null){

        }else{
            RecursosStatics.connection = this.connection;
        }

    }
    Connection connection;

    @FXML
    void btnProbarConexion_OnAction(ActionEvent event) {
        String path = this.Name + this.txt_IPSERVER.getText() + ":" + this.txt_Puerto.getText() + "/"  + this.txt_NombreDB.getText();



        String color ;
        try {
            Class.forName(RecursosStatics.NAME).newInstance();
            connection = DriverManager.getConnection(path,this.txt_user.getText(),this.txt_Contra.getText());

            color = "4587FF";
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();

            color = "ff4587";

        }

        this.btn_ProbarConexion.backgroundProperty().setValue(
                new Background(
                        new BackgroundFill(Paint.valueOf(color),new CornerRadii(2), new Insets(1,1,1,1))
                )
        );

    }


}
