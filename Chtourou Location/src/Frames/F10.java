package Frames;

import classes.Car;
import classes.Client;
import classes.JDBC;
import classes.User;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class F10 extends JFrame implements ActionListener {
    JButton rentbutton;
    Client client;
    Car car;
    JButton button;
    JPasswordField passTextField;
    JDBC jdbc;
    Date startDate;
    Date endDate;

    F10(JButton rent, JDateChooser dateChooser1, JDateChooser dateChooser2, Client user, Car car) {
        rentbutton = rent;
        client = user;
        startDate = dateChooser1.getDate();
        endDate = dateChooser2.getDate();
        jdbc = new JDBC();

        long differenceInMillis = endDate.getTime() - startDate.getTime();
        long nbdays = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
        this.car = car;
        this.setSize(600, 350);
        this.setTitle("Chtourou Location");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0xFFFFFF));

        JLabel label = new JLabel("<html><div style='text-align: center;'>If you want to confirm your order of renting a "+car.Color+" "+car.Model.MakeName+" "+car.Model.ModelName+" for "+nbdays+" days for a total of " +nbdays* car.Price +" TND write your password in the box bellow</div></html>");
        label.setFont(new Font("Poppins", Font.PLAIN, 20));
        label.setBounds(25, 25, 550, 100);
        this.add(label);

        passTextField = new JPasswordField(16);
        passTextField.setMargin(new Insets(0, 10, 0, 0));
        passTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        passTextField.setBounds(15,150,550,50);

        this.add(passTextField);

        button = new JButton();
        button.setText("Confirm");
        button.setBounds(225, 230, 150, 50);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setFont(new Font("Poppins", Font.PLAIN, 15));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.add(button);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String pass = passTextField.getText();
            if (jdbc.ClientExists(client.Email, pass)) {
                rentbutton.setEnabled(false);
                rentbutton.setText("Rented");
                jdbc.UpdateCarAndUser(car,client,startDate,endDate,pass);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Make sure you entered the right password!");
            }
        }
    }
}