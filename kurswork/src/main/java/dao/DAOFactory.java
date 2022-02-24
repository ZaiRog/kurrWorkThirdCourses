package dao;

import connection.ConnectToServer;
import connection.ConnectionInfo;
import dao.admin.AdminDAO;
import dao.admin.AdminDAOl;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOl;
import dao.storage.StorageDAO;
import dao.storage.StorageDAOl;
import dao.user.UserDAO;
import dao.user.UserDAOl;

public class DAOFactory {


    public static final DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance() {
        return instance;
    }

    private DAOFactory() {
    }


    private static ConnectionInfo connectionInfo = ConnectToServer.getInstance().createConnection();


    public StorageDAO getStorageDAO() {
        return new StorageDAOl(connectionInfo);
    }


    public AdminDAO getAdminDAO(){
        return new AdminDAOl((connectionInfo));
    }

    public UserDAO getUserDAO(){
        return new UserDAOl((connectionInfo));
    }

    public OrdersDAO getOrdersDAO(){
        return new OrdersDAOl((connectionInfo));
    }


}
