package lesson6.HW;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    Scanner scanner = new Scanner(System.in);

    public Server() {
        ServerSocket server;
        Socket socket;

        try {
            server = new ServerSocket(9393);
            System.out.println("Server is launched");
            socket = server.accept();
            System.out.println("Client connected");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                while (true) {
                    String instr;
                    try {
                        instr = in.readUTF();
                        if (instr.equalsIgnoreCase("/end")) {
                            break;
                        }
                        System.out.println(instr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    out.writeUTF("/end");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                while (true){

                    String srvMsg = scanner.nextLine();
                    try {

                        out.writeUTF("server: " + srvMsg);
                        if (srvMsg.equalsIgnoreCase("/end"))break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }try {
                    out.writeUTF("/end");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public static void main(String[] args) {
        new Server();
    }


}
