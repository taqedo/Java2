package lesson6.HW;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {


    public Server() {
        ServerSocket server = null;
        Socket socket = null;


        try {
            server = new ServerSocket(9393);
            System.out.println("Server is launched");
            socket = server.accept();
            System.out.println("Client connected");

            final Scanner in = new Scanner(socket.getInputStream());
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            final Scanner console = new Scanner(System.in);

            Thread t1 = new Thread(() -> {
                while (true) {
                    String instr;
                    instr = in.nextLine();
                    if (instr.equalsIgnoreCase("/end")) {
                        System.out.println("client: " + instr);
                        System.out.println("t1 is dead");
                        out.println("/end");
                        break;
                    }
                    System.out.println("client: " + instr);
                }
            });
            t1.start();

            Thread t2 = new Thread(() -> {
                while (true) {
                    String srvMsg = console.nextLine();
                    out.println(srvMsg);
                    if (srvMsg.equalsIgnoreCase("/end") || !t1.isAlive()) {
                        System.out.println("t2 is deadddy :)");
                        break;
                    }
                }
            });
            t2.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    public static void main(String[] args) {
        new Server();
    }
}
