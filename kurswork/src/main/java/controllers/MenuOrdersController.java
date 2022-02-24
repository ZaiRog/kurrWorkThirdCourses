package controllers;



import action.ordersAction.OrdersActionl;
import dao.DAOFactory;
import dao.orders.OrdersDAO;
import entity.orders.OrdersProperty;
import entity.user.UserProperty;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MenuOrdersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelCount;

    @FXML
    private TableView<OrdersProperty> table;

    @FXML
    private TableColumn<OrdersProperty, String> nameOfProduct;

    @FXML
    private TableColumn<OrdersProperty, String> numberOfProduct;

    @FXML
    private TableColumn<OrdersProperty, String> unit;

    @FXML
    private TableColumn<OrdersProperty, String> cost;

    @FXML
    private TableColumn<OrdersProperty, String> comment;

    @FXML
    private TableColumn<OrdersProperty, String> code;

    @FXML
    private TableColumn<OrdersProperty, String> customer;


    @FXML
    private Button btnAdd;

    @FXML
    private Font x1;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnChange;

    @FXML
    private Button btnSumOfOrder;

    @FXML
    private TextField textFind;

    @FXML
    private Button btnFind;



    private Parent fxmlEdit2;
    private FormMakeOrdersForAdminController formMakeOrders;
    private FXMLLoader fxmlLoader2 = new FXMLLoader();
    private Stage editDialogStage;
    private Stage mainStage2;
    private OrdersActionl ordersAction;



    @FXML
    void initialize() {

        OrdersDAO ordersDAO = DAOFactory.getInstance().getOrdersDAO();
        ordersAction = new OrdersActionl(ordersDAO);
        InitColumns();
        initListener();
        setOrderList();
        initLoader();
    }

    private void setOrderList() {
        table.setItems(ordersAction.getObservableList());
    }

    private void InitColumns(){
        nameOfProduct.setCellValueFactory(new PropertyValueFactory<>("nameOfOrderProduct"));
        numberOfProduct.setCellValueFactory(new PropertyValueFactory<>("numberOfOrderProduct"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unitOfOrderProduct"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comments"));
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
        customer.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));


    }

    private void initLoader(){
        try{
            fxmlLoader2.setLocation(getClass().getResource("/fxml/FormMakeOrdersForAdmin.fxml"));
            fxmlEdit2 = fxmlLoader2.load();
            formMakeOrders = fxmlLoader2.getController();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void initListener(){
        ordersAction.getObservableList().addListener(new ListChangeListener<OrdersProperty>() {
            @Override
            public void onChanged(Change<? extends OrdersProperty> c) {
                updateCountLabel();
            }
        });
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    OrdersProperty ordersProperty = new OrdersProperty();
                    ordersProperty = (OrdersProperty)table.getSelectionModel().getSelectedItem();
                    ordersAction.deleteOrders(ordersProperty);

                    formMakeOrders.setOrdersProperty(ordersProperty);
                    showDialog();
                    textFind.clear();
                    ordersAction.addOrders(formMakeOrders.getOrdersProperty());
                }
            }
        });
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage2 = mainStage;
    }

    private void updateCountLabel() {
        Platform.runLater(() -> {
            labelCount.setText("Количество записей: " + ordersAction.getObservableList().size());
        });
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        OrdersProperty selectedProperty = (OrdersProperty) table.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        System.out.println(clickedButton.getId());

        switch (clickedButton.getId()) {
            case "btnAdd": {
                formMakeOrders.setOrdersProperty(new OrdersProperty());
                showDialog();
                if(!(formMakeOrders.getOrdersProperty().getNameOfOrderProduct().equals(""))) {
                    ordersAction.addOrders(formMakeOrders.getOrdersProperty());
                }
            }
            break;
            case "btnChange": {
                if(!(selectedProperty == (null))) {
                    OrdersProperty ordersProperty = new OrdersProperty();
                    ordersProperty = selectedProperty;
                    ordersAction.deleteOrders(selectedProperty);

                    System.out.println("Имя" + selectedProperty.getNameOfOrderProduct());

                    formMakeOrders.setOrdersProperty(selectedProperty);
                    showDialog();
                    table.refresh();

                    ordersAction.deleteOrders(ordersProperty);
                    ordersAction.addOrders(selectedProperty);
                }
            }
            break;
            case "btnDelete": {
                if(selectedProperty!=null) {
                    ordersAction.deleteOrders(selectedProperty);
                }

            }
            break;

            case "btnSumOfOrder": {

                double cost = selectedProperty.getCost();

                double numberOfProduct = selectedProperty.getNumberOfOrderProduct();
                double sum = cost * numberOfProduct;

                String summ = "Сумма данного заказа " + String.valueOf(sum);

                System.out.println("Sum = " + sum);
                System.out.println("String = " + summ);

                Alert alert = new Alert(Alert.AlertType.INFORMATION,summ);
                alert.showAndWait();
            }
            break;

        }
    }

    private void showDialog() {
        if(editDialogStage==null){
            editDialogStage=new Stage();
            editDialogStage.setTitle("Меню добавления");
            editDialogStage.setScene(new Scene(fxmlEdit2));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(this.mainStage2);
        }

        editDialogStage.showAndWait();
    }



    public void actionSearch(ActionEvent actionEvent) {
        try {
            String text2 = new String(textFind.getText());
            System.out.println(text2);
            ObservableList<OrdersProperty> newList = FXCollections.observableArrayList();

            for (OrdersProperty c : ordersAction.getObservableList()) {
                if (c.getNameOfOrderProduct().trim().equals( text2.trim() )) {
                    newList.add(c);
                }
            }

            table.setItems(newList);
            table.refresh();
        } catch (Exception ex) {
            textFind.clear();
        }

    }

    public void ShowAll(ActionEvent actionEvent) {
        textFind.clear();
        setOrderList();
        table.refresh();
    }




    @FXML
    void ShowSumOfOrder(ActionEvent actionEvent) {
//        result();
    }

}
