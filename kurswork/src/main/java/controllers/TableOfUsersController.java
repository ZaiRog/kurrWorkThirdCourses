package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import action.userAction.UserActionl;
import dao.DAOFactory;
import dao.user.UserDAO;
import entity.user.UserProperty;
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

public class TableOfUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelCount;

    @FXML
    private TableView<UserProperty> table;

    @FXML
    private TableColumn<UserProperty, String> columnLogin;

    @FXML
    private TableColumn<UserProperty, String> columnPassword;

    @FXML
    private TableColumn<UserProperty, String> columnName;

    @FXML
    private TableColumn<UserProperty, String> columnSurname;

    @FXML
    private Button btnAddNew;

    @FXML
    private Font x2;

    @FXML
    private Button btnChange;

    @FXML
    private Insets x1;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField textFind;

    @FXML
    private Button btnFind;


    private Parent fxmlEdit;
    private AddNewUserController addNewUser;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Stage editDialogStage;
    private Stage mainStage;
    private UserActionl userAction;

    @FXML
    void initialize() {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        userAction = new UserActionl(userDAO);
        InitColumns();
        initListener();

        setUserList();
        initLoader();

    }

    private void setUserList() {
        table.setItems(userAction.getUserList());
    }

    private void InitColumns() {

        columnLogin.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("login"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("password"));
        columnName.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<UserProperty, String>("surname"));
        textFind.setPromptText("введите логин");
    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource("/fxml/AddNewUser.fxml"));
            fxmlEdit = fxmlLoader.load();
            addNewUser = fxmlLoader.getController();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initListener() {
        userAction.getUserList().addListener(new ListChangeListener<UserProperty>() {
            @Override
            public void onChanged(Change<? extends UserProperty> c) {
                updateCountLabel();
            }
        });

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    textFind.clear();
                }
            }
        });


    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void updateCountLabel() {

        Platform.runLater(() -> {
            labelCount.setText("Количество записей: " + userAction.getUserList().size());
        });
    }

    @FXML
    void actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        UserProperty selectedUserProperty = (UserProperty) table.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {

            case "btnAddNew": {
                addNewUser.setUserProperty(new UserProperty());
                showDialog();
                userAction.add(addNewUser.getUserProperty());
                Platform.runLater(()->{
                    UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
                    userAction = new UserActionl(userDAO);
                    setUserList();
                });
            }
            break;
            case "btnChange": {
                if(!(selectedUserProperty==null)) {
                    addNewUser.setUserProperty(selectedUserProperty);
                    showDialog();
                    table.refresh();

                    userAction.update(selectedUserProperty);
                }
            }
            break;
            case "btnDelete": {
                if(selectedUserProperty!=null) {
                    userAction.delete(selectedUserProperty);
                }
            }
            break;
        }

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

    @FXML
    void actionSearch(ActionEvent actionEvent) {
        String text = textFind.getText();
        System.out.println(text);
        ObservableList<UserProperty> newList = FXCollections.observableArrayList();
        for (UserProperty c : userAction.getUserList()) {
            if (c.getLogin().trim().equals( text.trim() )) {
                newList.add(c);
            }
        }
        table.setItems(newList);
        table.refresh();

    }

    @FXML
    void ShowAll(ActionEvent actionEvent) {
        textFind.clear();
        setUserList();
        table.refresh();
    }

}
