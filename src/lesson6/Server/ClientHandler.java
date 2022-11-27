package lesson6.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClientHandler {

    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;
    public ClientHandler(Server server, Socket socket) {

        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true){
                        String str = in.readUTF();
                        if (str.startsWith("/auth")){
                            String tokens[] = str.split(" ");
                            String newNick = AuthService.getNickname(tokens[1], tokens[2]);
                            if (newNick != null){
                                String del = "_";
                                sendMsg("/authok");
                                nickname = newNick;
                                server.subscribe(ClientHandler.this);
                                server.addNicknames(nickname);
                                String nickes = String.join(del, server.getNicknames());
                                server.broadcast("/new_" + nickes);
                                break;
                            }
                        }
                    }
                    server.broadcast("Пользователь " + nickname + " вошел в чат!" + "\n");


                    while (true) {
                        String str = in.readUTF();
                        if (str.equalsIgnoreCase("/end")) {
                            out.writeUTF("/serverclosed");
                            break;
                        }
                        server.broadcast(str);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    String del = "_";
                    server.remNicknames(nickname);
                    String nickes = String.join(del, server.getNicknames());
                    server.broadcast("/new_" + nickes);
                    server.broadcast("Пользователь " + nickname + " вышел из чата" + "\n");
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                       out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(ClientHandler.this);

                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }
}
