package dao.orders;

import communication.Communication;
import communication.Communicationl;
import connection.ConnectionInfo;
import entity.orders.Orders;
import entity.orders.OrdersProperty;


public class OrdersDAOl implements OrdersDAO{


    public static OrdersDAOl instance=new OrdersDAOl();
    public static OrdersDAOl getInstance(){
        return instance;
    }

    private OrdersDAOl() { }

    private ConnectionInfo connectionInfo;

    public OrdersDAOl(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }



    @Override
    public void insertOrders(OrdersProperty ordersProperty) {
        System.out.println("Отправка: "+ ordersProperty);

        Orders orders = new Orders();
        orders.setNameOfOrderProduct(ordersProperty.getNameOfOrderProduct());
        orders.setNumberOfOrderProduct(ordersProperty.getNumberOfOrderProduct());
        orders.setUnitOfOrderProduct(ordersProperty.getUnitOfOrderProduct());
        orders.setNameCustomer(ordersProperty.getNameCustomer());
        orders.setComments(ordersProperty.getComments());

        Communication msq= new Communicationl(Communication.ADD_NEW_ORDER,orders);
        connectionInfo.send(msq);
    }

    @Override
    public void deleteOrders(OrdersProperty ordersProperty) {

        Orders orders = new Orders();
        orders.setNameOfOrderProduct(ordersProperty.getNameOfOrderProduct());
        orders.setNumberOfOrderProduct(ordersProperty.getNumberOfOrderProduct());
        orders.setUnitOfOrderProduct(ordersProperty.getUnitOfOrderProduct());
        orders.setNameCustomer(ordersProperty.getNameCustomer());
        orders.setComments(ordersProperty.getComments());
        Communication msq = new Communicationl(Communication.DELETE_ORDER,orders);
        connectionInfo.send(msq);
    }



    @Override
    public void getList() {
        System.out.println("ждем список");
        Communication mes = new Communicationl(Communication.SHOW_ALL_ORDERS);
        connectionInfo.send(mes);
    }


}
