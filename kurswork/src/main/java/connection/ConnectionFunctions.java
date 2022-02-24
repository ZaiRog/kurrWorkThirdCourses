package connection;

public interface ConnectionFunctions {

    void connectionCreated(ConnectionInfo c);

    void connectionClose(ConnectionInfo c);

    void connectionException(Exception ex);
}
