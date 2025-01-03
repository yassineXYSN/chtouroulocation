package Frames;

import classes.Car;
import classes.Client;
import classes.User;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class F9 extends JFrame implements ActionListener {
    public Client user;
    Car car;
    JButton button;
    JDateChooser dateChooser1;
    JDateChooser dateChooser2;
    JButton rentbutton;
    public F9(Client user, JButton rentbutton, Car car) {
        this.car = car;
        this.user = user;
        this.rentbutton = rentbutton;
        this.setSize(400, 200);
        this.setTitle("Chtourou Location");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setBackground( new Color(0xFFFFFF) );

        JLabel chooselabel1 = new JLabel("Date of start :");
        chooselabel1.setFont(new Font("Poppins", Font.PLAIN, 20));
        chooselabel1.setBounds(25,25, 150,25);
        this.add(chooselabel1);

        dateChooser1 = new JDateChooser();
        dateChooser1.setDateFormatString("yyyy-MM-dd");
        Calendar calendar1 = Calendar.getInstance();
        dateChooser1.setBounds(25,50,150,40);
        dateChooser1.setDate(calendar1.getTime());
        this.add(dateChooser1);

        JLabel chooselabel2 = new JLabel("Date of end :");
        chooselabel2.setFont(new Font("Poppins", Font.PLAIN, 20));
        chooselabel2.setBounds(200,25, 150,25);
        this.add(chooselabel2);

        dateChooser2 = new JDateChooser();
        dateChooser2.setDateFormatString("yyyy-MM-dd");
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR, 1);
        dateChooser2.setBounds(200,50,150,40);
        dateChooser2.setDate(calendar2.getTime());
        this.add(dateChooser2);

        button = new JButton();
        button.setText("Submit");
        button.setBounds(125,100,150,50);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setFont(new Font("Poppins", Font.PLAIN, 15));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.add(button);

        this.setVisible(true);

    }

    private Date stripTime(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            Date selectedDate1 = dateChooser1.getDate();
            Date selectedDate2 = dateChooser2.getDate();
            if ((selectedDate1 != null)||(selectedDate2 != null)) {
                Date today = new Date();
                today = stripTime(today);

                if (selectedDate1.before(today)) {
                    JOptionPane.showMessageDialog(this, "You can't rent a car for date that has passed!");
                } else if (selectedDate2.before(selectedDate1)) {
                    JOptionPane.showMessageDialog(this, "The end day can't be before the start day!");
                } else{
                    this.dispose();
                    F10 fenetre10 = new F10(rentbutton,dateChooser1,dateChooser2,user,car);
                }
            }
        }
    }
}
