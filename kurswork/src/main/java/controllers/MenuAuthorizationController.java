package controllers;

        import java.io.IOException;
        import java.net.URL;
        import java.util.Objects;
        import java.util.ResourceBundle;

        import action.adminAction.AdminActionl;
        import action.userAction.UserActionl;
        import connection.ConnectToServer;
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
        import javafx.fxml.FXMLLoader;
        import javafx.geometry.Insets;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Alert;
        import javafx.scene.control.ChoiceBox;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
        import javafx.scene.text.Font;
        import javafx.stage.Modality;
        import javafx.stage.Stage;

public class MenuAuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Insets x1;

    @FXML
    private Font x3;

    @FXML
    private TextField textLogin;

    @FXML
    private Insets x2;

    @FXML
    private PasswordField textPassword;

    @FXML
    private ChoiceBox choise;


    private ObservableList<String> list = FXCollections.observableArrayList();

    private AdminActionl adminCollection;

    private UserActionl userCollection;


    @FXML
    void Autorization(ActionEvent actionEvent){
        takeLists();
        try{
            String string = (String) choise.getSelectionModel().getSelectedItem();
            if(string.trim().equals(null)){

            }else{
                if(textLogin.getText().equals("") || textPassword.getText().equals("")){
                    showMistake("Все поля должны быть заполнены!\n Также вы могли забыть выбрать роль!");
                    clearFields();
                }else{
                    if(adminCollection.getAdminList().size()==0){
                        showMistake("Нет зарегистрированных пользователей!\n" +
                                "Пройдите процедуры регистрации!");
                        clearFields();
                    } else {
                        try {
                            if(string.equals(list.get(0))){
                                checkAdmin(actionEvent);
                            }else{
                                checkUser(actionEvent);
                            }
                        }catch (Exception ex){
                            showMistake("Данные не найдены!\n" +
                                    "Проверьте корректность ввода данных!");
                            clearFields();
                        }
                    }
                }
            }
        }catch (Exception ex){
            showMistake("Выберите роль!");
            clearFields();
        }
    }


    private void clearFields(){
        textLogin.clear();
        textPassword.clear();
        choise.getSelectionModel().clearSelection();
    }

    private void showMistake(String s){
        Alert alert = new Alert(Alert.AlertType.ERROR, s);
        alert.showAndWait();
    }



    private void checkUser(ActionEvent actionEvent){
        boolean flag = false;

        for(UserProperty c: userCollection.getUserList()){
            if(c.getLogin().trim().equals(textLogin.getText().trim()) &&
                    c.getPassword().trim().equals(textPassword.getText().trim())){
                showUserMenu(actionEvent);
                flag=true;
            }
        }
        if(!flag){
            showMistake("Вы ввели неверно данные!");
            Platform.runLater(()->{
                clearFields();
            });
        }
    }


    private void checkAdmin(ActionEvent actionEvent){

        boolean flag=false;

        for(AdminProperty c: adminCollection.getAdminList()){
            if(c.getLogin().trim().equals(textLogin.getText().trim()) &&
                    c.getPassword().trim().equals(textPassword.getText().trim())){
                flag=true;
                showAdminMenu(actionEvent);
            }
        }
        if(!flag){
            showMistake("Вы ввели неверно данные!");
            clearFields();
        }
    }


    private void showAdminMenu(ActionEvent actionEvent){

        close(actionEvent);
        try{
            Stage stage=new Stage();
            Parent rootAdmin= FXMLLoader.load(getClass().getResource("/fxml/MenuAdmin.fxml"));
            stage.setTitle("Меню Админа");

            stage.setResizable(false);

            stage.setScene(new Scene(rootAdmin,  850, 540));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }



    private void showUserMenu(ActionEvent actionEvent){
        close(actionEvent);

        try{
            Stage stage=new Stage();
            Parent rootAdmin=FXMLLoader.load(getClass().getResource("/fxml/MenuUser.fxml"));
            stage.setTitle("Меню пользователя");

            stage.setResizable(false);

            stage.setScene(new Scene(rootAdmin, 800, 520));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }




    public void Exit(ActionEvent actionEvent){
        close(actionEvent);
        ConnectToServer.getInstance().connectionClose(ConnectToServer.getConnectionInfo());
        System.exit(0);
    }

    private void close(ActionEvent actionEvent){
        Node source=(Node) actionEvent.getSource();
        Stage stage2=(Stage) source.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void Registration(ActionEvent actionEvent) {

        try {
            Stage stage=new Stage();
            Parent rootAdmin=FXMLLoader.load(getClass().getResource("/fxml/Registration.fxml"));
            stage.setTitle("Регистрация");

            stage.setScene(new Scene(rootAdmin, 660, 450));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
            AdminDAO adminDAO=DAOFactory.getInstance().getAdminDAO();
            adminCollection=new AdminActionl(adminDAO);
        } catch (IOException ex){
            ex.printStackTrace();
        }

    }


    @FXML
    void initialize() {
        list.addAll("Администратор", "Пользователь");
        textLogin.setPromptText("Введите логин");
        textPassword.setPromptText("Введите пароль");
        takeLists();
        choise.setItems(list);
    }


    private void takeLists(){
        Platform.runLater(()->{
            AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
            adminCollection = new AdminActionl(adminDAO);
            UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
            userCollection = new UserActionl(userDAO);

        });
    }
}
