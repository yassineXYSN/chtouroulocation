package Frames;

import classes.Car;
import classes.JDBC;
import classes.Make;
import classes.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class F7 extends JFrame implements ActionListener {
    JDBC jdbc;
    JComboBox cbmake;
    JComboBox cbmodels;
    JTextField ageTextField;
    JTextField colorTextField;
    JTextField priceTextField;
    JLabel lblmodel;
    JButton button;
    public F7(){
        jdbc = new JDBC();
        this.setSize(500, 500);
        this.setTitle("Chtourou Location");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground( new Color(0xFFFFFF) );

        JLabel lblmaek = new JLabel("Make :");
        lblmaek.setFont(new Font("Poppins", Font.BOLD, 20));
        lblmaek.setBounds(25,40,200,40);
        this.add(lblmaek);
        String[] makes = jdbc.getMakes().toArray(new String[0]);
        cbmake = new JComboBox<>(makes);
        cbmake.setBounds(25, 80, 200, 40);
        cbmake.addActionListener(this);
        cbmake.setFont(new Font("Poppins", Font.PLAIN, 20));
        this.add(cbmake);

        JLabel lblage = new JLabel("Age :");
        lblage.setFont(new Font("Poppins", Font.BOLD, 20));
        lblage.setBounds(25,140,200,40);
        this.add(lblage);
        ageTextField = new JTextField(16);
        ageTextField.setBounds(25,180,200,40);
        ageTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        this.add(ageTextField);

        JLabel lblcolor = new JLabel("Color :");
        lblcolor.setFont(new Font("Poppins", Font.BOLD, 20));
        lblcolor.setBounds(250,140,200,40);
        this.add(lblcolor);
        colorTextField = new JTextField(16);
        colorTextField.setBounds(250,180,200,40);
        colorTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        this.add(colorTextField);

        JLabel lblprice = new JLabel("Price :");
        lblprice.setFont(new Font("Poppins", Font.BOLD, 20));
        lblprice.setBounds(150,240,200,40);
        this.add(lblprice);
        priceTextField = new JTextField(16);
        priceTextField.setBounds(150,280,200,40);
        priceTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        this.add(priceTextField);

        button = new JButton();
        button.setText("Add car");
        button.setBounds(140,350,220,50);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setFont(new Font("Poppins", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.add(button);

        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbmake){
            if (cbmodels != null){
                this.remove(lblmodel);
                this.remove(cbmodels);
            }
            lblmodel = new JLabel("Model :");
            lblmodel.setFont(new Font("Poppins", Font.BOLD, 20));
            lblmodel.setBounds(250,40,200,40);
            this.add(lblmodel);
            String[] models = jdbc.getModels(cbmake.getSelectedItem().toString()).toArray(new String[0]);
            cbmodels = new JComboBox<>(models);
            cbmodels.setBounds(250, 80, 200, 40);
            cbmodels.addActionListener(this);
            cbmodels.setFont(new Font("Poppins", Font.PLAIN, 20));
            this.add(cbmodels);
            this.repaint();
            this.setVisible(true);
        }
        else if (e.getSource() == button){
            Model model = new Model(cbmodels.getSelectedItem().toString(),cbmake.getSelectedItem().toString());
            int age = 0;
            try {
                age = Integer.parseInt(ageTextField.getText());
            }catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(null, "Please enter a number in age");
            }
            float price = 0;
            try {
                price = Float.parseFloat(priceTextField.getText());
            }catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(null, "Please enter a number in age");
            }
            Car car = new Car(0,model,age,price,colorTextField.getText(),true);
            jdbc.InsertCar(car);
            this.dispose();
        }
    }
}
