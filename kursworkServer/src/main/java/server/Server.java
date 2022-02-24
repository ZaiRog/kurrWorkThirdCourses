package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server implements ConnectionFunc{

    private Set<ConnectionInfo> connectionInfos = new HashSet<ConnectionInfo>();

    private ServerSocket serverSocket;

    public Server() {
        try {
            this.serverSocket = new ServerSocket(ConnectionInfo.PORT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void startServer() {

        System.out.println("Сервер запущен..");

        while (true) {
            try {
                Socket socket = serverSocket.accept();

                connectionCreated(new ConnectionInfol(socket, this));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }


    @Override
    public void connectionCreated(ConnectionInfo c) {
        System.out.println("ConnectionInfo added." + c.toString());

        connectionInfos.add(c);
    }

    @Override
    public void connectionClose(ConnectionInfo c) {
        System.out.println("ConnectionInfo closed");
        connectionInfos.remove(c);
        c.close();
    }

    @Override
    public void connectionException(Exception ex) {
        ex.printStackTrace();
    }
}
