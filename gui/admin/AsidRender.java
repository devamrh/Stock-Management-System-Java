package gui.admin;


import javax.swing.*;
import java.awt.*;

public class AsidRender {
    

    public void Render(JPanel aside, JButton regularProductList, JButton discountProductList, JButton ImportedProductList, JButton editEmployees, JButton exit) {
        JLabel loginType = new JLabel("Admin Panel");
        loginType.setForeground(Color.WHITE);
        loginType.setFont(new Font("ROBOTO", Font.BOLD, 24));
        loginType.setBounds(60, 50, 200, 100);
        aside.add(loginType);

        regularProductList = new JButton("Regular Product");
        discountProductList = new JButton("Discounted Product");
        ImportedProductList = new JButton("Imported Product");
        editEmployees = new JButton("Edit Employees");
        
        exit = new JButton("Exit");

        regularProductList.setBounds(0, 170, 280, 25);
        discountProductList.setBounds(0, 205, 280, 25);
        ImportedProductList.setBounds(0, 240, 280, 25);
        editEmployees.setBounds(0, 275, 280, 25);
        exit.setBounds(0, 500, 280, 25);

        regularProductList.setBackground(new Color(52, 131, 235));
        discountProductList.setBackground(new Color(52, 131, 235));
        ImportedProductList.setBackground(new Color(52, 131, 235));
        editEmployees.setBackground(new Color(52, 131, 235));
        exit.setBackground(new Color(52, 131, 235));

        regularProductList.setBorderPainted(false);
        discountProductList.setBorderPainted(false);
        ImportedProductList.setBorderPainted(false);
        editEmployees.setBackground(new Color(52, 131, 235));
        exit.setBorderPainted(false);

        regularProductList.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        discountProductList.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        ImportedProductList.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        editEmployees.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        exit.setFont(new Font("ROBOTO", Font.BOLD, 16));

        regularProductList.setForeground(Color.WHITE);
        discountProductList.setForeground(Color.WHITE);
        ImportedProductList.setForeground(Color.WHITE);
        editEmployees.setForeground(Color.WHITE);
        exit.setForeground(Color.WHITE);

        aside.add(regularProductList);
        aside.add(discountProductList);
        aside.add(ImportedProductList);
        aside.add(editEmployees);
        aside.add(exit);
    }

}
