package gui.admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import utils.ProductManager;
import utils.Validation;
import utils.ImportedProduct;
import utils.Product;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;

public class ImportedProductView extends JPanel implements ActionListener, ItemListener {
    private String filename = "./files/itemImportedList.sms";
    String idParseFileName = "./files/staticScoreItem.sms";

    private ProductManager productManager = new ProductManager();

    DefaultTableModel tableModel;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextField idField;
    private JTextField nameField;
    private Product[] productArray;
    private Product[] filteredArray;
    private JComboBox<String> SortType;
    private JComboBox<String> filterType;
    private JLabel priceLabel;
    private JTextField priceMin;
    private JTextField priceMax;
    private JButton nameSearchBtn;
    private JButton idSearchBtn;
    private JButton priceSeachBtn;
    private String[] tableTitle = { "ID", "Product Name", "Type", "Stocks", "Price with VAT", "VAT (%)", "Price", "CreatedAt" };
    private JTable table;
    private JScrollPane jScrollPane;
    private String[] ArrFilterType;
    private JLabel imgLabel;

    private JButton updateBtn;
    private JButton createBtn;
    private JButton deleteBtn;
    private JButton updateConfirmBtn;
    private JButton createConfirmBtn;
    private JTextField nameCreateField;
    private JTextField priceCreateField;
    private JTextField stockCreateField;
    private JTextField typeCreateField;
    private JTextField nameUpdateField;
    private JTextField priceUpdateField;
    private JTextField stockUpdateField;
    private JTextField percentageCreateField;
    private JTextField percentageUpdateField;
    private JLabel UpdateIdHolder;

    private JButton fileChooser;
    private String SelectedFilePathHolder = new String();
    private String filePathHolder = new String();

    public ImportedProductView() {
        jScrollPane = new JScrollPane();
        table = new JTable();
        this.add(jScrollPane);
        this.add(table);
        productArray = productManager.getObjectFromText(filename);
        this.setLayout(null);
        // this.setBounds(0,0,1000,720);
        this.setBackground(Color.WHITE);

        // ----------------------Search by ID Start--------------------
        idLabel = new JLabel("Search by ID: ");
        idLabel.setBounds(10, 10, 100, 25);
        idLabel.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        this.add(idLabel);
        idField = new JTextField();
        idField.setBounds(110, 10, 200, 25);
        idField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        this.add(idField);

        idSearchBtn = new JButton("Search");
        idSearchBtn.setBounds(320, 10, 80, 25);
        idSearchBtn.setBackground(new Color(52, 128, 235));
        idSearchBtn.setForeground(Color.WHITE);
        idSearchBtn.setBorderPainted(false);
        this.add(idSearchBtn);
        idSearchBtn.addActionListener(this);

        // ----------------------Search by ID End-----------------------

        // ----------------------Search by Name Start---------------------
        nameLabel = new JLabel("Search By Name: ");
        nameLabel.setBounds(450, 10, 140, 25);
        nameLabel.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        this.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(580, 10, 200, 25);
        nameField.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        this.add(nameField);

        nameSearchBtn = new JButton("Search");
        nameSearchBtn.setBounds(800, 10, 80, 25);
        nameSearchBtn.setBackground(new Color(52, 128, 235));
        nameSearchBtn.setForeground(Color.WHITE);
        nameSearchBtn.setBorderPainted(false);
        this.add(nameSearchBtn);
        nameSearchBtn.addActionListener(this);

        // ----------------------Search by Name End-----------------------

        // ------------------------SortBy Start --------------------------
        String[] ArrSortType = { "ID [Low to High]", "Name [A-Z]", "Price [Low to High]" };
        SortType = new JComboBox<>(ArrSortType);
        SortType.setBounds(20, 60, 160, 40);
        SortType.setForeground(Color.BLACK);
        SortType.setBackground(Color.white);
        SortType.setSelectedItem("ID [High to Low]");
        this.add(SortType);
        SortType.addItemListener(this);

        // --------------------- SortBy End --------------------------

        // ------------------------FilterBy Start --------------------------
        ArrFilterType = productManager.ProductItemTypeList(productArray);
        filterType = new JComboBox<>(ArrFilterType);
        filterType.setBounds(220, 60, 160, 40);
        filterType.setForeground(Color.BLACK);
        filterType.setBackground(Color.white);
        filterType.setSelectedItem("All Products");
        this.add(filterType);
        filterType.addItemListener(this);
        // --------------------- FilterBy End --------------------------

        // ------------------------Price Range Start --------------------------
        priceLabel = new JLabel("Search By Price: ");
        priceLabel.setBounds(450, 60, 140, 40);
        priceLabel.setFont(new Font("ROBOTO", Font.PLAIN, 16));
        this.add(priceLabel);

        priceMin = new JTextField("");
        priceMin.setBounds(580, 60, 120, 25);
        this.add(priceMin);

        priceMax = new JTextField("");
        priceMax.setBounds(580, 90, 120, 25);
        this.add(priceMax);

        priceSeachBtn = new JButton("Filter");
        priceSeachBtn.setBounds(720, 60, 120, 40);
        this.add(priceSeachBtn);
        priceSeachBtn.setBackground(new Color(52, 128, 235));
        priceSeachBtn.setForeground(Color.WHITE);
        priceSeachBtn.setBorderPainted(false);
        priceSeachBtn.addActionListener(this);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(20, 110, 120, 30);
        this.add(updateBtn);
        updateBtn.setBackground(new Color(3, 173, 252));
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setBorderPainted(false);
        updateBtn.addActionListener(this);

        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(160, 110, 120, 30);
        this.add(deleteBtn);
        deleteBtn.setBackground(new Color(252, 3, 3));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBorderPainted(false);
        deleteBtn.addActionListener(this);

        createBtn = new JButton("Create");
        createBtn.setBounds(300, 110, 120, 30);
        this.add(createBtn);
        createBtn.setBackground(new Color(8, 201, 118));
        createBtn.setForeground(Color.WHITE);
        createBtn.setBorderPainted(false);
        createBtn.addActionListener(this);

        // --------------------- Price Range End --------------------------

        // ----------------------Table Start-----------------------------

        tableRender(productArray);
        // ----------------------Table End-----------------------------
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteBtn) {
            int[] tableIndex = table.getSelectedRows();
            String[] tableID = new String[tableIndex.length];
            Arrays.sort(tableIndex);
            if (tableIndex.length == 0) {
                JOptionPane.showMessageDialog(null, "Please first Select Row(s).", null, JOptionPane.ERROR_MESSAGE);
            }
            if (tableIndex.length != 0) {
                int confirmInt = JOptionPane.showConfirmDialog(null, "Press Yes to Delete.", null,
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (confirmInt == 0) {
                    productArray = productManager.getObjectFromText(filename);
                    for (int j = (tableIndex.length - 1); j >= 0; j--) {
                        int len = tableIndex[j];
                        // System.out.println(len);
                        String id = (String) tableModel.getValueAt(len, 0);
                        tableID[j] = id;
                    }
                    productArray = productManager.getObjectFromText(filename);
                    for (int j = 0; j < tableID.length; j++) {
                        productManager.searchByIdAndDelete(filename, tableID[j], productArray);
                    }
                    productArray = productManager.getObjectFromText(filename);
                    tableRender(productArray);
                }

            }
            // String id = (String) tableModel.getValueAt(x[0], 0);

            // JOptionPane.showMessageDialog(null, "Okay");
        }

        if (e.getSource() == updateBtn) {
            int[] tableIndex = table.getSelectedRows();
            if (tableIndex.length == 0) {
                JOptionPane.showMessageDialog(null, "Please first Select Row(s).", null, JOptionPane.ERROR_MESSAGE);
            } else if (tableIndex.length > 1) {
                JOptionPane.showMessageDialog(null, "You Must Select only one Row.", null, JOptionPane.ERROR_MESSAGE);
            } else if (tableIndex.length == 1) {
                JFrame updateFrame = new JFrame("Update Box");
                updateFrame.setResizable(false);
                // updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                updateFrame.setLayout(null);
                updateFrame.setSize(400, 600);
                updateFrame.setLocationRelativeTo(null);

                JLabel name = new JLabel("Product Name: ");
                name.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                name.setBounds(20, 40, 100, 30);
                updateFrame.add(name);

                nameUpdateField = new JTextField("");
                nameUpdateField.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                nameUpdateField.setBounds(140, 40, 200, 30);
                updateFrame.add(nameUpdateField);
                nameUpdateField.setText(tableModel.getValueAt(tableIndex[0], 1).toString());

                JLabel price = new JLabel("Price: ");
                price.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                price.setBounds(20, 90, 100, 30);
                updateFrame.add(price);

                priceUpdateField = new JTextField("");
                priceUpdateField.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                priceUpdateField.setBounds(140, 90, 200, 30);
                updateFrame.add(priceUpdateField);
                priceUpdateField.setText(tableModel.getValueAt(tableIndex[0], 6).toString());

                JLabel stock = new JLabel("Stocks: ");
                stock.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                stock.setBounds(20, 130, 100, 30);
                updateFrame.add(stock);

                UpdateIdHolder = new JLabel(tableModel.getValueAt(tableIndex[0], 0).toString());

                stockUpdateField = new JTextField("");
                stockUpdateField.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                stockUpdateField.setBounds(140, 130, 200, 30);
                updateFrame.add(stockUpdateField);
                stockUpdateField.setText(tableModel.getValueAt(tableIndex[0], 3).toString());


                JLabel percentage = new JLabel("VAT (%): ");
                percentage.setFont(new Font("ROBOTO", Font.PLAIN, 12));
                percentage.setBounds(20, 170, 100, 30);
                updateFrame.add(percentage);

                percentageUpdateField = new JTextField();
                percentageUpdateField.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                percentageUpdateField.setBounds(140, 170, 200, 30);
                updateFrame.add(percentageUpdateField);
                String percentageStr = tableModel.getValueAt(tableIndex[0], 5).toString();
                percentageUpdateField.setText(percentageStr.substring(0, percentageStr.length()-1));


                updateConfirmBtn = new JButton("Update");
                updateConfirmBtn.setBounds(20, 240, 120, 30);
                updateConfirmBtn.setBackground(new Color(3, 182, 252));
                updateConfirmBtn.setForeground(Color.WHITE);
                updateConfirmBtn.setBorderPainted(false);
                updateFrame.add(updateConfirmBtn);
                updateConfirmBtn.addActionListener(this);

                updateFrame.setVisible(true);

            }
        }

        if (e.getSource() == updateConfirmBtn) {
            String IdGrabber = UpdateIdHolder.getText().toString();
            productArray = productManager.getObjectFromText(filename);
            Product[] productHolder = productManager.searchByID(IdGrabber, productArray);
            productHolder[0].setProductName(nameUpdateField.getText().toString());
            productHolder[0].setStocks(stockUpdateField.getText().toString());
            productHolder[0].setPrice(priceUpdateField.getText().toString());
            productHolder[0].setPercentage(percentageUpdateField.getText());

            productManager.searchByIdAndUpdate(filename, IdGrabber, productArray, productHolder[0]);

            tableRender(productArray);

        }

        if (e.getSource() == createConfirmBtn) {

            String creatingName = nameCreateField.getText().toString();
            String creatingType = typeCreateField.getText().toString();
            String creatingStock = stockCreateField.getText().toString();
            String creatingPrice = priceCreateField.getText().toString();
            String creatingPercenrage = percentageCreateField.getText().toString();

            if (creatingName.equals("") ||
                    creatingName.equals("") ||
                    creatingName.equals("") ||
                    creatingName.equals("") ||
                    SelectedFilePathHolder.equals("")) {
                JOptionPane.showMessageDialog(null, "You must fill up all Fields.");
            } else if (SelectedFilePathHolder.indexOf("png") == -1) {
                JOptionPane.showMessageDialog(null, "You must select PNG Photos.");
            } else {
                String pid = String.valueOf(Validation.idManipulator(idParseFileName));
                productArray = productManager.getObjectFromText(filename);
                Product newProduct = new ImportedProduct(pid, creatingName,
                creatingType, creatingStock, creatingPrice, LocalDate.now().toString(), creatingPercenrage);
                productManager.addNewProduct(newProduct, filename);
                tableRender(productArray);
                
                filePathHolder = "./assets/stocks/" + pid + ".png";
                productArray = productManager.getObjectFromText(filename);
                tableRender(productArray);

                try {
                    File copyfile = new File(SelectedFilePathHolder);
                    File newfile = new File(filePathHolder);
                    Files.copy(copyfile.toPath(), newfile.toPath());
                } catch (Exception err) {
                    System.out.println(err);
                }
            }

        }

        if (e.getSource() == createBtn) {
            JFrame createFrame = new JFrame("Creating Box");
            createFrame.setResizable(false);
            // updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            createFrame.setLayout(null);
            createFrame.setSize(400, 600);
            createFrame.setLocationRelativeTo(null);

            JLabel name = new JLabel("Product Name: ");
            name.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            name.setBounds(20, 40, 100, 30);
            createFrame.add(name);

            nameCreateField = new JTextField("");
            nameCreateField.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            nameCreateField.setBounds(140, 40, 200, 30);
            createFrame.add(nameCreateField);

            JLabel price = new JLabel("Price: ");
            price.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            price.setBounds(20, 90, 100, 30);
            createFrame.add(price);

            priceCreateField = new JTextField("");
            priceCreateField.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            priceCreateField.setBounds(140, 90, 200, 30);
            createFrame.add(priceCreateField);

            JLabel stock = new JLabel("Stocks: ");
            stock.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            stock.setBounds(20, 130, 100, 30);
            createFrame.add(stock);

            stockCreateField = new JTextField("");
            stockCreateField.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            stockCreateField.setBounds(140, 130, 200, 30);
            createFrame.add(stockCreateField);

            JLabel type = new JLabel("Type: ");
            type.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            type.setBounds(20, 180, 100, 30);
            createFrame.add(type);

            typeCreateField = new JTextField("");
            typeCreateField.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            typeCreateField.setBounds(140, 180, 200, 30);
            createFrame.add(typeCreateField);

            imgLabel = new JLabel();
            imgLabel.setBounds(20, 400, 115, 100);
            createFrame.add(imgLabel);

            JLabel percentage = new JLabel("Percentage: ");
            percentage.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            percentage.setBounds(20, 230, 100, 30);
            createFrame.add(percentage);

            percentageCreateField = new JTextField("");
            percentageCreateField.setFont(new Font("ROBOTO", Font.PLAIN, 14));
            percentageCreateField.setBounds(140, 230, 200, 30);
            createFrame.add(percentageCreateField);

            fileChooser = new JButton();
            fileChooser.setText("Choose Your PNG Image...");
            fileChooser.setBounds(20, 300, 320, 30);
            createFrame.add(fileChooser);
            fileChooser.addActionListener(this);

            createConfirmBtn = new JButton("Create");
            createConfirmBtn.setBounds(20, 350, 120, 30);
            createConfirmBtn.setBackground(new Color(3, 182, 252));
            createConfirmBtn.setForeground(Color.WHITE);
            createConfirmBtn.setBorderPainted(false);
            createFrame.add(createConfirmBtn);
            createConfirmBtn.addActionListener(this);

            createFrame.setVisible(true);
        }

        if (e.getSource() == fileChooser) {
            JFileChooser fileSelect = new JFileChooser("./");
            int response = fileSelect.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                SelectedFilePathHolder = fileSelect.getSelectedFile().getAbsolutePath();
                fileChooser.setText(fileSelect.getSelectedFile().getAbsolutePath());
                System.out.println(fileSelect.getSelectedFile().getAbsolutePath());
                ImageIcon imgPreview = new ImageIcon(SelectedFilePathHolder);
                Image imagePreviewTemp = imgPreview.getImage(); // transform it
                Image newImagePreviewTemp = imagePreviewTemp.getScaledInstance(115, 100, java.awt.Image.SCALE_SMOOTH);                                                        // way
                imgPreview = new ImageIcon(newImagePreviewTemp);
                imgLabel.setIcon(imgPreview);
            }
        }

        if (e.getSource() == nameSearchBtn) {
            this.remove(jScrollPane);
            this.repaint();
            productArray = productManager.getObjectFromText(filename);
            String str = nameField.getText();
            productArray = productManager.getObjectFromText(filename);
            if (!String.valueOf(filterType.getSelectedItem()).equals("All Products")) {
                productArray = productManager.filterProductByType(String.valueOf(filterType.getSelectedItem()),
                        productArray);
            } else {
                productArray = productManager.getObjectFromText(filename);

            }
            productArray = productManager.searchByName(str, productArray);
            tableRender(productArray);
        } else if (e.getSource() == idSearchBtn) {
            this.remove(jScrollPane);
            this.repaint();
            productArray = productManager.getObjectFromText(filename);
            String str = idField.getText();
            productArray = productManager.searchByID(str, productArray);
            tableRender(productArray);
        } else if (e.getSource() == priceSeachBtn) {
            String minPriceStr = priceMin.getText();
            String maxPriceStr = priceMax.getText();
            if (minPriceStr.length() >= 1 && maxPriceStr.length() >= 1) {
                try {
                    // warningLabel.setText("");
                    Double.parseDouble(minPriceStr);
                    Double.parseDouble(maxPriceStr);
                    this.remove(jScrollPane);
                    this.repaint();
                    filteredArray = productManager.filteritemByPrice(minPriceStr, maxPriceStr, productArray);
                    tableRender(filteredArray);

                } catch (Exception exp) {
                    // warningLabel.setText("Warning: Enter valid price.");
                }
            }
        }

    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == SortType) {
            this.remove(jScrollPane);
            this.repaint();
            String str = (String) SortType.getSelectedItem();
            if (str.equals("ID [Low to High]")) {
                productArray = productManager.sortedProductsList("ID", productArray);
                tableRender(productArray);
            } else if (str.equals("Name [A-Z]")) {
                productArray = productManager.sortedProductsList("name", productArray);
                tableRender(productArray);
            } else if (str.equals("Price [Low to High]")) {
                productArray = productManager.sortedProductsList("price", productArray);
                tableRender(productArray);
            }
        } else if (e.getSource() == filterType) {
            String str = (String) filterType.getSelectedItem();
            if (str.equals("All Products")) {
                productArray = productManager.getObjectFromText(filename);
                tableRender(productArray);
            } else {
                for (int i = 0; i < ArrFilterType.length; i++) {
                    if (str.equals(ArrFilterType[i])) {
                        productArray = productManager.getObjectFromText(filename);
                        productArray = productManager.filterProductByType(str, productArray);
                        tableRender(productArray);
                    }
                }
            }
        }
    }

    public void tableRender(Product[] productList) {
        this.remove(jScrollPane);
        this.remove(table);
        String[][] itemData = new String[productList.length][tableTitle.length];
        for (int i = 0; i < productList.length; i++) {
            itemData[i][0] = productList[i].getPidNo();
            itemData[i][1] = productList[i].getProductName();
            itemData[i][2] = productList[i].getType();
            itemData[i][3] = productList[i].getStocks();
            itemData[i][4] = productList[i].getPrice();
            itemData[i][5] = productList[i].getPercentage() + "%";
            itemData[i][6] = productList[i].getRealPrice();
            itemData[i][7] = productList[i].getPublishedAt();
        }
        tableModel = new DefaultTableModel(itemData, tableTitle);
        table = new JTable(tableModel);
        table.setRowHeight(60);
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(10, 160, 970, 500);
        table.getTableHeader().setPreferredSize(
                new Dimension(jScrollPane.getWidth(), 60));
        this.add(jScrollPane);
        table.clearSelection();
    }
}