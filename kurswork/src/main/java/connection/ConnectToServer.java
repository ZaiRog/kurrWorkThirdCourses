package connection;

import javafx.scene.control.Alert;

import java.net.InetAddress;
import java.net.Socket;

public class ConnectToServer implements ConnectionFunctions {

    public static final ConnectToServer instance = new ConnectToServer();

    public static ConnectToServer getInstance(){
        return instance;
    }

    private ConnectToServer(){}

    private static ConnectionInfoList connectionInfoList;

    public ConnectionInfo createConnection(){

        try{
            Socket socket = new Socket(InetAddress.getLocalHost(), ConnectionInfo.PORT);
            connectionCreated(connectionInfoList = new ConnectionInfoList(socket, this));
            return connectionInfoList;
        }catch (Exception ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ошибка подключения к серверу!\n" +
                    "Проверьте его состояние!" );
            alert.showAndWait();
        }
        return connectionInfoList;
    }

    public static ConnectionInfo getConnectionInfo(){
        return connectionInfoList;
    }


    @Override
    public void connectionCreated(ConnectionInfo c){
        System.out.println("Подключено успешно!");
    }

    @Override
    public void connectionClose(ConnectionInfo c){
        System.out.println("Подключение закрыто!");
        c.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Отключение от сервера успешно!");
        alert.showAndWait();
    }

    @Override
    public void connectionException(Exception ex){
        ex.printStackTrace();
    }
}
