package lesson6.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Nickname extends JFrame {
    private String nick;

    public Nickname() {


        setTitle("Nickname");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(700, 450, 400, 200);
        setResizable(false);

        JPanel welcomePane = new JPanel();
        add(welcomePane, BorderLayout.NORTH);
        welcomePane.setBackground(Color.gray);
        JTextArea welcome = new JTextArea("\n" + "Добро пожаловать в mIRC 2022!" + "\n" + "\n" + "Введите ваш никнейм:");
        welcome.setBackground(Color.gray);
        welcome.setEditable(false);
        welcomePane.add(welcome, BorderLayout.CENTER);

        JPanel nickPane = new JPanel();
        add(nickPane, BorderLayout.CENTER);
        nickPane.setBackground(Color.gray);

        JTextField nickFld = new JFormattedTextField();
        nickFld.setPreferredSize(new Dimension(180, 25));
        nickPane.add(nickFld, BorderLayout.CENTER);

        JButton lestGo = new JButton("Accept");
        nickPane.add(lestGo, BorderLayout.EAST);

        setVisible(true);

        lestGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nickFld.getText().trim().isEmpty()) {
                    nick = nickFld.getText();
                    removeAll();
                    new ChatClient(nick);
                    dispose();

                }
            }

        });
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
