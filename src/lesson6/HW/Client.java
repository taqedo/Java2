package lesson6.HW;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String ADRR = "localhost";
    private final int PORT = 9393;
    private Socket socket = null;

    public Client() {
        connect();
    }

    public void connect() {
        try {
            socket = new Socket(ADRR, PORT);
            final Scanner in = new Scanner(socket.getInputStream());
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            final Scanner console = new Scanner(System.in);

            Thread t1 = new Thread(() -> {
                while (true) {
                    String inStr = in.nextLine();
                    if (inStr.equalsIgnoreCase("/end")) {
                        System.out.println("Server: " + inStr);
                        System.out.println("t1 is dead");
                        out.println("/end");
                        break;
                    }
                    System.out.println("Server: " + inStr);
                }

            });
            t1.start();

            Thread t2 = new Thread(() -> {
                while (true) {
                    String outStr = console.nextLine();
                    out.println(outStr);
                    if (outStr.equalsIgnoreCase("/end") || !t1.isAlive()) {
                        System.out.println("t2 is dead");
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
        }
    }
    public static void main(String[] args) {
        new Client();
    }
}
