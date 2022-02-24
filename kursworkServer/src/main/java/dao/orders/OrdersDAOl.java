package dao.orders;

import dao.DAOFactory;
import entity.orders.Orders;
import entity.storage.Storage;
import entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOl implements OrdersDAO{

    public static OrdersDAOl instance =new OrdersDAOl();
    public static synchronized OrdersDAOl getInstance(){
        return instance;
    }

    private OrdersDAOl(){

    }

    private static Connection connection;

    static {
        connection = DAOFactory.getInstance().createConnection();
    }


    private User user;
    private Storage storage;

    public int findIdByName(String nameOfProduct){
        ArrayList<Orders> orders = new ArrayList<Orders>();
        Orders orders1 = new Orders();

        int id = 1;
        try{
            Statement statement=connection.createStatement();

            String command ="SELECT storage.storages.id FROM storage.storages WHERE name IN ('" + nameOfProduct + "')";
            ResultSet rs = statement.executeQuery(command);
            try{
                while (rs.next()) {

                    Orders orders2 = new Orders();
                    orders2.setStorageId(rs.getInt("id"));
                    id = orders2.getStorageId();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                rs.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }


    public int findIdByUser(){


        int id = 0;
        try{
            Statement statement=connection.createStatement();

            String command ="SELECT storage.user.id FROM storage.user WHERE storage.user.name IN ('" + user.getId() + "')";

            ResultSet rs = statement.executeQuery(command);
            try{
                while (rs.next()) {

                    Orders orders2= new Orders();
                    orders2.setUserId(rs.getInt("id"));
                    id = rs.getInt("id");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                rs.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    private OrdersDAOl ordersDAOl;

    @Override
    public void insertOrders(Orders orders){


        String nameOfProduct = orders.getNameOfOrderProduct();
        double numberOfProduct = orders.getNumberOfOrderProduct();
        String unitOfProduct = orders.getUnitOfOrderProduct();
        String comments = orders.getComments();
        int userId = 1;
        int storageId = findIdByName(orders.getNameOfOrderProduct());
        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate("INSERT INTO storage.orders ( nameOfProduct, numberOfProduct, unitOfProduct, comments, userId, storageId) VALUES("
//                      + "' "+ id  + "' , "
                    + "' "+ nameOfProduct.trim() + "', "
                    + "' "+ numberOfProduct + "', "
                    + "' "+ unitOfProduct.trim() + "', "
                    + "' "+ comments.trim() + "', "
                    + "' "+ userId + "', "
                    + "' "+ storageId + "'); ");
            System.out.println("Заказ добавлен");

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    @Override
    public void deleteOrders(Orders orders){
        System.out.println("Удаляем:  " + orders.getNameOfOrderProduct() + " заказчик: " + orders.getNameCustomer());
        try{
            Statement statement=connection.createStatement();
            statement.executeUpdate(" DELETE FROM storage.orders " +
                    "WHERE nameOfProduct = " + "'" + orders.getNameOfOrderProduct() + "';");

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("Удаление завершено! "+ orders.getNameOfOrderProduct());
    }

    @Override
    public ArrayList<Orders> getOrdersList(){
        ArrayList<Orders> orders = new ArrayList<Orders>();
        try{

            Statement statement = connection.createStatement();
            String command = "SELECT storage.orders.nameOfProduct, storage.orders.numberOfProduct, storage.orders.unitOfProduct,storage.storages.cost, storage.orders.comments,\n" +
                    "       storage.storages.code, storage.user.name FROM storage.orders JOIN storage.storages\n" +
                    "        ON storage.orders.storageId = storage.storages.id JOIN storage.user ON storage.orders.userId = storage.user.id;";
            ResultSet rs = statement.executeQuery(command);
            try{
                while (rs.next()) {

                    Orders orders1= new Orders();

                    orders1.setNameOfOrderProduct(rs.getString("nameOfProduct"));
                    orders1.setNumberOfOrderProduct(rs.getDouble("numberOfProduct"));
                    orders1.setUnitOfOrderProduct(rs.getString("unitOfProduct"));
                    orders1.setCost(rs.getDouble("cost"));
                    orders1.setComments(rs.getString("comments"));
                    orders1.setCode(rs.getString("code"));
                    orders1.setNameCustomer(rs.getString("name"));

                    orders.add(orders1);

                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
            finally {
                rs.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

}
