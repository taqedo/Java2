package lesson6.Client;

import javafx.fxml.Initializable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ChatClient extends JFrame
        implements Initializable {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 9323;
    private Socket socket1;
    private JTextArea logArea;
    private JTextField msgFld;
    private JTextArea nickArea;
    DataInputStream in1;
    DataOutputStream out1;
    String nick;

    public ChatClient(String nick) {
        this.nick = nick;

        initialize(null, null);

        chat(nick);
    }


//    public void openConnection() throws IOException {
//        socket1 = new Socket(SERVER_ADDR, SERVER_PORT);
//        in1 = new DataInputStream(socket1.getInputStream());
//        out1 = new DataOutputStream(socket1.getOutputStream());
//        new Thread(() -> {
//            try {
//                while(true){
//                    String strFromServer = in1.readUTF();
//                    if (strFromServer.equalsIgnoreCase("/end")){
//                        break;
//                    }
//                    logArea.append(strFromServer);
//
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }

    public void closeConnection() {
        try {
            in1.close();
            out1.close();
            socket1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg() {
        if (!msgFld.getText().trim().isEmpty()) {
            String timeColonPattern = "HH:mm:ss";
            DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
            String time = timeColonFormatter.format(LocalTime.now());
            try {
                out1.writeUTF(time + " " + nick + ": " + msgFld.getText() + "\n");
                msgFld.setText(null);
                msgFld.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Sending msg error!");
            }
        }
    }

    public void chat(String nickname) {
        setTitle("IRC 2022");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 400, 800, 600);

        JPanel topPanel = new JPanel();
        JPanel botPanel = new JPanel();
        JPanel nickPanel = new JPanel();
        botPanel.setPreferredSize(new Dimension(1, 40));
        nickPanel.setPreferredSize(new Dimension(180, 1));
        add(topPanel, BorderLayout.CENTER);
        add(botPanel, BorderLayout.SOUTH);
        add(nickPanel, BorderLayout.EAST);
        nickPanel.setBackground(Color.BLUE);
        topPanel.setBackground(Color.BLACK);
        botPanel.setBackground(Color.GREEN);

        topPanel.setLayout(new BorderLayout());
        botPanel.setLayout(new FlowLayout());
        nickPanel.setLayout(new BorderLayout());

        JTextArea nickArea = new JTextArea();
        nickArea.setEditable(false);
        JScrollPane nickScroll = new JScrollPane(nickArea);
        nickPanel.add(nickScroll);
        nickArea.setBackground(Color.black);
        nickArea.setForeground(Color.pink);

//        tut:
        nickArea.append(nickname);


        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane logAScroll = new JScrollPane(logArea);
        topPanel.add(logAScroll);
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(Color.WHITE);

        msgFld = new JFormattedTextField("Введите сообщение...");
        msgFld.setPreferredSize(new Dimension(550, 30));

        msgFld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (msgFld.getText().trim().equalsIgnoreCase("введите сообщение...")) {
                    msgFld.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (msgFld.getText().trim().equalsIgnoreCase("")) {
                    msgFld.setText("Введите сообщение...");
                }
            }
        });

        JButton send = new JButton("Send message");


        botPanel.add(msgFld);
        botPanel.add(send, BorderLayout.EAST);

        send.addActionListener(e -> {
            sendMsg();
        });

        msgFld.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMsg();
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out1.writeUTF("/end");
                    closeConnection();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket1 = new Socket(SERVER_ADDR, SERVER_PORT);
            in1 = new DataInputStream(socket1.getInputStream());
            out1 = new DataOutputStream(socket1.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String strFromServer = in1.readUTF();
                        if (strFromServer.equalsIgnoreCase("/end")) {
                            break;
                        }
                        logArea.append(strFromServer);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
