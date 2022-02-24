package entity.user;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    static final long serialVersionUID=1L;

    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;

    public User(){

    }


    public User(String name, String surname, String login, String password){
        this.name=name;
        this.surname=surname;
        this.login=login;
        this.password=password;
    }

    public String getName(){
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User admin = (User) o;
        return name.equals(admin.name) &&
                surname.equals(admin.surname) &&
                login.equals(admin.login) &&
                password.equals(admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, login, password);
    }



    private void writeObject(ObjectOutputStream c) throws Exception {
        c.defaultWriteObject();
    }

    private void readObject(ObjectInputStream c) throws Exception {
        c.defaultReadObject();
    }



}
