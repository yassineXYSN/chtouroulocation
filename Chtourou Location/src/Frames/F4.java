package Frames;

import classes.Admin;
import classes.Car;
import classes.JDBC;
import classes.User;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class F4 extends JFrame implements ActionListener {
    User user;
    JButton Seecars;
    JButton Seeusers;
    JButton Addcar;
    JButton Rentcar;
    JButton seeprofile;
    JDBC jdbc;

    public F4(User user){
        this.user = user;
        this.jdbc = new JDBC();
        this.setSize(700, 250);
        this.setTitle("Chtourou Location");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground( new Color(0xFFFFFF) );

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xFFFFFF));
        panel.setBounds(0, 30, 700, 50);
        JLabel label = new JLabel("Hello Mr "+ user.LastName);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.TOP);
        label.setFont(new Font("Poppins", Font.BOLD, 30));
        panel.add(label);
        this.add(panel);

        if (user.getClass() == Admin.class){
            Addcar = new JButton("Add Car");
            Addcar.setBackground(new Color(0xFFFFFF));
            Addcar.setFont(new Font("Poppins", Font.PLAIN, 20));
            Addcar.setFocusable(false);
            Addcar.addActionListener(this);

            Seecars = new JButton("See Cars");
            Seecars.setBackground(new Color(0xFFFFFF));
            Seecars.setFont(new Font("Poppins", Font.PLAIN, 20));
            Seecars.setFocusable(false);
            Seecars.addActionListener(this);

            Seeusers = new JButton("See users");
            Seeusers.setBackground(new Color(0xFFFFFF));
            Seeusers.setFont(new Font("Poppins", Font.PLAIN, 20));
            Seeusers.setFocusable(false);
            Seeusers.addActionListener(this);


            JPanel radiopanel = new JPanel();
            radiopanel.setBackground(new Color(0xFFFFFF));
            radiopanel.setBounds(0, 100, 700 , 50);
            radiopanel.add(Addcar);
            radiopanel.add(Seecars);
            radiopanel.add(Seeusers);
            this.add(radiopanel);
            //
        }
        else{
            Rentcar = new JButton("Rent a car");
            Rentcar.setBackground(new Color(0xFFFFFF));
            Rentcar.setFont(new Font("Poppins", Font.PLAIN, 20));
            Rentcar.setFocusable(false);
            Rentcar.addActionListener(this);

            seeprofile = new JButton("See profile");
            seeprofile.setBackground(new Color(0xFFFFFF));
            seeprofile.setFont(new Font("Poppins", Font.PLAIN, 20));
            seeprofile.setFocusable(false);
            seeprofile.addActionListener(this);


            JPanel radiopanel = new JPanel();
            radiopanel.setBackground(new Color(0xFFFFFF));
            radiopanel.setBounds(0, 100, 700 , 50);
            radiopanel.add(Rentcar);
            radiopanel.add(seeprofile);
            this.add(radiopanel);
        }

        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Seecars) {
            F5 fenetre5 = new F5();
        }
        else if (e.getSource() == Seeusers) {
            F6 fenetre6 = new F6(user);
        }
        else if (e.getSource() == Addcar) {
            F7 fenetre7 = new F7();
        }
        else if (e.getSource() == Rentcar) {
            F8 fenetre8 = new F8(user);
        } else if (e.getSource() == seeprofile) {
            F11 fenetre8 = new F11(user);
        }
    }



}
