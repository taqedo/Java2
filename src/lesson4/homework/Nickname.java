package lesson4.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Nickname extends JFrame {
    private String nickname;
    public Nickname(){
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

        JTextField nick = new JFormattedTextField();
        nick.setPreferredSize(new Dimension(180, 25));
        nickPane.add(nick, BorderLayout.CENTER);

        JButton lestGo = new JButton("Accept");
        nickPane.add(lestGo, BorderLayout.EAST);

        setVisible(true);

        lestGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String f = nick.getText();
                if (!(f.equals(""))) {
                    nickname = nick.getText();
                    removeAll();
                    new Chat(nickname);
                    dispose();
                }
            }
        });


    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
