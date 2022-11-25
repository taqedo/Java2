package lesson6.HW;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Scanner scanner = new Scanner(System.in);
    private final String ADRR = "localhost";
    private final int PORT = 9393;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        connect();
    }

    public void connect() {
        try {
            socket = new Socket(ADRR, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                while (true){
                    try {
                        String inStr = in.readUTF();
                        if (inStr.equalsIgnoreCase("/end")){
                            break;
                        }
                        System.out.println(inStr);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }try {
                    out.writeUTF("/end");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();

            new Thread(() -> {
                while (true){
                    try {
                        String outStr = scanner.nextLine();
                        out.writeUTF("client: " + outStr);
                        if (outStr.equalsIgnoreCase("/end") ){
                            break;
                        }
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
