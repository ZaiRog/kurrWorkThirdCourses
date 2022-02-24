package communication;

import entity.admin.Admin;
import entity.orders.Orders;
import entity.storage.Storage;
import entity.user.User;

import java.io.Serializable;
import java.util.ArrayList;

public interface Communication extends Serializable {

    long serialVersionUID = 1L;

    int ADD_NEW_PRODUCT = 0;
    int DELETE_PRODUCT = 1;
    int SHOW_ALL_PRODUCT = 2;

    int CHECK_ADMIN = 5;
    int ADD_NEW_ADMIN = 6;
    int SHOW_ALL_ADMIN = 7;
    int DELETE_ADMIN = 8;
    int UPDATE_ADMIN = 9;

    int CHECK_USER = 10;
    int ADD_NEW_USER = 11;
    int SHOW_ALL_USER = 12;
    int UPDATE_USER = 13;
    int DELETE_USER = 14;

    int ADD_NEW_ORDER = 15;
    int DELETE_ORDER = 16;
    int SHOW_ALL_ORDERS = 17;


    ArrayList<Admin> getAdminList();
    Admin getAdmin();

    ArrayList<User> getUserList();
    User getUser();

    ArrayList<Storage> getStorageList();
    Storage getStorage();

    ArrayList<Orders> getOrdersList();
    Orders getOrders();

    int getType();
}
