package controllers;


import java.net.URL;
import java.util.ResourceBundle;

import entity.admin.AdminProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddNewAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSave;

    @FXML
    private Font x2;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField textLogin;

    @FXML
    private Insets x1;

    @FXML
    private TextField textPassword;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSurname;

    private AdminProperty adminProperty;

    @FXML
    void initialize() {
        textLogin.setPromptText("введите логин");
        textPassword.setPromptText("введите пароль");
        textName.setPromptText("введите имя");
        textSurname.setPromptText("введите фамилию");
    }

    public AdminProperty getAdminProperty() {
        return this.adminProperty;
    }

    public void setAdminProperty(AdminProperty adminProperty){

        if(adminProperty == null){
            return;
        }
        this.adminProperty = adminProperty;

        textLogin.setText(adminProperty.getLogin());
        textPassword.setText(adminProperty.getPassword());
        textName.setText(adminProperty.getName());
        textSurname.setText(adminProperty.getSurname());

    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent){
        if(textLogin.getText().equals("") || textPassword.getText().equals("") || textName.getText().equals("")||
                textSurname.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Все поля должны быть заполнены!");
            alert.showAndWait();
        }
        else {


            adminProperty.setLogin(textLogin.getText());
            adminProperty.setPassword(textPassword.getText());
            adminProperty.setSurname(textSurname.getText());
            adminProperty.setName(textName.getText());

            actionClose(actionEvent);
        }
    }

}
