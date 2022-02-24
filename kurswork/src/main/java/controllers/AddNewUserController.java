package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entity.user.UserProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddNewUserController {

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

    private UserProperty userProperty;

    @FXML
    private void initialize(){
        textLogin.setPromptText("введите логин");
        textPassword.setPromptText("введите пароль");
        textName.setPromptText("введите имя");
        textSurname.setPromptText("введите фамилию");

    }

    public UserProperty getUserProperty() {
        return this.userProperty;
    }

    public void setUserProperty(UserProperty userProperty){

        if(userProperty == null){
            return;
        }
        this.userProperty = userProperty;

        textLogin.setText(userProperty.getLogin());
        textPassword.setText(userProperty.getPassword());
        textName.setText(userProperty.getName());
        textSurname.setText(userProperty.getSurname());

    }

    @FXML
    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    @FXML
    public void actionSave(ActionEvent actionEvent){
        if(textLogin.getText().equals("") || textPassword.getText().equals("") || textName.getText().equals("")||
                textSurname.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Все поля должны быть заполнены!");
            alert.showAndWait();
        }
        else {


            userProperty.setLogin(textLogin.getText());
            userProperty.setPassword(textPassword.getText());
            userProperty.setSurname(textSurname.getText());
            userProperty.setName(textName.getText());

            actionClose(actionEvent);
        }
    }

}
