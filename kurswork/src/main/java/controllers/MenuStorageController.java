package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import action.storageAction.StorageActionl;
import dao.DAOFactory;
import dao.storage.StorageDAO;
import entity.orders.OrdersProperty;
import entity.storage.StorageProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuStorageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelCount;

    @FXML
    private TableView<StorageProperty> table;

    @FXML
    private TableColumn<StorageProperty, String> codeOfProduct;

    @FXML
    private TableColumn<StorageProperty, String> nameOfProduct;

    @FXML
    private TableColumn<StorageProperty, Integer> numberOfProduct;

    @FXML
    private TableColumn<StorageProperty, String> unit;

    @FXML
    private TableColumn<StorageProperty, Integer> costOfProduct;

    @FXML
    private Button btnAddNew;

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


    private Parent fxmlEdit;
    private AddNewProductController addNewProduct;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage editDialogStage;
    private Stage mainStage;
    private StorageActionl storageAction;

    @FXML
    void initialize() {

        StorageDAO storageDAO = DAOFactory.getInstance().getStorageDAO();
        storageAction = new StorageActionl(storageDAO);
        InitColumns();
        initListener();
        setStorageList();
        initLoader();
    }

    private void setStorageList() {
        table.setItems(storageAction.getObservableList());
    }


    private void InitColumns(){
        nameOfProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberOfProduct.setCellValueFactory(new PropertyValueFactory<>("number"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        costOfProduct.setCellValueFactory(new PropertyValueFactory<>("cost"));
        codeOfProduct.setCellValueFactory(new PropertyValueFactory<>("code"));

    }

    private void initLoader(){
        try{
            fxmlLoader.setLocation(getClass().getResource("/fxml/AddNewProduct.fxml"));
            fxmlEdit = fxmlLoader.load();
            addNewProduct = fxmlLoader.getController();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void initListener(){
        storageAction.getObservableList().addListener(new ListChangeListener<StorageProperty>() {
            @Override
            public void onChanged(Change<? extends StorageProperty> c) {
                updateCountLabel();
            }
        });
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    StorageProperty storageProperty = new StorageProperty();
                    storageProperty = (StorageProperty)table.getSelectionModel().getSelectedItem();
                    storageAction.deleteStorage(storageProperty);

                    addNewProduct.setStorageProperty(storageProperty);
                    showDialog();
                    textFind.clear();
                    storageAction.addStorage(addNewProduct.getStorageProperty());
                }
            }
        });
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {
        Platform.runLater(() -> {
            labelCount.setText("Количество записей: " + storageAction.getObservableList().size());
        });
    }

    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }


        StorageProperty selectedProperty = (StorageProperty) table.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        System.out.println(clickedButton.getId());

        switch (clickedButton.getId()) {
            case "btnAdd": {
                addNewProduct.setStorageProperty(new StorageProperty());
                showDialog();
                if(!(addNewProduct.getStorageProperty().getName().equals(""))) {
                    storageAction.addStorage(addNewProduct.getStorageProperty());
                }
            }
            break;
            case "btnChange": {
                if(!(selectedProperty == (null))) {
                    StorageProperty storageProperty = new StorageProperty();
                    storageProperty = selectedProperty;
                    storageAction.deleteStorage(selectedProperty);

                    System.out.println("Имя" + selectedProperty.getName());

                    addNewProduct.setStorageProperty(selectedProperty);
                    showDialog();
                    table.refresh();

                    storageAction.deleteStorage(storageProperty);
                    storageAction.addStorage(selectedProperty);
                }
            }
            break;
            case "btnDelete": {
                if(selectedProperty!=null) {
                    storageAction.deleteStorage(selectedProperty);
                }

            }
            break;

        }
    }

    private void showDialog() {
        if(editDialogStage==null){
            editDialogStage=new Stage();
            editDialogStage.setTitle("Меню добавления");
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(this.mainStage);
        }

        editDialogStage.showAndWait();
    }

    public void actionSearch(ActionEvent actionEvent) {
        try {
            String text = textFind.getText();
            System.out.println(text);
            ObservableList<StorageProperty> newList = FXCollections.observableArrayList();

            for (StorageProperty c : storageAction.getObservableList()) {
                if (c.getName().trim().equals( text.trim() )) {
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
        setStorageList();
        table.refresh();
    }


}