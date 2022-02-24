package dao.admin;

import dao.DAOFactory;
import entity.admin.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminDAOl implements AdminDAO {

    public static final AdminDAOl instance = new AdminDAOl();

    public static synchronized AdminDAOl getInstance() {
        return instance;
    }

    private AdminDAOl() {
    }

    private static Connection connection = DAOFactory.getInstance().createConnection();

    @Override
    public void insertAdmin(Admin admin) {

        String login = admin.getLogin();
        String password = admin.getPassword();
        String name = admin.getName();
        String surname = admin.getSurname();

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO storage.administration (login, password, name, surname) VALUES("
                    + "' " + login.trim() + "', "
                    + "' " + password.trim() + "', "
                    + "' " + name.trim() + "', "
                    + "' " + surname.trim() + "'); ");
            System.out.println("Info was added");


        } catch (SQLException ex) {
            ex.printStackTrace();


        }

    }


    @Override
    public void deleteAdmin(Admin admin) {

        System.out.println("Получили");
        System.out.println(admin.getId());
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM storage.administration " +
                    "WHERE id = " + admin.getId() + " ;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public ArrayList<Admin> getAdminList() {
        ArrayList<Admin> adminList = new ArrayList<Admin>();
        try {

            Statement statement = connection.createStatement();
            String command ="SELECT * FROM storage.administration";
            ResultSet rs = statement.executeQuery(command);
            try {

                while (rs.next()) {

                    Admin admin = new Admin();
                    admin.setId(rs.getInt("id"));
                    admin.setLogin(rs.getString("login"));
                    admin.setPassword(rs.getString("password"));
                    admin.setName(rs.getString("name"));
                    admin.setSurname(rs.getString("surname"));
                    adminList.add(admin);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                rs.close();
            }


        } catch(SQLException e){
            e.printStackTrace();
        }
        return adminList;
    }


    @Override
    public void update (Admin admin){
        int id = admin.getId();
        System.out.println("Получили id " + id);
        String login = admin.getLogin();
        String password = admin.getPassword();
        String name = admin.getName();
        String surname = admin.getSurname();

        try {

            Statement statement = connection.createStatement();


            statement.executeUpdate("UPDATE storage.administration " +
                    "SET login = '" + login.trim() + "'," +
                    "password = '" + password.trim() + "'," +
                    "name = '" + name.trim() + "'," +
                    "surname = '" + surname.trim() + "' " +
                    "WHERE id = " + id + " ;");

            System.out.println("Данные изменены");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }




}
