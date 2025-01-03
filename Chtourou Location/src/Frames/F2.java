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

public class F2 extends JFrame implements ActionListener {
    JButton button;
    JTextField emailTextField;
    JPasswordField passTextField;
    public F2() {
        this.setSize(525, 400);
        this.setTitle("LogIn");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0xFFFFFF));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0xFFFFFF));
        panel.setBounds(0, 30, 500, 50);
        JLabel label = new JLabel("Login to your account");
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

        //SignIn
        JLabel clickableText = new JLabel("<html><a href='#'>Click here to create an account</a></html>", SwingConstants.CENTER);
        clickableText.setBounds(0,250,500,50);
        clickableText.setHorizontalTextPosition(SwingConstants.CENTER);
        clickableText.setFont(new Font("Poppins", Font.PLAIN, 16));
        clickableText.setForeground(Color.BLUE);
        clickableText.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(clickableText);

        clickableText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goToSignIn();
            }
        });
        //

        button = new JButton();
        button.setText("LogIn");
        button.setBounds(150,300,200,50);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setFont(new Font("Poppins", Font.BOLD, 20));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.add(button);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if ((Objects.equals(emailTextField.getText(), "")) || (Objects.equals(passTextField.getText(), ""))){
                System.out.println("Entered");
                JOptionPane.showMessageDialog(null, "Please enter your email address or password");
            }
            else {
                JDBC jdbc = new JDBC();
                User user = jdbc.LogIn(emailTextField.getText(),passTextField.getText());
                if (user == null) {JOptionPane.showMessageDialog(null, "Please make sure you are using the right information or create a new account");}
                else {
                    this.dispose();
                    F4 fenetre4 = new F4(user);
                }
            }
        }
    }

    public void goToSignIn() {
        this.dispose();
        F3 fenetre3 = new F3();
    }
}
