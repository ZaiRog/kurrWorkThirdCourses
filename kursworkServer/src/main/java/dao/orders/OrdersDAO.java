package dao.orders;

import entity.orders.Orders;

import java.util.ArrayList;
import java.util.List;

public interface OrdersDAO {

    void insertOrders(Orders orders);

    void deleteOrders(Orders orders);
    ArrayList<Orders> getOrdersList();


}
