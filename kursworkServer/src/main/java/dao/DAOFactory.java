package dao;

import dao.admin.AdminDAO;
import dao.admin.AdminDAOl;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOl;
import dao.storage.StorageDAO;
import dao.storage.StorageDAOl;
import dao.user.UserDAO;
import dao.user.UserDAOl;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOFactory {


    public static final DAOFactory instance = new DAOFactory();
    public static synchronized DAOFactory getInstance(){return instance; }
    private DAOFactory(){}
    private static final String url = "jdbc:mysql://localhost:3306";
    private static final String username = "root";
    private static final String password = "12345Egor";

    private static Connection conn;

    public static Connection createConnection() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение установлено с БД!");

        } catch (Exception ex) {
            System.out.println(" Данные не переданы! Ошибка...");
            System.out.println(ex);
        }
        return conn;
    }

    public StorageDAO getStorageDAO(){
        return StorageDAOl.getInstance();
    }

    public AdminDAO getAdminDAO(){
        return AdminDAOl.getInstance();
    }

    public UserDAO getUserDAO(){
        return UserDAOl.getInstance();
    }

    public OrdersDAO getOrdersDAO(){
    return OrdersDAOl.getInstance();
    }


}
