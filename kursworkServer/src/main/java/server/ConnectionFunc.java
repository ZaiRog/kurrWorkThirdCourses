package server;

public interface ConnectionFunc {

    void connectionCreated(ConnectionInfo c);

    void connectionClose(ConnectionInfo c);

    void connectionException(Exception ex);

}
