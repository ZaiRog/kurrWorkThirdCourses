package server;

import communication.Communication;
import communication.Communicationl;
import dao.DAOFactory;
import dao.admin.AdminDAO;
import dao.orders.OrdersDAO;
import dao.storage.StorageDAO;
import dao.user.UserDAO;

import java.io.*;
import java.net.Socket;

public class ConnectionInfol implements ConnectionInfo, Runnable {

    private boolean needToRun = true;

    private Socket socket;

    private InputStream in;
    private OutputStream out;


    public ConnectionInfol(Socket socket, Server connectionFunc) throws Exception{
        super();
        this.socket=socket;
        this.in=socket.getInputStream();
        this.out=socket.getOutputStream();

        Thread t=new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    @Override
    public void send(Communication communication){
        try{
            System.out.println("Сообщение отправлено");
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(communication);
            objOut.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    @Override
    public void close(){
        try{
            this.needToRun=false;
            this.socket.close();
            this.in.close();
            this.out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Соединение закрыто!");
        }
    }

    @Override
    public void run(){

        while(needToRun){
            try{
                int amount=in.available();
                if(amount!=0) {
                    ObjectInputStream objIn = new ObjectInputStream(in);
                    Communication msq = (Communication) objIn.readObject();
                    System.out.println("Мы принимаем подключение с типом:" + msq.getType());
                    readMessage(msq);
                }else {
                    Thread.sleep(300);
                }

            } catch (IOException | InterruptedException | ClassNotFoundException ex){
                ex.printStackTrace();
            }
        }
    }


    public synchronized void readMessage(Communication msq) {

        StorageDAO storageDAO = DAOFactory.getInstance().getStorageDAO();
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        OrdersDAO ordersDAO = DAOFactory.getInstance().getOrdersDAO();
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        System.out.println("Получили тип:" + msq.getType());

        switch (msq.getType()) {
            case Communication.ADD_NEW_PRODUCT: {
                storageDAO.insertStorage(msq.getStorage());

            }

            break;
            case Communication.SHOW_ALL_PRODUCT: {
                Communication mes = new Communicationl(Communication.SHOW_ALL_PRODUCT, storageDAO.getStorageList());
                storageDAO.getStorageList().forEach(System.out::println);
                send(mes);
            }

            break;
            case Communication.DELETE_PRODUCT: {
                storageDAO.deleteStorage(msq.getStorage());
            }
            break;

            case Communication.ADD_NEW_ORDER: {
                ordersDAO.insertOrders(msq.getOrders());

            }

            break;
            case Communication.SHOW_ALL_ORDERS: {
                Communication mes = new Communicationl(Communication.SHOW_ALL_ORDERS, ordersDAO.getOrdersList() , 17.0);
                ordersDAO.getOrdersList().forEach(System.out::println);
                send(mes);
            }

            break;
            case Communication.DELETE_ORDER: {
                ordersDAO.deleteOrders(msq.getOrders());
            }

            break;
            case Communication.ADD_NEW_ADMIN: {
                adminDAO.insertAdmin(msq.getAdmin());
            }

            break;
            case Communication.SHOW_ALL_ADMIN: {

//               adminDAO.getAdminList();
                Communication mes = new Communicationl(adminDAO.getAdminList(), Communication.SHOW_ALL_ADMIN);
                adminDAO.getAdminList().forEach(System.out::println);
                send(mes);
            }

            break;
            case Communication.DELETE_ADMIN: {
                adminDAO.deleteAdmin(msq.getAdmin());
            }
            break;
            case Communication.UPDATE_ADMIN: {
                adminDAO.update(msq.getAdmin());
            }
            break;
            case Communication.ADD_NEW_USER: {
                userDAO.insert(msq.getUser());
            }
            break;
            case Communication.SHOW_ALL_USER: {
                Communication mes = new Communicationl(Communication.SHOW_ALL_USER, userDAO.getUserList(), 1);
                userDAO.getUserList().forEach(System.out::println);
                send(mes);
            }
            break;

            case Communication.DELETE_USER: {
                userDAO.delete(msq.getUser());
            }
            break;
            case Communication.UPDATE_USER: {
                userDAO.update(msq.getUser());
            }
            break;
        }
    }


}
