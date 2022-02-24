package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import action.adminAction.AdminActionl;
import action.ordersAction.OrdersActionl;
import dao.DAOFactory;
import dao.admin.AdminDAO;
import dao.orders.OrdersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void Back(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage2 = (Stage) source.getScene().getWindow();
        stage2.close();
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuAuthorization.fxml"));
            stage.setTitle("Авторизация" );

            stage.setScene(new Scene(root, 680, 460));
            stage.showAndWait();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private OrdersActionl ordersCollection;


    @FXML
    void MakeOrder(ActionEvent actionEvent) {


        try {
            Stage stage=new Stage();
            Parent rootOrders=FXMLLoader.load(getClass().getResource("/fxml/FormMakeOrder.fxml"));
            stage.setTitle("Регистрация заказа");

            stage.setScene(new Scene(rootOrders, 800, 600));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
            OrdersDAO ordersDAO= DAOFactory.getInstance().getOrdersDAO();
            ordersCollection = new OrdersActionl(ordersDAO);
        } catch (IOException ex){
            ex.printStackTrace();
        }


//        try {
//
//            Stage stage = new Stage();
//
//
//            FXMLLoader fxmll = new FXMLLoader(getClass().getResource("/fxml/FormMakeOrder.fxml"));
//            Parent rootAdmin = fxmll.load();
//            stage.setResizable(false);
//            stage.setTitle("Сделать заказ");
//            stage.setScene(new Scene(rootAdmin, 920, 840));
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
//            stage.show();
//        }
//        catch(IOException ex){
//            ex.printStackTrace();
//        }
    }

    @FXML
    void initialize() {

    }
}
