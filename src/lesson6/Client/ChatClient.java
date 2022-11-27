package lesson6.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ChatClient extends JFrame {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 9323;
    private Socket socket1;
    private JTextArea logArea;
    private JTextField msgFld;
    private JTextArea nickArea;
    DataInputStream in1;
    DataOutputStream out1;

    private JTextArea welcome;
    private JTextField loginFld;
    private JTextField passwordFld;
    private boolean isValid = true;
    String nickname;
    String login;
    String password;

    public ChatClient() {
        nickname();
    }
    public void nickname() {

        setTitle("Nickname");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(700, 450, 400, 200);
        setResizable(false);

        JPanel welcomePane = new JPanel();
        add(welcomePane, BorderLayout.NORTH);
        welcomePane.setBackground(Color.gray);
        welcome = new JTextArea("\n" + "Добро пожаловать в mIRC 2022!" + "\n" + "\n" + "Введите ваш логин и пароль");
        welcome.setBackground(Color.gray);
        welcome.setEditable(false);
        welcomePane.add(welcome, BorderLayout.CENTER);

        JPanel loginPane = new JPanel();
        add(loginPane, BorderLayout.CENTER);
        loginPane.setBackground(Color.gray);

        loginFld = new JFormattedTextField();
        loginFld.setPreferredSize(new Dimension(100, 25));
        loginPane.add(loginFld, BorderLayout.WEST);

        passwordFld = new JFormattedTextField();
        passwordFld.setPreferredSize(new Dimension(100, 25));
        loginPane.add(passwordFld, BorderLayout.CENTER);

        JButton lestGo = new JButton("Accept");
        loginPane.add(lestGo, BorderLayout.AFTER_LAST_LINE);

        setVisible(true);

        lestGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!loginFld.getText().trim().isEmpty() && !passwordFld.getText().trim().isEmpty()) {
                    login = loginFld.getText();
                    password = passwordFld.getText();
                    tryToAuth();


                }
            }

        });

        new Thread(() -> {
            while(isValid){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            loginPane.setVisible(false);
            welcomePane.setVisible(false);


        }).start();
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

        nickArea = new JTextArea();
        nickArea.setEditable(false);
        JScrollPane nickScroll = new JScrollPane(nickArea);
        nickPanel.add(nickScroll);
        nickArea.setBackground(Color.black);
        nickArea.setForeground(Color.pink);

//        tut:
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
    public void sendMsg() {
        if (!msgFld.getText().trim().isEmpty()) {
            String timeColonPattern = "HH:mm:ss";
            DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
            String time = timeColonFormatter.format(LocalTime.now());
            try {
                out1.writeUTF(time + " " + nickname + ": " + msgFld.getText() + "\n");
                msgFld.setText(null);
                msgFld.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Sending msg error!");
            }
        }
    }
    public void openConnection() {
        try {
            socket1 = new Socket(SERVER_ADDR, SERVER_PORT);
            in1 = new DataInputStream(socket1.getInputStream());
            out1 = new DataOutputStream(socket1.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String str = in1.readUTF();
                        if (str.startsWith("/authok")) {
                            isValid = false;
                            break;
                        } else {
                            welcome.setText("Неверный логин/пароль");
                        }
                    }

                    while (true) {
                        String str = in1.readUTF();
                        if (str.startsWith("/new_")) {
                            String[] tokens = str.split("_");
                            nickname = tokens[tokens.length-1];
                            chat(nickname);
                            for (int i = 1; i < tokens.length; i++){
                                nickArea.append(tokens[i] + "\n");
                            }
                            break;
                        }
                    }

                    while (true) {
                        String str = in1.readUTF();
                        if (str.startsWith("/new_")) {
                            String[] tokens = str.split("_");
                            nickArea.setText(null);
                            for (int i = 1; i < tokens.length; i++){
                                nickArea.append(tokens[i] + "\n");
                            }
                        }else logArea.append(str);
                        if (str.equalsIgnoreCase("/end")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void closeConnection() {
        try {
            in1.close();
            out1.close();
            socket1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void tryToAuth(){
        if (socket1 == null || socket1.isClosed()) {
            openConnection();
        }
        try {
            out1.writeUTF("/auth " + login + " " + password);
            loginFld.setText(null);
            passwordFld.setText(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
