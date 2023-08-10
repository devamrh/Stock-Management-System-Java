package gui.admin;

import javax.swing.*;

import utils.Product;
import utils.ProductManager;
import utils.Validation;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;

public class ActionProduct extends JPanel implements ItemListener, ActionListener {
    String filename = "./files/itemList.sms";
    String idParseFileName = "./files/staticScoreItem.sms";

    Product[] productArray;
    Product[] fileteredArray;
    JButton searchBtn;
    JButton updateBtn;
    JButton createBtn;
    JButton deleteBtn;
    JComboBox<String> combo;
    JLabel title;
    String[] optionArr;
    JTextField idSelect;
    JPanel dialogBox;
    ProductManager productManager = new ProductManager();
    JLabel warningLabel;

    // Updaing LightWeight Components
    JLabel itemUpdateName;
    JLabel itemUpdateType;
    JLabel itemUpdateStock;
    JLabel itemUpdatePrice;

    JTextField itemUpdateNameField;
    JTextField itemUpdateTypeField;
    JTextField itemUpdateStockField;
    JTextField itemUpdatePriceField;

    // Createing LightWeight Components
    JLabel itemCreateName;
    JLabel itemCreateType;
    JLabel itemCreateStock;
    JLabel itemCreatePrice;

    JTextField itemCreateNameField;
    JTextField itemCreateTypeField;
    JTextField itemCreateStockField;
    JTextField itemCreatePriceField;

    JButton fileChooser;
    String SelectedFilePathHolder = new String();
    String filePathHolder = new String();

    public ActionProduct() {

        productArray = productManager.getObjectFromText(filename);
        this.setLayout(null);
        title = new JLabel("Select Items:");
        title.setBounds(25, 25, 150, 25);
        title.setFont(new Font("ROBOTO", Font.BOLD, 24));
        this.add(title);

        optionArr = new String[] { "Delete", "Update", "Create" };
        combo = new JComboBox<>(optionArr);
        combo.setSelectedItem("Delete");
        combo.setBackground(Color.white);
        combo.setForeground(Color.BLACK);
        combo.setBounds(25, 60, 150, 40);
        this.add(combo);

        dialogBox = new JPanel();
        dialogBox.setLayout(null);
        dialogBox.setBounds(25, 120, 800, 500);
        // dialogBox.setBackground(Color.LIGHT_GRAY);
        this.add(dialogBox);
        combo.addItemListener(this);
        fileteredArray = new Product[30];
        // START----------------
        dialogBox.removeAll();
        dialogBox.repaint();
        dialogBox.setLayout(null);
        JLabel idLabel = new JLabel("Input ID: ");
        idLabel.setFont(new Font("ROBOTO", Font.PLAIN, 18));
        idLabel.setBounds(0, 0, 80, 25);
        dialogBox.add(idLabel);

        idSelect = new JTextField("");
        idSelect.setBounds(80, 0, 150, 25);
        dialogBox.add(idSelect);
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(0, 40, 80, 25);
        dialogBox.add(deleteBtn);
        deleteBtn.addActionListener(this);

        deleteBtn.setBackground(new Color(28, 159, 235));
        deleteBtn.setForeground(Color.white);

        warningLabel = new JLabel("");
        warningLabel.setBounds(0, 120, 200, 30);
        warningLabel.setFont(new Font("ROBOTO", Font.BOLD, 16));
        warningLabel.setForeground(Color.RED);
        dialogBox.add(warningLabel);

        // END-------------------
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == combo) {
            String str = (String) combo.getSelectedItem();
            if (str.equals("Delete")) {
                dialogBox.removeAll();
                dialogBox.repaint();
                dialogBox.setLayout(null);
                JLabel idLabel = new JLabel("Input ID: ");
                idLabel.setFont(new Font("ROBOTO", Font.PLAIN, 18));
                idLabel.setBounds(0, 0, 80, 25);
                dialogBox.add(idLabel);

                idSelect = new JTextField("");
                idSelect.setBounds(80, 0, 150, 25);
                dialogBox.add(idSelect);
                deleteBtn = new JButton("Delete");
                deleteBtn.setBounds(0, 40, 80, 25);
                deleteBtn.setBackground(new Color(28, 159, 235));
                deleteBtn.setForeground(Color.white);
                dialogBox.add(deleteBtn);
                deleteBtn.addActionListener(this);

                warningLabel = new JLabel("");
                warningLabel.setBounds(0, 120, 200, 30);
                warningLabel.setFont(new Font("ROBOTO", Font.BOLD, 16));
                warningLabel.setForeground(Color.RED);
                dialogBox.add(warningLabel);
            }
            if (str.equals("Update")) {
                dialogBox.removeAll();
                dialogBox.repaint();
                dialogBox.setLayout(null);
                JLabel idLabel = new JLabel("Input ID: ");
                idLabel.setFont(new Font("ROBOTO", Font.PLAIN, 18));
                idLabel.setBounds(0, 0, 80, 25);
                dialogBox.add(idLabel);

                idSelect = new JTextField("");
                idSelect.setBounds(80, 0, 150, 25);
                dialogBox.add(idSelect);

                searchBtn = new JButton("Search");
                searchBtn.setBounds(280, 0, 80, 25);
                dialogBox.add(searchBtn);
                searchBtn.setBackground(new Color(28, 159, 235));
                searchBtn.setForeground(Color.white);
                searchBtn.addActionListener(this);

                warningLabel = new JLabel("");
                warningLabel.setBounds(0, 100, 200, 30);
                warningLabel.setFont(new Font("ROBOTO", Font.BOLD, 16));
                warningLabel.setForeground(Color.RED);
                dialogBox.add(warningLabel);
            }

            if (str.equals("Create")) {
                dialogBox.removeAll();
                dialogBox.repaint();
                dialogBox.setLayout(null);

                createBtn = new JButton("Create");
                createBtn.setBounds(0, 350, 120, 40);
                createBtn.setFont(new Font("ROBOTO", Font.PLAIN, 22));
                createBtn.setBackground(new Color(28, 159, 235));
                createBtn.setForeground(Color.white);
                dialogBox.add(createBtn);
                createBtn.addActionListener(this);

                itemCreateName = new JLabel("Name: ");
                itemCreateName.setBounds(0, 50, 100, 30);
                itemCreateName.setFont(new Font("ROBOTO", Font.PLAIN, 24));
                dialogBox.add(itemCreateName);
                itemCreateNameField = new JTextField("");
                itemCreateNameField.setBounds(120, 50, 150, 30);
                itemCreateNameField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                dialogBox.add(itemCreateNameField);

                itemCreateType = new JLabel("Type: ");
                itemCreateType.setBounds(0, 100, 100, 30);
                itemCreateType.setFont(new Font("ROBOTO", Font.PLAIN, 24));
                dialogBox.add(itemCreateType);
                itemCreateTypeField = new JTextField("");
                itemCreateTypeField.setBounds(120, 100, 150, 30);
                itemCreateTypeField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                dialogBox.add(itemCreateTypeField);

                itemCreateStock = new JLabel("Stocks: ");
                itemCreateStock.setBounds(0, 150, 100, 30);
                itemCreateStock.setFont(new Font("ROBOTO", Font.PLAIN, 24));
                dialogBox.add(itemCreateStock);
                itemCreateStockField = new JTextField("");
                itemCreateStockField.setBounds(120, 150, 150, 30);
                itemCreateStockField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                dialogBox.add(itemCreateStockField);

                itemCreatePrice = new JLabel("Price: ");
                itemCreatePrice.setBounds(0, 200, 100, 30);
                itemCreatePrice.setFont(new Font("ROBOTO", Font.PLAIN, 24));
                dialogBox.add(itemCreatePrice);
                itemCreatePriceField = new JTextField("");
                itemCreatePriceField.setBounds(120, 200, 150, 30);
                itemCreatePriceField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                dialogBox.add(itemCreatePriceField);

                fileChooser = new JButton();
                fileChooser.setText("Choose file...");
                fileChooser.setBounds(0, 250, 150, 25);
                dialogBox.add(fileChooser);
                fileChooser.addActionListener(this);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fileChooser) {
            JFileChooser fileSelect = new JFileChooser("./");
            int response = fileSelect.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                SelectedFilePathHolder = fileSelect.getSelectedFile().getAbsolutePath();
                fileChooser.setText(fileSelect.getSelectedFile().getAbsolutePath());
                System.out.println(fileSelect.getSelectedFile().getAbsolutePath());
            }
        }

        if (e.getSource() == deleteBtn) {
            String str = idSelect.getText();
            // parsingData = arrayManager.searchByName(str, itemData, 0);
            if (str.equals("")) {
                warningLabel.setForeground(Color.RED);
                warningLabel.setText("ID Field Can't be Empty.");
            } else {
                boolean checker = productManager.searchByIdAndDelete("./files/itemList.sms", str, productArray);
                productArray = productManager.getObjectFromText(filename);
                if (checker == true) {
                    warningLabel.setForeground(Color.blue);
                    warningLabel.setText("ID Credential removed.");
                } else {
                    warningLabel.setForeground(Color.RED);
                    warningLabel.setText("Wrong ID.");
                }

            }
        }

        if (e.getSource() == searchBtn) {
            String str = idSelect.getText();

            if (str.equals("")) {
                warningLabel.setForeground(Color.RED);
                warningLabel.setText("ID Field Can't be Empty.");
            }
            if (str.length() != 0) {
                fileteredArray = productManager.searchByID(str, productArray);
                if (fileteredArray.length != 1) {
                    warningLabel.setForeground(Color.RED);
                    warningLabel.setText("Invalid ID.");
                } else if (fileteredArray.length == 1 && fileteredArray[0].getPidNo().equals(str)) {
                    dialogBox.remove(warningLabel);
                    dialogBox.repaint();
                    updateBtn = new JButton("Update");
                    updateBtn.setBounds(0, 350, 120, 40);
                    updateBtn.setFont(new Font("ROBOTO", Font.PLAIN, 22));
                    updateBtn.setBackground(new Color(28, 159, 235));
                    updateBtn.setForeground(Color.white);
                    dialogBox.add(updateBtn);
                    updateBtn.addActionListener(this);

                    itemUpdateName = new JLabel("Name: ");
                    itemUpdateName.setBounds(0, 50, 100, 30);
                    itemUpdateName.setFont(new Font("ROBOTO", Font.PLAIN, 24));
                    dialogBox.add(itemUpdateName);
                    itemUpdateNameField = new JTextField("");
                    itemUpdateNameField.setBounds(120, 50, 150, 30);
                    itemUpdateNameField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                    dialogBox.add(itemUpdateNameField);

                    itemUpdateType = new JLabel("Type: ");
                    itemUpdateType.setBounds(0, 100, 100, 30);
                    itemUpdateType.setFont(new Font("ROBOTO", Font.PLAIN, 24));
                    dialogBox.add(itemUpdateType);
                    itemUpdateTypeField = new JTextField("");
                    itemUpdateTypeField.setBounds(120, 100, 150, 30);
                    itemUpdateTypeField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                    dialogBox.add(itemUpdateTypeField);

                    itemUpdateStock = new JLabel("Stocks: ");
                    itemUpdateStock.setBounds(0, 150, 100, 30);
                    itemUpdateStock.setFont(new Font("ROBOTO", Font.PLAIN, 24));
                    dialogBox.add(itemUpdateStock);
                    itemUpdateStockField = new JTextField("");
                    itemUpdateStockField.setBounds(120, 150, 150, 30);
                    itemUpdateStockField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                    dialogBox.add(itemUpdateStockField);

                    itemUpdatePrice = new JLabel("Price: ");
                    itemUpdatePrice.setBounds(0, 200, 100, 30);
                    itemUpdatePrice.setFont(new Font("ROBOTO", Font.PLAIN, 24));
                    dialogBox.add(itemUpdatePrice);
                    itemUpdatePriceField = new JTextField("");
                    itemUpdatePriceField.setBounds(120, 200, 150, 30);
                    itemUpdatePriceField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                    dialogBox.add(itemUpdatePriceField);

                    itemUpdateNameField.setText(fileteredArray[0].getProductName());
                    itemUpdateTypeField.setText(fileteredArray[0].getType());
                    itemUpdateStockField.setText(fileteredArray[0].getStocks());
                    itemUpdatePriceField.setText(fileteredArray[0].getPrice());

                }
            }
        } else if (e.getSource() == updateBtn) {
            String setName = itemUpdateNameField.getText();
            String setType = itemUpdateTypeField.getText();
            String setStock = itemUpdateStockField.getText();
            String setUpdate = itemUpdatePriceField.getText();

            Product newTempProduct = new Product(fileteredArray[0].getPidNo(),
            setName, setType, setStock, setUpdate, fileteredArray[0].getPublishedAt());

            productManager.searchByIdAndUpdate(filename, fileteredArray[0].getPidNo(), productArray, newTempProduct);
            productArray = productManager.getObjectFromText(filename);
        } else if (e.getSource() == createBtn) {
            if (String.valueOf(itemCreateNameField.getText()).equals("") ||
                    String.valueOf(itemCreateTypeField.getText()).equals("") ||
                    String.valueOf(itemCreateStockField.getText()).equals("") ||
                    String.valueOf(itemCreatePriceField.getText()).equals("") ||
                    SelectedFilePathHolder.equals("")) {
                dialogBox.repaint();
                warningLabel.setForeground(Color.RED);
                warningLabel.setBounds(0, 0, 300, 40);
                warningLabel.setFont(new Font("ROBOTO", Font.BOLD, 20));
                warningLabel.setText("You must fill up all information");
                dialogBox.add(warningLabel);
            } else if (SelectedFilePathHolder.indexOf("png") == -1) {
                dialogBox.repaint();
                warningLabel.setForeground(Color.RED);
                warningLabel.setBounds(0, 0, 300, 40);
                warningLabel.setFont(new Font("ROBOTO", Font.BOLD, 20));
                warningLabel.setText("Please select PNG photo.");
                dialogBox.add(warningLabel);
            } else {
                String pid = String.valueOf(Validation.idManipulator(idParseFileName));
                filePathHolder = "./assets/stocks/" + pid + ".png";

                try {
                    File copyfile = new File(SelectedFilePathHolder);
                    File newfile = new File(filePathHolder);
                    Files.copy(copyfile.toPath(), newfile.toPath());
                } catch (Exception err) {
                    System.out.println(err);
                }

                Product addProduct = new Product(pid,
                itemCreateNameField.getText(), itemCreateTypeField.getText(), itemCreateStockField.getText(),
                itemCreatePriceField.getText(), LocalDate.now().toString());
                productManager.addNewProduct(addProduct, filename);
                productArray = productManager.getObjectFromText(filename);
                dialogBox.repaint();
                warningLabel.setForeground(Color.GREEN);
                warningLabel.setBounds(0, 0, 300, 40);
                warningLabel.setFont(new Font("ROBOTO", Font.BOLD, 20));
                warningLabel.setText("Item Added Succefully");
                dialogBox.add(warningLabel);
            }

        }
    }
}
