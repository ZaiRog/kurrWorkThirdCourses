package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import action.adminAction.AdminActionl;
import dao.DAOFactory;
import dao.admin.AdminDAO;
import entity.admin.AdminProperty;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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

public class TableOfAdminsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelAdminCount;

    @FXML
    private TableView<AdminProperty> tableAdmins;

    @FXML
    private TableColumn<AdminProperty, String> columnLogin;

    @FXML
    private TableColumn<AdminProperty, String> columnPassword;

    @FXML
    private TableColumn<AdminProperty, String> columnName;

    @FXML
    private TableColumn<AdminProperty, String> columnSurname;

    @FXML
    private Button btnAddNewAdmin;

    @FXML
    private Font x1;

    @FXML
    private Button btnChangeAdmin;

    @FXML
    private Insets x2;

    @FXML
    private Button btnDeleteAdmin;

    @FXML
    private TextField textFindAdmin;

    @FXML
    private Button btnFindAdmin;

    private Parent fxmlEdit;
    private AddNewAdminController addNewAdmin;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage editDialogStage;
    private Stage mainStage;
    private AdminActionl adminCollection;

    @FXML
    void ShowAll(ActionEvent actionEvent) {
        textFindAdmin.clear();
        setAdminList();
        tableAdmins.refresh();
    }

    private void setAdminList() {
        tableAdmins.setItems(adminCollection.getAdminList());
    }

    @FXML
    void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }


        AdminProperty selectedAdminProperty = (AdminProperty) tableAdmins.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAddNewAdmin": {
                addNewAdmin.setAdminProperty(new AdminProperty());
                showDialog();
                adminCollection.addAdmin(addNewAdmin.getAdminProperty());
                Platform.runLater(()->{
                    AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
                    adminCollection = new AdminActionl(adminDAO);
                    setAdminList();
                });
            }
            break;
            case "btnChangeAdmin": {
                if(!(selectedAdminProperty == (null))) {
                    addNewAdmin.setAdminProperty(selectedAdminProperty);
                    showDialog();
                    tableAdmins.refresh();

                    adminCollection.update(selectedAdminProperty);
                }
            }
            break;
            case "btnDeleteAdmin": {
                adminCollection.deleteAdmin(selectedAdminProperty);
            }
            break;
        }
    }

    @FXML
    void actionSearch(ActionEvent actionEvent) {
        String text = textFindAdmin.getText();
        System.out.println(text);
        ObservableList<AdminProperty> newList = FXCollections.observableArrayList();
        for (AdminProperty c : adminCollection.getAdminList()) {
            if (c.getLogin().trim().equals( text.trim() )) {
                newList.add(c);
            }
        }
        tableAdmins.setItems(newList);
        tableAdmins.refresh();

    }

    @FXML
    void initialize() {
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        adminCollection = new AdminActionl(adminDAO);
        initColumns();
        initListener();

        setAdminList();
        initLoader();

    }

    private void initColumns() {
        columnLogin.setCellValueFactory(new PropertyValueFactory<AdminProperty, String>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<AdminProperty, String>("password"));
        columnName.setCellValueFactory(new PropertyValueFactory<AdminProperty, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<AdminProperty, String>("surname"));
        textFindAdmin.setPromptText("введите логин");
    }



    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource("/fxml/AddNewAdmin.fxml"));
            fxmlEdit = fxmlLoader.load();
            addNewAdmin = fxmlLoader.getController();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void initListener() {
        adminCollection.getAdminList().addListener(new ListChangeListener<AdminProperty>() {
            @Override
            public void onChanged(Change<? extends AdminProperty> c) {
                updateCountAdminLabel();
            }
        });

        tableAdmins.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    textFindAdmin.clear();
                }
            }
        });


    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountAdminLabel() {
        Platform.runLater(() -> {
            labelAdminCount.setText("Количество записей: " + adminCollection.getAdminList().size());
        });
    }


    private void showDialog() {
        if(editDialogStage==null){
            editDialogStage=new Stage();
            editDialogStage.setTitle("Изменение");
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(this.mainStage);
        }
        editDialogStage.showAndWait();
    }


}


