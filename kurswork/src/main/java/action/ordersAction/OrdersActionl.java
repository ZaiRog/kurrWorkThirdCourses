package action.ordersAction;

import dao.orders.OrdersDAO;
import dao.storage.StorageDAO;
import entity.orders.Orders;
import entity.orders.OrdersProperty;
import entity.storage.Storage;
import entity.storage.StorageProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class OrdersActionl implements OrdersAction{

    static ObservableList<OrdersProperty> ordersPropertiesList = FXCollections.observableArrayList();

    private OrdersDAO ordersDAO;


    public OrdersActionl(OrdersDAO ordersDAO){
        this.ordersDAO = ordersDAO;
        this.ordersDAO.getList();
    }


    public  ObservableList<OrdersProperty> getObservableList() { return this.ordersPropertiesList; }



    public static void setOrdersList(ArrayList<Orders> list){
        ordersPropertiesList.clear();

        for(Orders c : list){

            OrdersProperty ordersProperty = new OrdersProperty();

            ordersProperty.setNameOfOrderProduct(c.getNameOfOrderProduct());
            ordersProperty.setNumberOfOrderProduct(c.getNumberOfOrderProduct());
            ordersProperty.setUnitOfOrderProduct(c.getUnitOfOrderProduct());
            ordersProperty.setCost(c.getCost());
            ordersProperty.setComments(c.getComments());
            ordersProperty.setCode(c.getCode());
            ordersProperty.setNameCustomer(c.getNameCustomer());
            ordersProperty.setUserId(c.getUserId());
            ordersProperty.setStorageId(c.getStorageId());

            ordersPropertiesList.add(ordersProperty);
        }
    }

    @Override
    public void addOrders(OrdersProperty ordersProperty) {

        ordersPropertiesList.add(ordersProperty);
        ordersDAO.insertOrders(ordersProperty);
    }

    @Override
    public void deleteOrders(OrdersProperty ordersProperty) {
        ordersPropertiesList.remove(ordersProperty);
        ordersDAO.deleteOrders(ordersProperty);
    }


}
