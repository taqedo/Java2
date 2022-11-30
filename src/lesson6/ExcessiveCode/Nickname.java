package lesson6.ExcessiveCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Nickname extends JFrame {
    private String login;
    private String password;

    public Nickname() {

        setTitle("Nickname");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(700, 450, 400, 200);
        setResizable(false);

        JPanel welcomePane = new JPanel();
        add(welcomePane, BorderLayout.NORTH);
        welcomePane.setBackground(Color.gray);
        JTextArea welcome = new JTextArea("\n" + "Добро пожаловать в mIRC 2022!" + "\n" + "\n" + "Введите ваш логин и пароль");
        welcome.setBackground(Color.gray);
        welcome.setEditable(false);
        welcomePane.add(welcome, BorderLayout.CENTER);

        JPanel loginPane = new JPanel();
        add(loginPane, BorderLayout.CENTER);
        loginPane.setBackground(Color.gray);

        JTextField loginFld = new JFormattedTextField();
        loginFld.setPreferredSize(new Dimension(100, 25));
        loginPane.add(loginFld, BorderLayout.WEST);

        JTextField passwordFld = new JFormattedTextField();
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
//                    removeAll();
//                    dispose();

                }
            }

        });
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {return password;}
}
