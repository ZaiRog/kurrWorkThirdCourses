package server;

import communication.Communication;

public interface ConnectionInfo {

    int PORT = 2288;

    void send(Communication communication);

    void close();

}
