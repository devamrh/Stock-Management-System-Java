package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import gui.admin.AdminGUI;
import gui.guest.DisplayPanel;
import gui.guest.ShowCase;

public class WelcomePage extends JFrame implements ActionListener {
    private JButton guestBtn;
    private JLabel coverHolder;
    private JLabel SMS;
    private JLabel SMSFull;
    private JLabel welcomeText;
    private JLabel grayText;
    private JLabel UsernameLabel;
    private JTextField usernameField;
    private JLabel passLabel;
    private JPasswordField passField;
    private JButton loginBtn;
    private JLabel breaker;
    private JLabel warningLabel;

    private Container c = this.getContentPane();
    private ImageIcon backCover = new ImageIcon("./assets/images/WelcomeCover.png");

    public WelcomePage() {
        c.setLayout(null);
        this.setIconImage(new ImageIcon("./assets/images/logo120px.png").getImage());
        coverHolder = new JLabel();
        coverHolder.setIcon(backCover);
        coverHolder.setBounds(0, 0, 1280, 720);

        // ******SMS LOGO START**************
        SMS = new JLabel("SMS");
        SMS.setFont(new Font("ROBOTO", Font.BOLD, 72));
        SMS.setBounds(200, 70, 360, 72);

        SMSFull = new JLabel("Shop Management System");
        SMSFull.setFont(new Font("ROBOTO", Font.BOLD, 13));
        SMSFull.setBounds(200, 150, 360, 13);
        // ******SMS LOGO END**************

        // ******WELCOME TEXT START**************
        welcomeText = new JLabel("Welcome Back");
        welcomeText.setFont(new Font("ROBOTO", Font.BOLD, 42));
        welcomeText.setBounds(700, 70, 360, 48);

        grayText = new JLabel("Login to continue.");
        grayText.setFont(new Font("ROBOTO", Font.ITALIC, 16));
        grayText.setForeground(Color.GRAY);
        grayText.setBounds(700, 125, 360, 48);
        // ******WELCOME TEXT END**************

        // ******USERNAME FIELD START**************
        UsernameLabel = new JLabel("Username:");
        UsernameLabel.setFont(new Font("ROBOTO", Font.PLAIN, 24));
        UsernameLabel.setForeground(Color.black);
        UsernameLabel.setBounds(700, 210, 360, 26);

        usernameField = new JTextField("", 40);
        usernameField.setOpaque(true);
        usernameField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        usernameField.setForeground(Color.BLACK);
        usernameField.setBounds(700, 260, 420, 40);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        // ******USERNAME FIELD END**************

        // ******PASSWORD FIELD START**************
        passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("ROBOTO", Font.PLAIN, 24));
        passLabel.setForeground(Color.black);
        passLabel.setBounds(700, 340, 360, 26);

        passField = new JPasswordField("", 40);
        passField.setOpaque(true);
        passField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        passField.setForeground(Color.BLACK);
        passField.setBounds(700, 380, 420, 40);
        passField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        // ******PASSWORD FIELD END**************

        loginBtn = new JButton("LOGIN");
        loginBtn.setBorderPainted(false);
        loginBtn.setBounds(700, 460, 420, 40);
        loginBtn.setBackground(new Color(52, 161, 235));
        loginBtn.setForeground(Color.WHITE);

        breaker = new JLabel("Or,");
        breaker.setBounds(900, 520, 48, 16);

        guestBtn = new JButton("LOGIN AS GUEST");
        guestBtn.setBorderPainted(false);
        guestBtn.setBounds(700, 560, 420, 40);
        guestBtn.setForeground(Color.WHITE);
        guestBtn.setBackground(new Color(52, 235, 150));

        warningLabel = new JLabel("");
        warningLabel.setFont(new Font("ROBOTO", Font.BOLD, 16));
        warningLabel.setForeground(Color.RED);
        warningLabel.setBounds(700, 150, 300, 50);

        c.add(warningLabel);
        c.add(breaker);
        c.add(guestBtn);
        c.add(loginBtn);
        c.add(SMSFull);
        c.add(SMS);
        c.add(passField);
        c.add(passLabel);
        c.add(usernameField);
        c.add(UsernameLabel);
        c.add(grayText);
        c.add(welcomeText);
        c.add(coverHolder);

        loginBtn.addActionListener(this);
        guestBtn.addActionListener(this);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guestBtn) {
           ShowCase showCase = new ShowCase();
            this.dispose();
        }
        if (e.getSource() == loginBtn) {
            // AdminGUI adminGUI = new AdminGUI();
            // adminGUI.setDefaultCloseOperation(AdminGUI.EXIT_ON_CLOSE);
            // // ShowCase showCase = new ShowCase();
            // // showCase.setDefaultCloseOperation(ShowCase.EXIT_ON_CLOSE);
            // this.dispose();

            String userString = usernameField.getText();
            // String passString=String.valueOf(passField.getPassword());

            System.out.println(userString);
            if (userString.equals("") && String.valueOf(passField.getPassword()).equals("")) {
                warningLabel.setText("Password cannot be empty.");
            } else if (userString.equals("root") && String.valueOf(passField.getPassword()).equals("1234#")) {
                warningLabel.setText("");
                AdminGUI adminGUI = new AdminGUI();
                adminGUI.setDefaultCloseOperation(AdminGUI.EXIT_ON_CLOSE);
                // ShowCase showCase = new ShowCase();
                // showCase.setDefaultCloseOperation(ShowCase.EXIT_ON_CLOSE);
                this.dispose();
            } else {
                warningLabel.setText("Incorrect credentials.");
            }
        }
    }
}