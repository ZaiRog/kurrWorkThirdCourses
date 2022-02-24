package controllers;

import entity.orders.OrdersProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FormMakeOrdersForAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Font x1;

    @FXML
    private TextField textName;

    @FXML
    private TextField textNumber;

    @FXML
    private Insets x2;

    @FXML
    private TextField textUnit;


    @FXML
    private TextField comments;


    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    private OrdersProperty ordersProperty;

    public OrdersProperty getOrdersProperty() {
        return this.ordersProperty;
    }

    public void setOrdersProperty(OrdersProperty ordersProperty){

        if(ordersProperty == null){
            return;
        }
        this.ordersProperty = ordersProperty;
        textName.setText(ordersProperty.getNameOfOrderProduct());
        textNumber.setText(String.valueOf((ordersProperty.getNumberOfOrderProduct())));
        textUnit.setText(ordersProperty.getUnitOfOrderProduct());
        comments.setText(ordersProperty.getComments());

    }


    public void actionSave(ActionEvent actionEvent){
        ordersProperty.setNameOfOrderProduct(textName.getText());
        ordersProperty.setUnitOfOrderProduct(textUnit.getText());
        ordersProperty.setComments(comments.getText());

        try {
            Double.parseDouble(textNumber.getText());

            if(     Double.parseDouble(textNumber.getText())< 0 ||Double.parseDouble(textNumber.getText()) >10000||
                    textName.getText().equals("") ||
                    textUnit.getText().equals("")
            ){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели некорректно число!");
                alert.showAndWait();
            }else {
                ordersProperty.setNumberOfOrderProduct(Double.parseDouble(textNumber.getText()));
                actionClose(actionEvent);
            }

        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели  букву вместо числа!");
            alert.showAndWait();
        }


    }
}
