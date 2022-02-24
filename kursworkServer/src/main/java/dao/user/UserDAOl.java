package dao.user;

import dao.DAOFactory;
import entity.user.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UserDAOl implements UserDAO {

    public static final UserDAOl instance = new UserDAOl();

    public static synchronized UserDAOl getInstance() {
        return instance;
    }

    private static Connection conn;

    static {
        conn = DAOFactory.getInstance().createConnection();
//        conn = DAOFactory.createConnection();
    }


    @Override
    public void insert(User user) {

        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();


        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(" INSERT INTO storage.user (login, password, name, surname) VALUES("
                    + "' " + login.trim() + "' , "
                    + "' " + password.trim() + "' , "
                    + "' " + name.trim() + "' , "
                    + "' " + surname.trim() + "'); ");
            System.out.println("Info was added");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void delete(User user) {
        System.out.println("Получили");
        System.out.println(user.getId());
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(" DELETE FROM storage.user " +
                    "WHERE id = " + user.getId() + " ;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public ArrayList<User> getUserList() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String command = " SELECT * FROM storage.user;";
            ResultSet rs = statement.executeQuery(command);
            try {

                while (rs.next()) {

                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    users.add(user);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                rs.close();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void update(User user){
        int id = user.getId();
        String login = user.getLogin();
        String password = user.getPassword();
        String name = user.getName();
        String surname = user.getSurname();

        try{
            Statement statement = conn.createStatement();

            statement.executeUpdate(" UPDATE storage.user " +
                    "SET login = '" + login.trim() +"'," +
                    " password = '" + password.trim() +"'," +
                    " name = '"+name.trim() +"'," +
                    " surname = '"+surname.trim() +"' " +
                    "WHERE id = " + id + " ;");

            System.out.println("Информация изменена ");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
