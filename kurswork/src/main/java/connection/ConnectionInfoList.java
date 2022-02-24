package connection;

import action.adminAction.AdminActionl;
import action.ordersAction.OrdersActionl;
import action.storageAction.StorageActionl;
import action.userAction.UserActionl;
import communication.Communication;
import communication.Communicationl;

import java.io.*;
import java.net.Socket;

public class ConnectionInfoList implements ConnectionInfo, Runnable {

    private boolean needToRun = true;
    private Socket socket;
    private OutputStream out;
    private InputStream in;


    public ConnectionInfoList(Socket socket, ConnectionFunctions connectionFunctions)throws Exception{
        super();
        this.socket=socket;
        out=socket.getOutputStream();
        in=socket.getInputStream();
        Thread t = new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    @Override
    public void send(Communication communication){
        try{
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(communication);
            objOut.flush();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            this.needToRun = false;
            this.socket.close();
            this.in.close();
            this.out.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            System.out.println("Соединение закрыто!");
        }
    }


    @Override
    public void run(){
        System.out.println("Запуск");
        while (needToRun){
            try{
                int amount = in.available();

                if(amount!=0){
                    System.out.println("Получение ответа");
                    System.out.println("Listening socket");

                    ObjectInputStream objIn = new ObjectInputStream(in);

                    Communication msq = (Communicationl)objIn.readObject();
                    System.out.println(""+msq.getType());
                    System.out.println();
                    readMessage(msq);
                }else {
                    Thread.sleep(300);
                }
            }catch (IOException | InterruptedException | ClassNotFoundException ex){
                ex.printStackTrace();
            }
        }
    }

    private void readMessage(Communication msq) {

        if(msq.getType() == Communication.SHOW_ALL_PRODUCT){
            StorageActionl.setStorageList(msq.getStorageList());
        }
        if (msq.getType() == Communication.SHOW_ALL_ADMIN) {
            AdminActionl.setAdminPropertyList(msq.getAdminList());
        }
        if (msq.getType() == Communication.SHOW_ALL_USER) {
            UserActionl.setUserProperties(msq.getUserList());
        }
        if (msq.getType() == Communication.SHOW_ALL_ORDERS) {
            OrdersActionl.setOrdersList(msq.getOrdersList());
            System.out.println(msq.getOrdersList());
        }

    }
}
