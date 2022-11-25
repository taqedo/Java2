package lesson6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class Server {

    private Vector<ClientHandler> clients;
    private ArrayList<String> nicknames;

    public Server() {
        clients = new Vector<>();
        Socket socket0 = null;
        ServerSocket sSocket = null;
        try {
            sSocket = new ServerSocket(9323);
            System.out.println("Сервер запущен");

            while (true) {
                socket0 = sSocket.accept();
                System.out.println("Client connected");
                clients.add(new ClientHandler(this, socket0));
            }


//            socket0 = sSocket.accept();
//            System.out.println("Клиент подключился");
//            DataInputStream in = new DataInputStream(socket0.getInputStream());
//            DataOutputStream out = new DataOutputStream(socket0.getOutputStream());
//
//            while (true) {
//                String instr = in.readUTF();
//                if (instr.equalsIgnoreCase("/end")) {
//                    break;
//                }
//                out.writeUTF(instr);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket0.close();
                sSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void broadcast(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }
}
