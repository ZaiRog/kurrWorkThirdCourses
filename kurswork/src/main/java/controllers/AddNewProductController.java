package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entity.storage.StorageProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AddNewProductController {

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
    private TextField textCost;

    @FXML
    private TextField textCode;

    @FXML
    private Font x3;

    private StorageProperty storageProperty;

    public StorageProperty getStorageProperty() {
        return this.storageProperty;
    }

    public void setStorageProperty(StorageProperty storageProperty){

        if(storageProperty == null){
            return;
        }
        this.storageProperty = storageProperty;
        textName.setText(storageProperty.getName());
        textNumber.setText(String.valueOf((storageProperty.getNumber())));
        textUnit.setText(String.valueOf((storageProperty.getUnit())));
        textCost.setText(String.valueOf((storageProperty.getCost())));
        textCode.setText(String.valueOf((storageProperty.getCode())));
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent){
        storageProperty.setName(textName.getText());
        storageProperty.setUnit(textUnit.getText());
        storageProperty.setCode(textCode.getText());

        try {
            Double.parseDouble(textNumber.getText());

            if(     Double.parseDouble(textNumber.getText())< 0 ||Double.parseDouble(textNumber.getText()) >100000||
                    Double.parseDouble(textCost.getText())< 0 ||
                    textName.getText().equals("") ||
                    textUnit.getText().equals("") ||
                    textCode.getText().equals("")
            ){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели некорректно число!");
                alert.showAndWait();
            }else {
                storageProperty.setNumber(Double.parseDouble(textNumber.getText()));
                storageProperty.setCost(Double.parseDouble(textCost.getText()));
                actionClose(actionEvent);
            }

        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Вы ввели  букву вместо числа!");
            alert.showAndWait();
        }


    }

}