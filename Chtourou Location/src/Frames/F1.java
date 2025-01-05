package Frames;

import classes.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class F1 extends JFrame implements ActionListener {
    JButton button;
    public F1(){
        this.setSize(1000, 700);
        this.setTitle("Chtourou Location");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setBackground( new Color(0xFFFFFF) );

        JLabel label = new JLabel();
        ImageIcon image = new ImageIcon(getClass().getResource("/Images/logo.png"));
        label.setIcon(image);
        label.setText("<html><div style='text-align: center;'>Hello and Welcome to Chtourou Location.<br>Here you can find a large selection of cars that you can rent.<br><br><br>Press the button bellow and see what car suits you best</div></html>");
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setFont(new Font("Poppins", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(0,-30,1000,500);
        this.add(label);

        button = new JButton();
        button.setText("Admin or Client");
        button.setBounds(400,501,200,50);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setFont(new Font("Poppins", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.add(button);

        this.setVisible(true);
        JDBC jdbc = new JDBC();
        jdbc.updateRents();

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            this.dispose();
            F2 fenetre2 = new F2();
        }
    }
}
