package Frames;

import classes.JDBC;
import classes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class F3 extends JFrame implements ActionListener {
    JButton button;
    JTextField emailTextField;
    JTextField nameTextField;
    JTextField lastNameTextField;
    JTextField villeTextField;
    JPasswordField confpassTextField;
    JPasswordField passTextField;
    public F3(){
        this.setSize(1000, 475);
        this.setTitle("SignIn");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground( new Color(0xFFFFFF) );

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xFFFFFF));
        panel.setBounds(0, 30, 1000, 50);
        JLabel label = new JLabel("Create a new account");
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.TOP);
        label.setFont(new Font("Poppins", Font.BOLD, 30));
        panel.add(label);
        this.add(panel);

        //Email
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        emailPanel.setBackground(Color.WHITE);
        emailPanel.setBounds(25, 100, 450, 75);

        JLabel emailLabel = new JLabel("Email:        ");
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 30));

        emailTextField = new JTextField(16);
        emailTextField.setMargin(new Insets(0, 10, 0, 0));
        emailTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        emailTextField.setPreferredSize(new Dimension(250, 40));

        emailPanel.add(emailLabel);
        emailPanel.add(emailTextField);
        this.add(emailPanel);
        //

        //Name
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        namePanel.setBackground(Color.WHITE);
        namePanel.setBounds(500, 100, 450, 75);

        JLabel nameLabel = new JLabel("Name:       ");
        nameLabel.setFont(new Font("Poppins", Font.PLAIN, 30));

        nameTextField = new JTextField(15);
        nameTextField.setMargin(new Insets(0, 10, 0, 0));
        nameTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        nameTextField.setPreferredSize(new Dimension(250, 40));

        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        this.add(namePanel);
        //

        //lastName
        JPanel lastNamePanel = new JPanel();
        lastNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        lastNamePanel.setBackground(Color.WHITE);
        lastNamePanel.setBounds(500, 175, 450, 75);

        JLabel lastNameLabel = new JLabel("Lastname:");
        lastNameLabel.setFont(new Font("Poppins", Font.PLAIN, 30));

        lastNameTextField = new JTextField(15);
        lastNameTextField.setMargin(new Insets(0, 10, 0, 0));
        lastNameTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        lastNameTextField.setPreferredSize(new Dimension(250, 40));

        lastNamePanel.add(lastNameLabel);
        lastNamePanel.add(lastNameTextField);
        this.add(lastNamePanel);
        //

        //Pass
        JPanel passPanel = new JPanel();
        passPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        passPanel.setBackground(Color.WHITE);
        passPanel.setBounds(25, 175, 450, 75);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Poppins", Font.PLAIN, 30));

        passTextField = new JPasswordField(16);
        passTextField.setMargin(new Insets(0, 10, 0, 0));
        passTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        passTextField.setPreferredSize(new Dimension(250, 40));

        passPanel.add(passLabel);
        passPanel.add(passTextField);
        this.add(passPanel);
        //

        //Ville
        JPanel villePanel = new JPanel();
        villePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        villePanel.setBackground(Color.WHITE);
        villePanel.setBounds(500, 250, 450, 75);

        JLabel villeLabel = new JLabel("Ville:           ");
        villeLabel.setFont(new Font("Poppins", Font.PLAIN, 30));

        villeTextField = new JTextField(16);
        villeTextField.setMargin(new Insets(0, 10, 0, 0));
        villeTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        villeTextField.setPreferredSize(new Dimension(250, 40));

        villePanel.add(villeLabel);
        villePanel.add(villeTextField);
        this.add(villePanel);
        //

        //ConfPass
        JPanel confpassPanel = new JPanel();
        confpassPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        confpassPanel.setBackground(Color.WHITE);
        confpassPanel.setBounds(25, 250, 450, 75);

        JLabel confpassLabel = new JLabel("Password:");
        confpassLabel.setFont(new Font("Poppins", Font.PLAIN, 30));

        confpassTextField = new JPasswordField(16);
        confpassTextField.setMargin(new Insets(0, 10, 0, 0));
        confpassTextField.setFont(new Font("Poppins", Font.PLAIN, 16));
        confpassTextField.setPreferredSize(new Dimension(250, 40));

        confpassPanel.add(confpassLabel);
        confpassPanel.add(confpassTextField);
        this.add(confpassPanel);
        //

        //SignIn
        JLabel clickableText = new JLabel("<html><a href='#'>Click here to login to your account</a></html>", SwingConstants.CENTER);
        clickableText.setBounds(0,325,1000,50);
        clickableText.setHorizontalTextPosition(SwingConstants.CENTER);
        clickableText.setFont(new Font("Poppins", Font.PLAIN, 16));
        clickableText.setForeground(Color.BLUE);
        clickableText.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(clickableText);

        clickableText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToLogIn();
            }
        });
        //

        button = new JButton();
        button.setText("SignIn");
        button.setBounds(400,375,200,50);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setFont(new Font("Poppins", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.add(button);

        this.setVisible(true);

    }

    public void goToLogIn() {
        this.dispose();
        F2 fenetre2 = new F2();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if ((Objects.equals(emailTextField.getText(), "")) || (Objects.equals(passTextField.getText(), "")) || (Objects.equals(confpassTextField.getText(), "")) || (Objects.equals(villeTextField.getText(), "")) || (Objects.equals(nameTextField.getText(), "")) || (Objects.equals(lastNameTextField.getText(), ""))) {
                JOptionPane.showMessageDialog(null, "Please fill up all the boxes");
            }

            else if (!Objects.equals(passTextField.getText(), confpassTextField.getText())){
                JOptionPane.showMessageDialog(null, "Passwords do not match");
            }

            else {
                JDBC jdbc = new JDBC();
                User user = jdbc.SignIn(emailTextField.getText(), passTextField.getText(),villeTextField.getText(),nameTextField.getText(),lastNameTextField.getText());
                if (user == null) {JOptionPane.showMessageDialog(null, "You already have an account linked to this email");}
                else {
                    this.dispose();
                    F4 fenetre4 = new F4(user);
                }
            }

        }
    }
}