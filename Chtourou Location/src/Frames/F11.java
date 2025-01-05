package Frames;

import classes.Client;
import classes.JDBC;
import classes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class F11 extends JFrame implements ActionListener {
    Client client;
    JButton button;
    public F11(User user){
        JDBC jdbc = new JDBC();
        this.client = (Client)user;
        this.setSize(500, 400);
        this.setTitle("Chtourou Location");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setBackground( new Color(0xFFFFFF) );

        JLabel nameLabel = new JLabel("Name : "+client.Name);
        nameLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        nameLabel.setBounds(25,25,150,50);

        JLabel lastnameLabel = new JLabel("Last name : "+client.LastName);
        lastnameLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        lastnameLabel.setBounds(200,25,275,50);

        JLabel emailLabel = new JLabel("Email : "+client.Email);
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        emailLabel.setBounds(25,100,450,50);

        JLabel addLabel = new JLabel("Address : "+client.AddressPostal);
        addLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        addLabel.setBounds(25,150,450,50);

        JLabel nbcarsretedLabel = new JLabel("Number of cars rented : "+ jdbc.getNBRentsByEmail(client.Email));
        nbcarsretedLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        nbcarsretedLabel.setBounds(25,200,450,50);

        button = new JButton();
        button.setText("Cars you are renting");
        button.setBounds(150,275,200,50);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setFont(new Font("Poppins", Font.BOLD, 16));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.add(button);

        this.add(nameLabel);
        this.add(lastnameLabel);
        this.add(emailLabel);
        this.add(addLabel);
        this.add(nbcarsretedLabel);


        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        F12 fenetre12 = new  F12(client);
    }
}
