package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int SERVER_PORT = 4080;
    public static final List<SocketThread> clients = new ArrayList<>();

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(SERVER_PORT);

            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();

                try {
                    clients.add(new SocketThread(client));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
