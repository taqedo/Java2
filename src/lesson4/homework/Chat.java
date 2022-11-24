package lesson4.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Chat extends JFrame {

    public Chat(String nickname){
        String nick = nickname;
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
        nickArea.append(nickname);
        JTextArea logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane logAScroll = new JScrollPane(logArea);
        topPanel.add(logAScroll);
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(Color.WHITE);

        JTextField msgFld = new JFormattedTextField("Введите сообщение...");
        msgFld.setPreferredSize(new Dimension(550, 30));
        msgFld.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(msgFld.getText().trim().toLowerCase().equals("введите сообщение...")){
                    msgFld.setText("");
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(msgFld.getText().trim().toLowerCase().equals("")){
                    msgFld.setText("Введите сообщение...");
                }

            }
        });

        JButton send = new JButton("Send message");


        botPanel.add(msgFld);
        botPanel.add(send, BorderLayout.EAST);

            send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String f = msgFld.getText();
                    if(!(f.equals(""))) {
                        String timeColonPattern = "HH:mm:ss";
                        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
                        String time = timeColonFormatter.format(LocalTime.now());

                        logArea.append(time + " " + nick + ": " + msgFld.getText() + "\n");
                        msgFld.setText(null);
                        msgFld.grabFocus();
                    }
                }
            });

            msgFld.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    String timeColonPattern = "HH:mm:ss";
                    DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
                    String time = timeColonFormatter.format(LocalTime.now());
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String f = msgFld.getText();
                        if(!(f.equals(""))) {
                            logArea.append(time + " " + nick + ": " + msgFld.getText() + "\n");
                            msgFld.setText(null);
                            msgFld.grabFocus();
                        }
                    }
                }
            });










        setVisible(true);
    }

}
