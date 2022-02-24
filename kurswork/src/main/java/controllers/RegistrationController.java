package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import action.adminAction.AdminActionl;
import action.userAction.UserActionl;
import dao.DAOFactory;
import dao.admin.AdminDAO;
import dao.user.UserDAO;
import entity.admin.AdminProperty;
import entity.user.UserProperty;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Font x1;

    @FXML
    private TextField textLogin;

    @FXML
    private Insets x2;

    @FXML
    private TextField textPassword;

    @FXML
    private TextField textName;

    @FXML
    private TextField textSurname;

    @FXML
    private ChoiceBox<?> choise;

    @FXML
    void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actionSave(ActionEvent actionEvent) {
        try {
            String string = (String) choise.getSelectionModel().getSelectedItem();

            if (string.trim().equals(null)) {

            }
            if (textLogin.getText().equals("") || textPassword.getText().equals("") || textName.getText().equals("") ||
                    textSurname.getText().equals("")) {
                showTrue(Alert.AlertType.ERROR, "Все поля должны быть заполнены!");
            } else {
                if(string.equals(list.get(0))) {
                    addNewAdmin();
                    showTrue(Alert.AlertType.INFORMATION, "Регистрация прошла успешно!!");
                }else {
                    AddNewUser();
                    showTrue(Alert.AlertType.INFORMATION, "Регистрация прошла успешно!!");
                }
                Platform.runLater(()-> {
                    AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
                    adminCollection = new AdminActionl(adminDAO);
                    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
                    userCollection = new UserActionl(userDAO);

                });
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        }
        catch(Exception ex){
            showMistake(" Выберите роль!");
        }
    }



    private void showTrue(Alert.AlertType information, String s) {
        Alert alert = new Alert(information, s);
        alert.showAndWait();
    }

    private void AddNewUser() {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        userCollection = new UserActionl(userDAO);

        UserProperty userProperty = new UserProperty();
        userProperty.setLogin(textLogin.getText());
        userProperty.setPassword(textPassword.getText());
        userProperty.setSurname(textSurname.getText());
        userProperty.setName(textName.getText());
        userCollection.add(userProperty);
    }

    private void addNewAdmin() {
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        adminCollection = new AdminActionl(adminDAO);

        AdminProperty adminProperty = new AdminProperty();
        adminProperty.setLogin(textLogin.getText());
        adminProperty.setPassword(textPassword.getText());
        adminProperty.setSurname(textSurname.getText());
        adminProperty.setName(textName.getText());
        adminCollection.addAdmin(adminProperty);
    }


    private ObservableList<String> list = FXCollections.observableArrayList();
    private AdminActionl adminCollection;
    private UserActionl userCollection;

    private void showMistake(String s) {
        showTrue(Alert.AlertType.ERROR, s);
    }

    @FXML
    void initialize() {
        list.addAll("Администратор","Пользователь");

        textLogin.setPromptText("введите логин");
        textPassword.setPromptText("введите пароль");
        textName.setPromptText("введите имя");
        textSurname.setPromptText("введите фамилию");
    }
}
