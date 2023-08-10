package gui.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import gui.WelcomePage;

public class AdminGUI extends JFrame implements ActionListener {
    public static void main(String[] args) {
    }

    JPanel aside;
    JButton regProductList;
    JButton disProductList;
    JButton impProductList;
    JButton editEmployees;
    JButton exit;

    JPanel rightSide;
    Container c = this.getContentPane();

    public AdminGUI() {
        this.setResizable(false);
        this.setIconImage(new ImageIcon("./assets/images/logo120px.png").getImage());
        this.setLayout(null);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        aside = new JPanel();
        aside.repaint();
        aside.setLayout(null);
        aside.setBounds(0, 0, 280, 720);
        aside.setBackground(new Color(52, 131, 235));

        JLabel loginType = new JLabel("Admin Panel");
        loginType.setForeground(Color.WHITE);
        loginType.setFont(new Font("ROBOTO", Font.BOLD, 24));
        loginType.setBounds(60, 50, 200, 100);
        aside.add(loginType);

        regProductList = new JButton("Regular Products");
        disProductList = new JButton("Discounted Products");
        impProductList = new JButton("Imported Products");
        editEmployees = new JButton("All Products");
        exit = new JButton("Exit");

        regProductList.setBounds(0, 170, 280, 25);
        disProductList.setBounds(0, 205, 280, 25);
        impProductList.setBounds(0, 240, 280, 25);
        editEmployees.setBounds(0, 275, 280, 25);
        exit.setBounds(0, 500, 280, 25);

        regProductList.setBackground(new Color(52, 131, 235));
        disProductList.setBackground(new Color(52, 131, 235));
        impProductList.setBackground(new Color(52, 131, 235));
        editEmployees.setBackground(new Color(52, 131, 235));
        exit.setBackground(new Color(52, 131, 235));

        regProductList.setBorderPainted(false);
        disProductList.setBorderPainted(false);
        impProductList.setBorderPainted(false);
        editEmployees.setBackground(new Color(52, 131, 235));
        exit.setBorderPainted(false);

        regProductList.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        disProductList.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        impProductList.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        editEmployees.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        exit.setFont(new Font("ROBOTO", Font.BOLD, 16));

        regProductList.setForeground(Color.WHITE);
        disProductList.setForeground(Color.WHITE);
        impProductList.setForeground(Color.WHITE);
        editEmployees.setForeground(Color.WHITE);
        exit.setForeground(Color.WHITE);

        aside.add(regProductList);
        aside.add(disProductList);
        aside.add(impProductList);
        // aside.add(editEmployees);
        aside.add(exit);

        regProductList.addActionListener(this);
        impProductList.addActionListener(this);
        disProductList.addActionListener(this);
        exit.addActionListener(this);

        c.add(aside);

        rightSide = new JPanel();
        rightSide.setLayout(null);
        rightSide.setBounds(280, 0, 1000, 720);
        c.add(rightSide);

        rightSide.removeAll();
        rightSide.repaint();
        RegularProductView regularProductView = new RegularProductView();
        regularProductView.setBounds(0, 0, 1000, 720);
        rightSide.add(regularProductView);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            this.dispose();
            WelcomePage welcomePage = new WelcomePage();
            welcomePage.setSize(1280, 720);
            welcomePage.setLocationRelativeTo(null);
            welcomePage.setResizable(false);
            welcomePage.setVisible(true);
            // System.exit(0);
        }
        if (e.getSource() == regProductList) {
            rightSide.removeAll();
            rightSide.repaint();
            RegularProductView newProductView = new RegularProductView();
            newProductView.setBounds(0, 0, 1000, 720);
            rightSide.add(newProductView);
        } else if (e.getSource() == impProductList) {
            rightSide.removeAll();
            rightSide.repaint();
            ImportedProductView newProductView = new ImportedProductView();
            newProductView.setBounds(0, 0, 1000, 720);
            rightSide.add(newProductView);
        } else if (e.getSource() == disProductList) {
            rightSide.removeAll();
            rightSide.repaint();
            DiscountProductView newProductView = new DiscountProductView();
            newProductView.setBounds(0, 0, 1000, 720);
            rightSide.add(newProductView);
        }
    }

}
