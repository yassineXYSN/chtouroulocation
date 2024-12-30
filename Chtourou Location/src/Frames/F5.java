package Frames;

import classes.Car;
import classes.JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class F5 extends JFrame implements ActionListener {
    List<Car> cars;
    JPanel p;
    JComboBox cb;
    JComboBox cb2;
    JDBC jdbc;
    JScrollPane scrollPane;
    public F5(){
        jdbc = new JDBC();
        this.setSize(1010, 700);
        this.setTitle("Chtourou Location");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().setBackground( new Color(0xFFFFFF) );
        JLabel label = new JLabel();
        ImageIcon image = new ImageIcon(getClass().getResource("/Images/logo.png"));
        label.setIcon(image);
        label.setFont(new Font("Poppins", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setBounds(0,-30,400,200);
        this.add(label);

        String[] choices = {"Search by", "Make", "Availability"};
        cb = new JComboBox<>(choices);
        cb.addActionListener(this);
        cb.setBounds(405, 110, 100, 25);
        this.add(cb);
        p = new JPanel();
        scrollPane = new JScrollPane(p);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 210, 1000, 490);

        this.add(scrollPane);
        p.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 25));
        p.setBounds(0, 155, 1000, 500);
        List<JPanel> cards;
        cards = this.getCards("Search by","");
        for (JPanel card : cards) {
            card.setBackground(Color.lightGray);
            card.setPreferredSize(new Dimension(450, 200));
            p.add(card);
        }

        int rows = (int) Math.ceil(cards.size() / 2.0);
        int preferredHeight = rows * (200 + 25) + 25;
        p.setPreferredSize(new Dimension(1000, preferredHeight));


        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cb) {
            if (cb2 != null){
                this.remove(cb2);
                this.repaint();
                this.setVisible(true);
            }
            if (cb.getSelectedItem().toString().equals("Search by")) {
                p = new JPanel();
                scrollPane = new JScrollPane(p);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setBounds(0, 210, 1000, 490);

                this.add(scrollPane);
                p.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 25));
                p.setBounds(0, 155, 1000, 500);
                List<JPanel> cards;
                cards = this.getCards("Search by","");
                for (JPanel card : cards) {
                    card.setBackground(Color.lightGray);
                    card.setPreferredSize(new Dimension(450, 200));
                    p.add(card);
                }

                int rows = (int) Math.ceil(cards.size() / 2.0);
                int preferredHeight = rows * (200 + 25) + 25;
                p.setPreferredSize(new Dimension(1000, preferredHeight));
                this.setVisible(true);
            }
            else {
                String[] choices = {};
                if (cb.getSelectedItem().toString().equals("Availability")) {
                    choices = new String[]{"Available", "Unavialable"};
                } else if (cb.getSelectedItem().toString().equals("Make")) {
                    choices = jdbc.getMakes().toArray(new String[0]);
                }
                if (cb2 != null){
                    this.remove(cb2);
                }
                cb2 = new JComboBox<>(choices);
                cb2.addActionListener(this);
                cb2.setBounds(510, 110, 100, 25);
                this.add(cb2);
                this.repaint();
                this.setVisible(true);
            }
        }
        if (e.getSource() == cb2) {
            try{
                this.remove(scrollPane);
                this.repaint();
                this.setVisible(true);
            }
            catch (Exception ex){}
            p = new JPanel();
            scrollPane = new JScrollPane(p);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(0, 210, 1000, 490);

            this.add(scrollPane);
            p.setLayout(new FlowLayout(FlowLayout.LEADING, 25, 25));
            p.setBounds(0, 155, 1000, 500);
            List<JPanel> cards;
            cards = this.getCards(cb.getSelectedItem().toString(),cb2.getSelectedItem().toString());
            for (JPanel card : cards) {
                card.setBackground(Color.lightGray);
                card.setPreferredSize(new Dimension(450, 200));
                p.add(card);
            }

            int rows = (int) Math.ceil(cards.size() / 2.0);
            int preferredHeight = rows * (200 + 25) + 25;
            p.setPreferredSize(new Dimension(1000, preferredHeight));
            this.setVisible(true);

        }
    }

    public List<JPanel> getCards(String n,String n2){
        List<JPanel> cards = new ArrayList<JPanel>();
        jdbc = new JDBC();

        if (Objects.equals(n, "Search by")){
            cars = jdbc.getCars();
        }
        else if (n.equals("Make")){
            cars = jdbc.getCarsByMake(n2);
        }
        else if (n.equals("Availability")){
            cars = jdbc.getCarsByAvailability(n2);
        }
        for (Car car : cars) {
            JPanel card = new JPanel();
            card.setLayout(null);
            card.setPreferredSize(new Dimension(450, 200));
            card.setBorder(BorderFactory.createLineBorder(Color.black));

            //Name
            JPanel namepanel = new JPanel();
            namepanel.setBounds(25, 25, 200, 50);
            namepanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            namepanel.setBackground(new Color(0,0,0,0));

            JLabel namelabel = new JLabel(car.Model.MakeName +" "+car.Model.ModelName );
            namelabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            namepanel.add(namelabel);
            //

            //Name
            JPanel colorpanel = new JPanel();
            colorpanel.setBounds(200, 25, 200, 50);
            colorpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            colorpanel.setBackground(new Color(0,0,0,0));

            JLabel colorlabel = new JLabel(car.Color );
            colorlabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            colorpanel.add(colorlabel);
            //

            //Age
            JPanel agepanel = new JPanel();
            agepanel.setBounds(25, 75, 200, 50);
            agepanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            agepanel.setBackground(new Color(0,0,0,0));

            JLabel agelabel = new JLabel("Age : "+car.Age+" years");
            agelabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            agepanel.add(agelabel);
            //

            //Availability
            JPanel avaipanel = new JPanel();
            avaipanel.setBounds(25, 125, 200, 50);
            avaipanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            avaipanel.setBackground(new Color(0,0,0,0));

            JLabel availabel = new JLabel("availabity : "+car.availability);
            availabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            avaipanel.add(availabel);
            //

            //Price
            JPanel pricepanel = new JPanel();
            pricepanel.setBounds(225, 125, 200, 50);
            pricepanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            pricepanel.setBackground(new Color(0,0,0,0));

            JLabel pricelabel = new JLabel(car.Price+" dt/jour");
            pricelabel.setFont(new Font("Poppins", Font.PLAIN, 20));
            pricepanel.add(pricelabel);
            //

            card.add(namepanel);
            card.add(agepanel);
            card.add(avaipanel);
            card.add(pricepanel);
            card.add(colorpanel);
            cards.add(card);

        }
        return cards;
    }
}
