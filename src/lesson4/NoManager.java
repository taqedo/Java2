package lesson4;

import javax.swing.*;

public class NoManager extends JFrame {
    public NoManager() {
        setTitle("Hey");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100,100, 600, 500);



        JPanel panel1 = new JPanel();
        panel1.setLayout(null);


        JButton button = new JButton("Butttton");
        button.setBounds(20, 10, 120, 30);
        panel1.add(button);

        JButton button1 = new JButton("Butttton1");
        button1.setBounds(20, 50, 120, 30);
        panel1.add(button1);

        JButton button2 = new JButton("Butttton1");
        button2.setBounds(20, 90, 120, 30);
        panel1.add(button2);
        add(panel1);

        setVisible(true);

    }
}
