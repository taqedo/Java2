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
    private ArrayList<String> nicknames = new ArrayList<>();

    public Server() {
        AuthService.connect();
        clients = new Vector<>();
        Socket socket0 = null;
        ServerSocket sSocket = null;
        try {
            sSocket = new ServerSocket(9323);
            System.out.println("Сервер запущен");

            while (true) {
                socket0 = sSocket.accept();
                System.out.println("Client connected");

                new ClientHandler(this, socket0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket0.close();
                sSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }


    public void broadcast(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public void whisper (String msg, String nickname) {
        for (ClientHandler o : clients) {
            if (o.getNickname().equals(nickname)) {o.sendMsg(msg);}
        }
    }

    public void subscribe(ClientHandler clientHandler){
        clients.add(clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }


    public boolean isNickBusy(String nick){
//        for (ClientHandler o : clients) {
//            if (o.getNickname().equalsIgnoreCase(nick))
              if (nicknames.contains(nick)) return true;
//        }
        return false;
    }

    public ArrayList<String> getNicknames() {
        return nicknames;
    }

    public void addNicknames(String nicknames) {
        this.nicknames.add(nicknames);
    }

    public void remNicknames(String nicknames){
        this.nicknames.remove(nicknames);
    }
}
