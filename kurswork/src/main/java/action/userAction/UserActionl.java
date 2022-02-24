package action.userAction;

import dao.user.UserDAO;
import entity.user.User;
import entity.user.UserProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class UserActionl implements UserAction{

    private static ObservableList<UserProperty> userProperties= FXCollections.observableArrayList();
    private static ArrayList<User> userArrayList=new ArrayList<>();

    private UserDAO userDAO;

    public UserActionl(UserDAO userDAO){
        this.userDAO=userDAO;
        this.userDAO.getListFromServer();
    }


    @Override
    public void add(UserProperty userProperty) {
        if (!(userProperty.getLogin().equals("") &&
                userProperty.getPassword().equals("") &&
                userProperty.getName().equals("") &&
                userProperty.getSurname().equals("")
        )) {
            userProperties.add(userProperty);
            userDAO.insert(userProperty);//sql
        }
    }



    public ObservableList<UserProperty> getUserList() {
        return this.userProperties;
    }

    public static ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public static void setUserProperties(ArrayList<User> list) {

        userProperties.clear();
        userArrayList.clear();
        userArrayList = list;
        for (User c : list) {
            UserProperty userProperty = new UserProperty();
            userProperty.setId(c.getId());
            userProperty.setLogin(c.getLogin());
            userProperty.setPassword(c.getPassword());
            userProperty.setName(c.getName());
            userProperty.setSurname(c.getSurname());
            userProperties.add(userProperty);
        }

    }

    @Override
    public void update(UserProperty userProperty) {
        userDAO.update(userProperty);
    }

    @Override
    public void delete(UserProperty userProperty) {
        userProperties.remove(userProperty);
        userDAO.delete(userProperty);
    }



}
