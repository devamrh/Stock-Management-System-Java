package gui.guest;

import javax.swing.*;

import gui.WelcomePage;

import java.awt.*;
import java.awt.event.*;



public class ShowCase extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new ShowCase();
    }

    JButton allBtn;
    JButton regularBtn;
    JButton discountBtn;
    JButton importedBtn;

    public ShowCase() {
        this.setIconImage(new ImageIcon("./assets/images/logo120px.png").getImage());
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        
        

        JButton titleBtn = new JButton("Choose any Options");
        titleBtn.setBounds(450, 140, 400, 60);
        titleBtn.setOpaque(false);
        titleBtn.setContentAreaFilled(false);
        titleBtn.setBorderPainted(false);
        titleBtn.setFont(new Font("Arial", Font.BOLD, 36));
        titleBtn.setFocusPainted(false);
        titleBtn.setForeground(new Color(69, 106, 227));
        this.add(titleBtn);
        
        

        regularBtn = new JButton("Regular Products");
        regularBtn.setBorderPainted(false);
        regularBtn.setBounds(540, 280, 200, 40);
        regularBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        regularBtn.setBackground(new Color(69, 106, 227));
        regularBtn.setForeground(Color.WHITE);
        this.add(regularBtn);

        discountBtn = new JButton("Discount Products");
        discountBtn.setBorderPainted(false);
        discountBtn.setBounds(540, 340, 200, 40);
        discountBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        discountBtn.setBackground(new Color(69, 106, 227));
        discountBtn.setForeground(Color.WHITE);
        this.add(discountBtn);

        importedBtn = new JButton("Imported Products");
        importedBtn.setBorderPainted(false);
        importedBtn.setBounds(540, 400, 200, 40);
        importedBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        importedBtn.setBackground(new Color(69, 106, 227));
        importedBtn.setForeground(Color.WHITE);
        this.add(importedBtn);

        allBtn = new JButton("Exit");
        allBtn.setBorderPainted(false);
        allBtn.setBounds(540, 460, 200, 40);
        allBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        allBtn.setBackground(new Color(69, 106, 227));
        allBtn.setForeground(Color.WHITE);

        allBtn.addActionListener(this);
        regularBtn.addActionListener(this);
        discountBtn.addActionListener(this);
        importedBtn.addActionListener(this);
        this.add(allBtn);

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == regularBtn){
            new DisplayPanel("./files/itemRegularList.sms");
        }else if(e.getSource() == discountBtn){
            new DisplayPanel("./files/itemDiscountList.sms");
        }else if(e.getSource() == importedBtn){
            new DisplayPanel("./files/itemImportedList.sms");
        }else if(e.getSource() == allBtn){
            this.dispose();
            WelcomePage welcomePage = new WelcomePage();
            welcomePage.setSize(1280, 720);
            welcomePage.setLocationRelativeTo(null);
            welcomePage.setResizable(false);
            welcomePage.setVisible(true);
        }
    }
}
