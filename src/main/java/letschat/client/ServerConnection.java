package letschat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConnection {
    private String host;
    private int port;
    private Socket socket = null;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public ServerConnection(String host, int port) {
        this.setHost(host);
        this.setPort(port);
    }

    public boolean connect() throws UnknownHostException, IOException {
        if (this.getSocket() == null) {
            this.setSocket(new Socket(getHost(), getPort()));
        }

        this.socket.setSoTimeout(10000);
        this.setOutputStream(new DataOutputStream(this.getSocket().getOutputStream()));
        this.setInputStream(new DataInputStream(this.getSocket().getInputStream()));
        return true;
    }

    public void close() throws IOException {
        if (this.getSocket() != null) {
            this.getSocket().close();
            this.setSocket(null);
        }

        if (this.getOutputStream() != null) {
            this.getOutputStream().close();
        }

        if (this.getInputStream() != null) {
            this.getInputStream().close();
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }
}
