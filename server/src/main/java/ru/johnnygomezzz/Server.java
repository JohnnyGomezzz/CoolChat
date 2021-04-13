package ru.johnnygomezzz;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket mySocket;
    private final int PORT = 8189;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен!");

            while (true) {
                mySocket = serverSocket.accept();
                System.out.println("Клиент подключился " + mySocket.getRemoteSocketAddress());
                new ClientHandler(this, mySocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //SQLHandler.disconnect();
            System.out.println("Сервер отключен.");
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
