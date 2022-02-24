package controllers;


import action.adminAction.AdminActionl;
import action.ordersAction.OrdersActionl;
import action.userAction.UserActionl;
import dao.DAOFactory;
import dao.admin.AdminDAO;
import dao.orders.OrdersDAO;
import dao.user.UserDAO;
import entity.admin.AdminProperty;
import entity.orders.OrdersProperty;
import entity.storage.StorageProperty;
import entity.user.UserProperty;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FormMakeOrdersController {

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
    private ChoiceBox<?> choiceBox;

    @FXML
    private TextField comments;

    @FXML
    private Font x111;




    @FXML
    void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

        private OrdersProperty ordersProperty;


    @FXML
    void actionSave(ActionEvent actionEvent) {

        try {

            String string = (String) choiceBox.getSelectionModel().getSelectedItem();

        if (string.trim().equals(null)) {

        }


            if (textName.getText().equals("") || textNumber.getText().equals("")) {
                showTrue(Alert.AlertType.ERROR, "Все поля должны быть заполнены!");
            } else {
                if (string.equals(list.get(0))) {
                    addNewOrders();
                    showTrue(Alert.AlertType.INFORMATION, "Ваш заказ зарегистрирован!");
                } else
                {
                    addNewOrders();
                    showTrue(Alert.AlertType.INFORMATION, "Ваш заказ зарегистрирован!");
            }
                    Platform.runLater(() -> {
                        OrdersDAO ordersDAO = DAOFactory.getInstance().getOrdersDAO();
                        ordersCollection = new OrdersActionl(ordersDAO);

                    });
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        }
        catch(Exception ex){
            showMistake(" Выберите единицу измерения количества!");
        }
    }

    private void showTrue(Alert.AlertType information, String s) {
        Alert alert = new Alert(information, s);
        alert.showAndWait();
    }


    private void addNewOrders() {
        OrdersDAO ordersDAO = DAOFactory.getInstance().getOrdersDAO();
        ordersCollection = new OrdersActionl(ordersDAO);

        OrdersProperty ordersProperty = new OrdersProperty();
        ordersProperty.setNameOfOrderProduct(textName.getText());
        ordersProperty.setNumberOfOrderProduct(Double.parseDouble(textNumber.getText()));
        ordersProperty.setUnitOfOrderProduct((String) choiceBox.getValue());
        ordersProperty.setComments(comments.getText());
        ordersCollection.addOrders(ordersProperty);
    }


    private ObservableList<String> list = FXCollections.observableArrayList();
    private OrdersActionl ordersCollection;

    private void showMistake(String s) {
        showTrue(Alert.AlertType.ERROR, s);
    }

    @FXML
    void initialize() {
        list.addAll("Штук","Упаковок");

    }

//     try {
//        String string = (String) choise.getSelectionModel().getSelectedItem();
//
//        if (string.trim().equals(null)) {
//
//        }
//        if (textLogin.getText().equals("") || textPassword.getText().equals("") || textName.getText().equals("") ||
//                textSurname.getText().equals("")) {
//            showTrue(Alert.AlertType.ERROR, "Все поля должны быть заполнены!");
//        } else {
//            if(string.equals(list.get(0))) {
//                addNewAdmin();
//                showTrue(Alert.AlertType.INFORMATION, "Регистрация прошла успешно!!");
//            }else {
//                AddNewUser();
//                showTrue(Alert.AlertType.INFORMATION, "Регистрация прошла успешно!!");
//            }
//            Platform.runLater(()-> {
//                AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
//                adminCollection = new AdminActionl(adminDAO);
//                UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
//                userCollection = new UserActionl(userDAO);
//
//            });
//            Node source = (Node) actionEvent.getSource();
//            Stage stage = (Stage) source.getScene().getWindow();
//            stage.close();
//        }
//    }
//        catch(Exception ex){
//        showMistake(" Выберите роль!");
//    }
//}
//
//
//
//    private void showTrue(Alert.AlertType information, String s) {
//        Alert alert = new Alert(information, s);
//        alert.showAndWait();
//    }
//
//    private void AddNewUser() {
//        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
//        userCollection = new UserActionl(userDAO);
//
//        UserProperty userProperty = new UserProperty();
//        userProperty.setLogin(textLogin.getText());
//        userProperty.setPassword(textPassword.getText());
//        userProperty.setSurname(textSurname.getText());
//        userProperty.setName(textName.getText());
//        userCollection.add(userProperty);
//    }
//
//    private void addNewAdmin() {
//        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
//        adminCollection = new AdminActionl(adminDAO);
//
//        AdminProperty adminProperty = new AdminProperty();
//        adminProperty.setLogin(textLogin.getText());
//        adminProperty.setPassword(textPassword.getText());
//        adminProperty.setSurname(textSurname.getText());
//        adminProperty.setName(textName.getText());
//        adminCollection.addAdmin(adminProperty);
//    }
//
//
//    private ObservableList<String> list = FXCollections.observableArrayList();
//    private AdminActionl adminCollection;
//    private UserActionl userCollection;
//
//    private void showMistake(String s) {
//        showTrue(Alert.AlertType.ERROR, s);
//    }
//
//    @FXML
//    void initialize() {
//        list.addAll("Администратор","Пользователь");
//
//        textLogin.setPromptText("введите логин");
//        textPassword.setPromptText("введите пароль");
//        textName.setPromptText("введите имя");
//        textSurname.setPromptText("введите фамилию");
//    }





//    private OrdersProperty ordersProperty;
//
//
//
//    @FXML
//    void initialize() {
//
//    }
//
//    public OrdersProperty getOrdersProperty() {
//        return this.ordersProperty;
//    }
//
//    public void setOrdersProperty(OrdersProperty ordersProperty){
//
//        ObservableList<String> choiceB = FXCollections.observableArrayList("штук","упаковок");
//
//
//        if(ordersProperty == null){
//            return;
//        }
//        this.ordersProperty = ordersProperty;
//
//        textName.setText(ordersProperty.getNameOfOrderProduct());
//        textNumber.setText(String.valueOf(ordersProperty.getNumberOfOrderProduct()));
//        choiceBox.setItems(choiceB);
//        comments.setText(ordersProperty.getComments());
//
//    }
//
//    public void actionClose(ActionEvent actionEvent) {
//        Node source = (Node) actionEvent.getSource();
//        Stage stage = (Stage) source.getScene().getWindow();
//        stage.hide();
//    }
//
//    public void actionSave(ActionEvent actionEvent){
//        if(textName.getText().equals("") || textNumber.getText().equals("") || choiceBox.getValue().equals("")||
//                comments.getText().equals("")){
//            Alert alert = new Alert(Alert.AlertType.ERROR,"Все поля должны быть заполнены!");
//            alert.showAndWait();
//        }
//        else {
//
//
//            ordersProperty.setNameOfOrderProduct(textName.getText());
//            ordersProperty.setNumberOfOrderProduct(Double.parseDouble(textNumber.getText()));
//            ordersProperty.setUnitOfOrderProduct(choiceBox.getValue());
//            ordersProperty.setComments(comments.getText());
//
//            actionClose(actionEvent);
//        }
//    }



//    private OrdersProperty ordersProperty;
//
//        public OrdersProperty getOrdersProperty() {
//        return this.ordersProperty;
//    }
//
//    public void setOrdersProperty(OrdersProperty ordersProperty){
//
//                ObservableList<String> choiceB = FXCollections.observableArrayList("штук","упаковок");
//
//
//        if(ordersProperty == null){
//            return;
//        }
//        this.ordersProperty = ordersProperty;
//
//
//
//        textName.setText(ordersProperty.getNameOfOrderProduct());
//        textNumber.setText(String.valueOf(ordersProperty.getNumberOfOrderProduct()));
//        choiceBox.setItems(choiceB);
//        comments.setText(ordersProperty.getComments());
//
//    }
//
//    public void actionClose(ActionEvent actionEvent) {
//        Node source = (Node) actionEvent.getSource();
//        Stage stage = (Stage) source.getScene().getWindow();
//        stage.hide();
//    }
//
//    public void actionSave(ActionEvent actionEvent){
//        if(textName.getText().equals("") || textNumber.getText().equals("") || choiceBox.getValue().equals("")||
//                comments.getText().equals("")){
//            Alert alert = new Alert(Alert.AlertType.ERROR,"Все поля должны быть заполнены!");
//            alert.showAndWait();
//        }
//        else {
//
//
//            ordersProperty.setNameOfOrderProduct(textName.getText());
//            ordersProperty.setNumberOfOrderProduct(Double.parseDouble(textNumber.getText()));
//            ordersProperty.setUnitOfOrderProduct(choiceBox.getValue());
//            ordersProperty.setComments(comments.getText());
//
//            actionClose(actionEvent);
//        }
//    }



//

//    public void actionClose(ActionEvent actionEvent) {
//        Node source = (Node) actionEvent.getSource();
//        Stage stage = (Stage) source.getScene().getWindow();
//        stage.hide();
//    }
//
//    private OrdersProperty ordersProperty;
//
//    public OrdersProperty getOrdersProperty() {
//        return this.ordersProperty;
//    }
//
//    public void setOrdersProperty(OrdersProperty ordersProperty){
//
//        ObservableList<String> choiceB = FXCollections.observableArrayList("штук","упаковок");
//
//        if(ordersProperty == null){
//            return;
//        }
//        this.ordersProperty = ordersProperty;
//        textName.setText(ordersProperty.getNameOfOrderProduct());
//        textNumber.setText(String.valueOf((ordersProperty.getNumberOfOrderProduct())));
//        choiceBox.setItems(choiceB);
//        comments.setText(String.valueOf((ordersProperty.getComments())));
//
//    }
//
//
//
//    public void actionSave(ActionEvent actionEvent){
//
//        ordersProperty.setNameOfOrderProduct(textName.getText());
//        ordersProperty.setUnitOfOrderProduct(choiceBox.getValue());
//        ordersProperty.setComments(comments.getText());
//
//        try {
//            Double.parseDouble(textNumber.getText());
//
//            if(     Double.parseDouble(textNumber.getText())< 0 ||Double.parseDouble(textNumber.getText()) >10000||
//                    textName.getText().equals("") ||
//                    choiceBox.getValue() == null
//            ){
//                Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели некорректно число!");
//                alert.showAndWait();
//            }else {
//                ordersProperty.setNumberOfOrderProduct(Double.parseDouble(textNumber.getText()));
//                actionClose(actionEvent);
//            }
//
//        }
//        catch(Exception ex){
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели  букву вместо числа!");
//            alert.showAndWait();
//        }
//
//
//    }

}
