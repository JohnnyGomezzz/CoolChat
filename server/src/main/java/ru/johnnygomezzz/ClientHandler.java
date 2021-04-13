package ru.johnnygomezzz;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Server myServer;
    private Socket mySocket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Server myServer, Socket mySocket) {
        this.myServer = myServer;
        this.mySocket = mySocket;

        try {
            in = new DataInputStream(mySocket.getInputStream());
            out = new DataOutputStream(mySocket.getOutputStream());

            while (true) {
                String str = in.readUTF();

                if (str.equals("/end")) {
                    out.writeUTF("/end");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент отключился.");
            try {
                mySocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
