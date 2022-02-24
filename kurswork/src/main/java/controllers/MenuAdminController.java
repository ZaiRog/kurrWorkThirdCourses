package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void ShowAdmins(ActionEvent actionEvent) {
        try {

            Stage stage = new Stage();

            FXMLLoader fxmll = new FXMLLoader(getClass().getResource("/fxml/TableOfAdmins.fxml"));
            Parent rootAdmin = fxmll.load();
            stage.setTitle("Администраторы");
            stage.setScene(new Scene(rootAdmin, 780, 620));
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());

            stage.show();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void Back(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuAuthorization.fxml"));
            stage.setTitle("Авторизация" );
            stage.setScene(new Scene(root, 600, 450));
            stage.showAndWait();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void ShowTableOrders(ActionEvent actionEvent) {
        try {

            Stage stage = new Stage();

            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/MenuOrders.fxml"));
            Parent rootAdmin = fxml.load();
            stage.setTitle("Меню заказов");
            stage.setScene(new Scene(rootAdmin, 980, 665));
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());

            stage.show();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void ShowTableStorage(ActionEvent actionEvent) {
        try {

            Stage stage = new Stage();

            Parent rootAdmin = FXMLLoader.load(getClass().getResource("/fxml/MenuStorage.fxml"));

            stage.setResizable(false);
            stage.setTitle("Меню склада");
            stage.setScene(new Scene(rootAdmin, 950, 680));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            stage.show();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void ShowUsers(ActionEvent actionEvent) {
        try{
            Stage stage = new Stage();

            Parent rootAdmin = FXMLLoader.load(getClass().getResource("/fxml/TableOfUsers.fxml"));

            stage.setTitle("Пользователи");

            stage.setResizable(false);

            stage.setScene(new Scene(rootAdmin,820,580));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow()) ;
            stage.show();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }
}
