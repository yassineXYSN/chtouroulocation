package Frames;

import classes.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class F6 extends JFrame {
    JDBC jdbc;
    JPanel p;
    JScrollPane scrollPane;
    public F6(User user) {
        jdbc = new JDBC();
        this.setSize(510, 700);
        this.setTitle("Chtourou Location");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground( new Color(0xFFFFFF) );
        p = new JPanel();
        scrollPane = new JScrollPane(p);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 500, 700);

        this.add(scrollPane);
        p.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 25));
        p.setBounds(0, 0, 500, 700);
        List<JPanel> cards;
        cards = this.getCards(user);
        for (JPanel card : cards) {
            card.setBackground(Color.lightGray);
            card.setPreferredSize(new Dimension(450, 200));
            p.add(card);
        }

        int rows = (int) Math.ceil(cards.size() / 1.0);
        int preferredHeight = rows * (200 + 25) + 25;
        p.setPreferredSize(new Dimension(500, preferredHeight));


        this.setVisible(true);

    }

    public List<JPanel> getCards(User u){
        List<JPanel> cards = new ArrayList<JPanel>();
        List<User> users = jdbc.getUsers();
        System.out.println(users.size());
        for (User user : users) {
            JPanel card = new JPanel();
            card.setLayout(null);
            card.setPreferredSize(new Dimension(450, 200));
            card.setBorder(BorderFactory.createLineBorder(Color.black));

            //Name
            JPanel namepanel = new JPanel();
            namepanel.setBounds(25, 25, 400, 50);
            namepanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            namepanel.setBackground(new Color(0,0,0,0));
            String username = user.Name + " " + user.LastName;
            if ((Objects.equals(user.Email, u.Email))&&(user.getClass()==u.getClass())){
                username = username + "(You)";
            }
            JLabel namelabel = new JLabel( username );
            namelabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            namepanel.add(namelabel);
            //

            //Email
            JPanel emailpanel = new JPanel();
            emailpanel.setBounds(25, 75, 400, 50);
            emailpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            emailpanel.setBackground(new Color(0,0,0,0));
            JLabel emaillabel = new JLabel(user.Email);
            emaillabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            emailpanel.add(emaillabel);
            //
            //Email
            JPanel addpostpanel = new JPanel();
            addpostpanel.setBounds(25, 125, 200, 50);
            addpostpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            addpostpanel.setBackground(new Color(0,0,0,0));
            JLabel addpostlabel = new JLabel(user.AddressPostal);
            addpostlabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            addpostpanel.add(addpostlabel);
            //
            //Roles or cars rented
            JPanel rolesrentedpanel = new JPanel();
            rolesrentedpanel.setBounds(200, 125, 200, 50);
            rolesrentedpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            rolesrentedpanel.setBackground(new Color(0,0,0,0));
            JLabel rolesrentedlabel ;
            if (user.getClass() == Admin.class){
                rolesrentedlabel = new JLabel("Role admin");
            }
            else{
                Client c = (Client) user;
                rolesrentedlabel = new JLabel(c.NbRents+" cars rented");
            }
            rolesrentedlabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            rolesrentedpanel.add(rolesrentedlabel);
            //



            card.add(namepanel);
            card.add(emailpanel);
            card.add(addpostpanel);
            card.add(rolesrentedpanel);
            cards.add(card);

        }
        return cards;
    }
}
