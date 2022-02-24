package entity.admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class AdminProperty {

    private SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private transient SimpleStringProperty login =new SimpleStringProperty("");
    private transient SimpleStringProperty password =new SimpleStringProperty("");
    private transient SimpleStringProperty name =new SimpleStringProperty("");
    private transient SimpleStringProperty surname =new SimpleStringProperty("");

    public AdminProperty(){

    }

    public AdminProperty(String login, String password, String name, String surname){
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }


    public SimpleStringProperty getLoginProperty() {
        return login;
    }

    public SimpleStringProperty getPasswordProperty() {
        return password;
    }

    public SimpleStringProperty getNameProperty() {
        return name;
    }

    public SimpleStringProperty getSurnameProperty() {
        return surname;
    }


    @Override
    public String toString() {
        return "AdminProperty{" +
                "login=" + login +
                ", password=" + password +
                ", name=" + name +
                ", surname=" + surname +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminProperty that = (AdminProperty) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, surname);
    }

}
