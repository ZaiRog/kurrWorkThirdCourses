package dao.user;

import communication.Communication;
import communication.Communicationl;
import connection.ConnectionInfo;
import entity.user.User;
import entity.user.UserProperty;

public class UserDAOl implements UserDAO {

    public static UserDAOl instance = new UserDAOl();

    public static UserDAOl getInstance(){ return instance;}

    private UserDAOl(){}

    private ConnectionInfo connectionInfo;

    public UserDAOl(ConnectionInfo connectionInfo){
        this.connectionInfo = connectionInfo;
    }

    public void getListFromServer(){
        System.out.println("Ждём список");
        Communication mes = new Communicationl(Communication.SHOW_ALL_USER);
        connectionInfo.send(mes);
    }

    @Override
    public void insert(UserProperty userProperty){
        User user = new User();
        user.setId(userProperty.getId());
        user.setLogin(userProperty.getLogin());
        user.setPassword(userProperty.getPassword());
        user.setName(userProperty.getName());
        user.setSurname(userProperty.getSurname());

        Communication msq = new Communicationl(user, Communication.ADD_NEW_USER);
        connectionInfo.send(msq);

    }

    @Override
    public void update(UserProperty userProperty ) {

        User user = new User();

        user.setId(userProperty.getId());
        user.setLogin(userProperty.getLogin());
        user.setPassword(userProperty.getPassword());
        user.setName(userProperty.getName());
        user.setSurname(userProperty.getSurname());

        Communication msq = new Communicationl(user, Communication.UPDATE_USER);
        connectionInfo.send(msq);
    }



    @Override
    public void delete(UserProperty userProperty) {
        User user = new User();
        user.setId(userProperty.getId());
        user.setLogin(userProperty.getLogin());
        user.setPassword(userProperty.getPassword());
        user.setName(userProperty.getName());
        user.setSurname(userProperty.getSurname());

        Communication msq = new Communicationl(user, Communication.DELETE_USER);
        connectionInfo.send(msq);
    }

}
