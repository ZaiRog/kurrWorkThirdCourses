package controllers;

import action.ordersAction.OrdersActionl;
import dao.DAOFactory;
import dao.orders.OrdersDAO;
import entity.orders.OrdersProperty;
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
import java.util.ResourceBundle;

public class MenuOrdersForUserController {

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
    private TableColumn<OrdersProperty, String> nameOrder;

    @FXML
    private TableColumn<OrdersProperty, String> comment;

    @FXML
    private Button btnAdd;

    @FXML
    private Font x1;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnChange;

    @FXML
    private TextField textFind;

    @FXML
    private Button btnFind;



    private Parent fxmlEdit2;
    private FormMakeOrdersController formMakeOrders;
    private FXMLLoader fxmlLoader2 = new FXMLLoader();
    private Stage editDialogStage;
    private Stage mainStage2;
    private OrdersActionl ordersAction;



    @FXML
    void initialize() {

        OrdersDAO ordersDAO = DAOFactory.getInstance().getOrdersDAO();
        ordersAction = new OrdersActionl(ordersDAO);
        InitColumns();
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
        nameOrder.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));
        comment.setCellValueFactory(new PropertyValueFactory<>("comments"));

    }

    private void initLoader(){
        try{
            fxmlLoader2.setLocation(getClass().getResource("/fxml/FormMakeOrder.fxml"));
            fxmlEdit2 = fxmlLoader2.load();
            formMakeOrders = fxmlLoader2.getController();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }


    public void setMainStage(Stage mainStage) {
        this.mainStage2 = mainStage;
    }



//    public void actionButtonPressed(ActionEvent actionEvent) {
//        Object source = actionEvent.getSource();
//
//        if (!(source instanceof Button)) {
//            return;
//        }
//
//        OrdersProperty selectedProperty = (OrdersProperty) table.getSelectionModel().getSelectedItem();
//
//        Button clickedButton = (Button) source;
//
//        System.out.println(clickedButton.getId());
//
//        switch (clickedButton.getId()) {
//            case "btnAdd": {
//                formMakeOrders.setOrdersProperty(new OrdersProperty());
//                showDialog();
//                if(!(formMakeOrders.getOrdersProperty().getNameOfOrderProduct().equals(""))) {
//                    ordersAction.addOrders(formMakeOrders.getOrdersProperty());
//                }
//            }
//            break;
//
//        }
//    }

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

    public void ShowAll(ActionEvent actionEvent) {
        textFind.clear();
        setOrderList();
        table.refresh();
    }
}
