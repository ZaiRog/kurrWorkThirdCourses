package dao.orders;

import entity.orders.OrdersProperty;

public interface OrdersDAO {

    void insertOrders(OrdersProperty ordersProperty);

    void deleteOrders(OrdersProperty ordersProperty);

    void getList();
}
