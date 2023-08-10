package gui.guest;
// import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import utils.*;

public class DisplayPanel extends JFrame implements ActionListener, ItemListener {
    public static void main(String[] args) {
    }

    private String filename = "./files/itemDiscountList.sms";
    private int cartNum = 0;
    private Product[] productCart = new Product[100];
    private ProductManager productManager = new ProductManager();
    private Product[] productArray;
    private String[] arrItemList;
    private Product[] filteredDataProduct;
    private JPanel[] panelArr;
    private JPanel itemHolder = new JPanel();
    private JPanel pagination = new JPanel();
    private Container c = this.getContentPane();
    private JButton[] PageBtn = new JButton[1];
    private JComboBox<String> sortComboBox;
    private JComboBox<String> filterComboBox;
    private JButton priceFilterBtn;
    private JTextField PriceMin;
    private JTextField PriceMax;
    private JLabel warningLabel;
    private JLabel searchLabel;
    private JButton nameSearchBtn;
    private JTextField nameSearch;
    private JButton myStore;
    private JButton cartlen;
    int totalPrice;
    int totalPriceHolder;

    JTextField fullNameText;
    JTextField fullNumberText;
    JTextField promoText;
    JPanel cartPanel;
    JLabel pAmount;

    public DisplayPanel(String filename) {
        this.filename = filename;
        this.setIconImage(new ImageIcon("./assets/images/logo120px.png").getImage());
        this.setResizable(false);
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);

        myStore = new JButton("My Store");
        myStore.setBounds(90, 10, 120, 30);
        myStore.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        myStore.setBackground(new Color(0, 128, 255));
        myStore.setForeground(Color.WHITE);
        this.add(myStore);
        myStore.addActionListener(this);

        cartlen = new JButton("0");
        cartlen.setFont(new Font("ROBOTO", Font.PLAIN, 12));
        cartlen.setBounds(220, 5, 40, 40);
        cartlen.setOpaque(false);
        cartlen.setContentAreaFilled(false);
        cartlen.setBorderPainted(false);
        cartlen.setText("0");
        this.add(cartlen);

        JButton cartlenBg = new JButton();
        cartlenBg.setBounds(220, 5, 40, 40);
        cartlenBg.setOpaque(false);
        cartlenBg.setContentAreaFilled(false);
        cartlenBg.setBorderPainted(false);
        cartlenBg.setIcon(new ImageIcon("./assets/images/ellipse.png"));

        this.add(cartlenBg);

        productArray = productManager.getObjectFromText(filename);
        arrItemList = productManager.ProductItemTypeList(productArray);
        // System.out.println(Arrays.deepToString(ItemArray));
        this.setLayout(null);

        // this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        c.setBackground(Color.WHITE);
        // Render(ItemArray, 0);

        itemHolder.setBounds(280, 20, 960, 600);
        itemHolder.setBackground(Color.WHITE);
        itemHolder.setLayout(null);

        pagination = new JPanel();
        pagination.setLayout(null);
        pagination.setBackground(Color.WHITE);
        pagination.setBounds(290, 620, 950, 55);

        JPanel aside = new JPanel();
        aside.setLayout(null);
        aside.setBounds(0, 0, 280, 720);
        aside.setBackground(new Color(245, 245, 245));
        AsideRender(aside);

        c.add(pagination);
        c.add(aside);
        c.add(itemHolder);

        Render(productArray, 1);

        getPagination(productArray, pagination);
        this.setVisible(true);
    }

    public void AsideRender(JPanel asidPanel) {
        JLabel title = new JLabel("Guest Login");
        title.setBounds(80, 40, 200, 40);
        title.setFont(new Font("ROBOTO", Font.BOLD, 21));
        asidPanel.add(title);

        /*------------------Sorting By Item title Start------------ */
        JLabel sortLabel = new JLabel("Sort By: ");
        sortLabel.setBounds(20, 240, 200, 30);
        sortLabel.setFont(new Font("ROBOTO", Font.PLAIN, 21));
        asidPanel.add(sortLabel);

        String[] sortList = { "Name [A-Z]", "ID [A-Z]", "Price [Low to High]" };
        sortComboBox = new JComboBox<>(sortList);
        sortComboBox.setBounds(20, 270, 160, 40);
        sortComboBox.setForeground(Color.BLACK);
        sortComboBox.setBackground(Color.white);
        asidPanel.add(sortComboBox);
        sortComboBox.addItemListener(this);
        /*------------------Sorting By Item title End------------ */

        /*------------------Filtering By Item title Start------------ */
        JLabel filterLabel = new JLabel("Filter: ");

        filterLabel.setBounds(20, 100, 200, 40);
        filterLabel.setFont(new Font("ROBOTO", Font.PLAIN, 21));
        asidPanel.add(filterLabel);
        filterComboBox = new JComboBox<>(arrItemList);
        filterComboBox.setBounds(20, 160, 160, 40);
        filterComboBox.setForeground(Color.BLACK);
        filterComboBox.setBackground(Color.white);
        filterComboBox.setSelectedItem("All Products");
        asidPanel.add(filterComboBox);
        filterComboBox.addItemListener(this);
        /*------------------Filtering By Item title End------------ */

        /* ------------------Filtering by Price Range Start -------- */
        JLabel filterLabelForPrice = new JLabel("Price Range: ");
        filterLabelForPrice.setBounds(20, 350, 200, 40);
        filterLabelForPrice.setFont(new Font("ROBOTO", Font.PLAIN, 21));
        asidPanel.add(filterLabelForPrice);

        JLabel PriceMinLabel = new JLabel("Min: ");
        PriceMinLabel.setBounds(20, 400, 40, 20);
        PriceMinLabel.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        asidPanel.add(PriceMinLabel);

        PriceMin = new JTextField("");
        PriceMin.setBounds(70, 400, 130, 20);
        PriceMin.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        asidPanel.add(PriceMin);

        JLabel PriceMaxLabel = new JLabel("Max: ");
        PriceMaxLabel.setBounds(20, 440, 200, 20);
        PriceMaxLabel.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        asidPanel.add(PriceMaxLabel);

        PriceMax = new JTextField("");
        PriceMax.setBounds(70, 440, 130, 20);
        PriceMax.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        asidPanel.add(PriceMax);

        priceFilterBtn = new JButton("Filter");
        priceFilterBtn.setBounds(20, 480, 120, 30);
        priceFilterBtn.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        priceFilterBtn.setBackground(new Color(0, 128, 255));
        priceFilterBtn.setBorderPainted(false);
        priceFilterBtn.setForeground(Color.WHITE);
        asidPanel.add(priceFilterBtn);
        priceFilterBtn.addActionListener(this);
        /* ------------------Filtering by Price Range End -------- */

        warningLabel = new JLabel("");
        warningLabel.setForeground(Color.RED);
        warningLabel.setBounds(20, 10, 200, 20);
        warningLabel.setFont(new Font("ROBOTO", Font.BOLD, 14));
        asidPanel.add(warningLabel);

        /* ------------------Search by Name Start -------- */
        searchLabel = new JLabel("Search By Name:");
        searchLabel.setBounds(20, 540, 200, 30);
        searchLabel.setFont(new Font("ROBOTO", Font.PLAIN, 21));
        asidPanel.add(searchLabel);

        nameSearch = new JTextField();
        nameSearch.setBounds(20, 580, 200, 40);
        nameSearch.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        asidPanel.add(nameSearch);

        nameSearchBtn = new JButton("Search");
        nameSearchBtn.setBounds(20, 630, 120, 30);
        nameSearchBtn.setBackground(new Color(0, 128, 255));
        nameSearchBtn.setBorderPainted(false);
        nameSearchBtn.setForeground(Color.WHITE);
        nameSearchBtn.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        asidPanel.add(nameSearchBtn);
        nameSearchBtn.addActionListener(this);
        /* ------------------Search by Name End -------- */

    };

    public void Render(Product[] productArrayPass, int pageNum) {
        int len = productArrayPass.length;
        itemHolder.removeAll();
        itemHolder.repaint();
        // this.setLayout(null);
        panelArr = new JPanel[0];
        panelArr = new JPanel[len];
        for (int i = (pageNum - 1) * 8, j = 0; i < (pageNum) * 8; i++, j++) {

            if (i < len && productArrayPass[i] != null) {
                panelArr[i] = new JPanel();
                panelArr[i].setLayout(null);

                JButton cartBtn = new JButton();
                cartBtn.setIcon(new ImageIcon("./assets/images/shopping-cart.png"));
                cartBtn.setOpaque(false);
                cartBtn.setContentAreaFilled(false);
                cartBtn.setBorderPainted(false);
                cartBtn.setFont(new Font("ROBOTO", Font.PLAIN, 12));
                cartBtn.setBounds(190, 245, 25, 25);
                // cartBtn.setBackground(new Color(255, 145, 0));
                // cartBtn.setForeground(Color.WHITE);

                cartBtn.setBorderPainted(false);
                cartBtn.setName(productArrayPass[i].getPidNo());
                panelArr[i].add(cartBtn);

                cartBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Product[] productCartStore = productManager.searchByID(cartBtn.getName(), productArray);
                        productCart[cartNum] = productCartStore[0];
                        cartNum++;
                        cartlen.setText(String.valueOf(cartNum));
                    }
                });

                String path = "./assets/stocks/" + productArrayPass[i].getPidNo() + ".png";
                JButton picBtn = new JButton(new ImageIcon(path));
                picBtn.setOpaque(true);
                picBtn.setBounds(0, 0, 230, 200);
                picBtn.setBorderPainted(false);
                panelArr[i].add(picBtn);

                String itemIDStr = "Product ID: " + productArrayPass[i].getPidNo();
                JLabel itemID = new JLabel(itemIDStr);
                itemID.setFont(new Font("ROBOTO", Font.PLAIN, 13));
                itemID.setBounds(10, 208, 230, 11);
                panelArr[i].add(itemID);

                String itemNameStr = "Name: " + productArrayPass[i].getProductName();
                JLabel itemName = new JLabel(itemNameStr);
                itemName.setFont(new Font("ROBOTO", Font.PLAIN, 13));
                itemName.setBounds(10, 223, 230, 11);
                panelArr[i].add(itemName);
                picBtn.setName(productArrayPass[i].getPidNo());
                picBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Product[] productList = productManager.searchByID(picBtn.getName().toString(), productArray);
                        JFrame newData = new JFrame();
                        newData.setResizable(false);
                        newData.setLayout(null);
                        newData.setSize(320, 500);
                        newData.setLocationRelativeTo(null);

                        JButton picFullBtn = new JButton(new ImageIcon(path));
                        picFullBtn.setOpaque(true);
                        picFullBtn.setBounds(45, 30, 230, 200);
                        picFullBtn.setBorderPainted(false);
                        newData.add(picFullBtn);

                        String itemIDFullStr = "Product ID: " + productList[0].getPidNo();
                        JLabel itemIDFull = new JLabel(itemIDFullStr);
                        itemIDFull.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                        itemIDFull.setBounds(20, 280, 230, 20);
                        newData.add(itemIDFull);

                        String itemNameFullStr = "Product Name: " + productList[0].getProductName();
                        JLabel itemNameFull = new JLabel(itemNameFullStr);
                        itemNameFull.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                        itemNameFull.setBounds(20, 300, 230, 20);
                        newData.add(itemNameFull);

                        String itemStockFullStr = "Product Stock(s): " + productList[0].getStocks();
                        JLabel itemStockFull = new JLabel(itemStockFullStr);
                        itemStockFull.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                        itemStockFull.setBounds(20, 320, 230, 20);
                        newData.add(itemStockFull);

                        String itemPriceFullStr = "Product Price: " + productList[0].getPrice();
                        JLabel itemPriceFull = new JLabel(itemPriceFullStr);
                        itemPriceFull.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                        itemPriceFull.setBounds(20, 340, 230, 20);
                        newData.add(itemPriceFull);

                        String itemTypeFullStr = "Product Type: " + productList[0].getType();
                        JLabel itemTypeFull = new JLabel(itemTypeFullStr);
                        itemTypeFull.setFont(new Font("ROBOTO", Font.PLAIN, 14));
                        itemTypeFull.setBounds(20, 360, 230, 20);
                        newData.add(itemTypeFull);

                        newData.setVisible(true);
                    }
                });

                String itemTypeStr = "Type: " + productArrayPass[i].getType();
                JLabel itemType = new JLabel(itemTypeStr);
                itemType.setFont(new Font("ROBOTO", Font.PLAIN, 13));
                itemType.setBounds(10, 238, 230, 11);
                panelArr[i].add(itemType);
                String itemPriceStr;
                if(filename.equals("./files/itemImportedList.sms")){
                    itemPriceStr = "<HTML>" + "Price: " + productArrayPass[i].getPrice() + " (with Tax)</HTML>";;
                }else if (productArrayPass[i].getPercentage().toString().equals("0")) {
                    itemPriceStr = "<HTML>" + "Price: " + productArrayPass[i].getPrice() + "</HTML>";
                } else {

                    itemPriceStr = "<HTML>" + "Price: <s>" + productArrayPass[i].getRealPrice() + "</s> &nbsp;&nbsp;&nbsp;"
                            + productArrayPass[i].getPrice() + "</HTML>";
                }

                JLabel itemPrice = new JLabel(itemPriceStr);
                itemPrice.setFont(new Font("ROBOTO", Font.PLAIN, 13));
                itemPrice.setBounds(10, 253, 230, 11);
                panelArr[i].add(itemPrice);

                // JLabel label = new JLabel(ItemArrayPass[i][0]);
                // panelArr[i].add(label);
                int getX = 0, getY = 0, width = 0, height = 0;
                if (j < 4) {
                    getX = j * 240 + 10;
                    getY = 10;
                    width = 230;
                    height = 280;
                } else if (j >= 4) {
                    getX = (j - 4) * 240 + 10;
                    getY = 30 + 280;
                    width = 230;
                    height = 280;
                }

                panelArr[i].setBounds(getX, getY, width, height);
                panelArr[i].setOpaque(true);
                panelArr[i].setBackground(new Color(247, 247, 247));
                itemHolder.add(panelArr[i]);
            }
        }

    }

    public void getPagination(Product[] productArrayPass, JPanel p) {
        pagination.removeAll();
        pagination.repaint();
        int len = productArrayPass.length;
        double totalPageDouble = (double) len / 8;
        int totalPage = (int) Math.ceil(totalPageDouble);
        PageBtn = new JButton[totalPage];
        for (int i = 0; i < totalPage; i++) {
            PageBtn[i] = new JButton(String.valueOf(i + 1));
            int getX = 10 + 50 * i;
            int getY = 8;
            int width = 40;
            int height = 40;
            PageBtn[i].setBounds(getX, getY, width, height);
            PageBtn[i].setFont(new Font("ROBOTO", Font.PLAIN, 12));
            PageBtn[i].setBorderPainted(false);
            PageBtn[i].setBackground(new Color(30, 138, 227));
            PageBtn[i].setForeground(Color.white);
            p.add(PageBtn[i]);
            PageBtn[i].addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myStore) {
            int i = 0;
            for (int n = 0; n < productCart.length; n++) {
                if (productCart[n] != null) {
                    i++;
                }
            }
            Product[] productCartOptimized = new Product[0];
            if (i > 0) {
                productCartOptimized = new Product[i];
                for (int j = 0; j < productCartOptimized.length; j++) {
                    productCartOptimized[j] = productCart[j];
                }
            }
            if (productCartOptimized.length == 0) {
                JOptionPane.showMessageDialog(null, "Please Select at least one product.");
            } else {
                JFrame cartFrame = new JFrame();
                cartFrame.setResizable(false);
                cartFrame.setLayout(null);
                cartFrame.setSize(800, 600);
                cartFrame.setLocationRelativeTo(null);

                renderCart(productCartOptimized, cartFrame);

                JButton checkoutBtn = new JButton("Checkout");
                checkoutBtn.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                checkoutBtn.setBounds(40, 180, 120, 30);
                checkoutBtn.setBackground(new Color(0, 128, 255));
                checkoutBtn.setForeground(Color.WHITE);
                checkoutBtn.setBorderPainted(false);

                JButton promoBtn = new JButton("Apply");
                promoBtn.setFont(new Font("ROBOTO", Font.PLAIN, 16));
                promoBtn.setBounds(460, 120, 120, 30);
                promoBtn.setBackground(new Color(0, 128, 255));
                promoBtn.setForeground(Color.WHITE);
                promoBtn.setBorderPainted(false);

                promoBtn.addActionListener(new ActionListener() {
                    Boolean couponcheck = false;

                    public void actionPerformed(ActionEvent e) {
                        // if () {
                        if (promoText.getText().toString().toUpperCase().equals("DECEMBER16")) {
                            totalPrice = (int) Math.round(totalPriceHolder - totalPriceHolder * .16);
                            JOptionPane.showMessageDialog(null, "Congractulation! 16% price is of now!");
                            String str = "<HTML><s>" + totalPriceHolder + "</s> &nbsp;&nbsp;&nbsp;" + totalPrice + "</HTML>";
                            pAmount.setText(str);
                            // couponcheck = true;
                        } else if (promoText.getText().toString().toUpperCase().equals("DECEMBER14")) {
                            totalPrice = (int) Math.round(totalPriceHolder - totalPriceHolder * .14);
                            JOptionPane.showMessageDialog(null, "Congractulation! 14% price is of now!");
                            String str = "<HTML><s>" + totalPriceHolder + "</s> &nbsp;&nbsp;&nbsp;" + totalPrice + "</HTML>";
                            pAmount.setText(str);
                            // couponcheck = true;
                        } else if (promoText.getText().toString().toUpperCase().equals("METRORAIL27")) {
                            if (!couponcheck) {
                                double mathPercentage = Math.round(Math.random() * 22 + 5);
                                totalPrice = (int) Math
                                        .round(totalPriceHolder - totalPriceHolder * (mathPercentage / 100));
                                String strmsg = "Congractulation! " + (int) mathPercentage + "% price is of now!";
                                JOptionPane.showMessageDialog(null, strmsg);
                                String str = "<HTML><s>" + totalPriceHolder + "</s> &nbsp;&nbsp;&nbsp;" + totalPrice + "</HTML>";
                                pAmount.setText(str);
                                couponcheck = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "You can use this coupon only once!");

                            }
                            // couponcheck = true;
                        } else if (promoText.getText().toString().toUpperCase().equals("NOMORENSU")) {
                            double mathPercentage = Math.round(Math.random() * 22 + 5);
                            totalPrice = (int) Math
                                    .round(totalPriceHolder - totalPriceHolder * (mathPercentage / 100));
                            String strmsg = "Congractulation! " + (int) mathPercentage + "% price is of now!";
                            JOptionPane.showMessageDialog(null, strmsg);
                            String str = "<HTML><s>" + totalPriceHolder + "</s> &nbsp;&nbsp;&nbsp;" + totalPrice + "</HTML>";
                                pAmount.setText(str);
                            // couponcheck = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Coupon is not valid.");
                        }
                        // }
                        // else{
                        // JOptionPane.showMessageDialog(null, "You can use coupon only once.");
                        // }

                    }
                });

                checkoutBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        if (fullNameText.getText().toString().equals("")
                                || fullNumberText.getText().toString().equals("")) {
                            JOptionPane.showMessageDialog(null, "Please add your information");
                        } else {
                            fullNameText.setOpaque(false);
                            fullNameText.setBorder(BorderFactory.createEmptyBorder());
                            fullNumberText.setOpaque(false);
                            fullNumberText.setBorder(BorderFactory.createEmptyBorder());
                            promoText.setOpaque(false);
                            promoText.setBorder(BorderFactory.createEmptyBorder());
                            cartPanel.remove(checkoutBtn);
                            cartPanel.remove(promoBtn);
                            cartPanel.validate();
                            cartFrame.setVisible(false);
                            String msg = "Thanks " + fullNameText.getText().toString() + ", for purchasing.\n"
                                    + "Total Price: " + String.valueOf(totalPrice);
                            JOptionPane.showMessageDialog(null, msg);
                            cartNum = 0;
                            Validation.print(cartPanel);
                            productCart = new Product[100];
                            cartlen.setText("0");
                        }
                    }
                });
                cartPanel.add(checkoutBtn);
                cartPanel.add(promoBtn);

            }
        }

        if (e.getSource() == nameSearchBtn) {

            String str = (String) nameSearch
                    .getText();
            productArray = productManager.getObjectFromText(filename);
            if (!String.valueOf(filterComboBox.getSelectedItem()).equals("All Products")) {
                productArray = productManager.filterProductByType(String.valueOf(filterComboBox.getSelectedItem()),
                        productArray);
            } else {
                productArray = productManager.getObjectFromText(filename);

            }
            productArray = productManager.searchByName(str, productArray);

            Render(productArray, 1);
            getPagination(productArray, pagination);
        }
        if (e.getSource() == priceFilterBtn)

        {
            String minPriceStr = PriceMin.getText();
            String maxPriceStr = PriceMax.getText();
            if (minPriceStr.length() >= 1 && maxPriceStr.length() >= 1) {
                try {
                    warningLabel.setText("");
                    Double.parseDouble(minPriceStr);
                    Double.parseDouble(maxPriceStr);
                    filteredDataProduct = productManager.filteritemByPrice(minPriceStr, maxPriceStr, productArray);
                    Render(filteredDataProduct, 1);
                    getPagination(filteredDataProduct, pagination);
                } catch (Exception exp) {
                    warningLabel.setText("Warning: Enter valid price.");
                }
            }
        }
        for (int i = 0; i < PageBtn.length; i++) {
            if (e.getSource() == PageBtn[i]) {
                // System.out.println(Integer.parseInt(PageBtn[i].getText())+1);
                // this.setVisible(false);
                Render(productArray, Integer.parseInt(PageBtn[i].getText()));
                // this.validate();
                // this.setVisible(true);
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == sortComboBox) {
            // System.out.println(Arrays.toString(arrItemList));
            String str = (String) sortComboBox.getSelectedItem();
            if (str.equals("Price [Low to High]")) {
                // System.out.println(str);
                productArray = productManager.sortedProductsList("price", productArray);
                Render(productArray, 1);
            }
            if (str.equals("Name [A-Z]")) {
                // System.out.println(str);
                productArray = productManager.sortedProductsList("name", productArray);
                Render(productArray, 1);
            }
            if (str.equals("ID [Low to High]")) {
                // System.out.println(str);
                productArray = productManager.sortedProductsList("ID", productArray);
                Render(productArray, 1);
            }
        } else if (e.getSource() == filterComboBox) {
            // System.out.println(Arrays.toString(arrItemList));
            String str = (String) filterComboBox.getSelectedItem();
            // System.out.println(str);
            if (str.equals("All Products")) {
                productArray = productManager.getObjectFromText(filename);
                Render(productArray, 1);
                getPagination(productArray, pagination);
                // System.out.println("OK");
            } else {
                for (int i = 0; i < arrItemList.length; i++) {
                    if (str.equals(arrItemList[i])) {
                        // System.out.println(str);
                        productArray = productManager.getObjectFromText(filename);
                        productArray = productManager.filterProductByType(arrItemList[i], productArray);
                        Render(productArray, 1);
                        getPagination(productArray, pagination);
                    }
                }
            }
        }
    }

    public void renderCart(Product[] productCartOptimized, JFrame cartFrame) {
        cartPanel = new JPanel();
        // cartPanel.setBounds(0,0,1000,10000);
        cartPanel.setLayout(null);
        // cartPanel.setBackground(Color.RED);

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(new Font("ROBOTO", Font.BOLD, 16));
        fullNameLabel.setBounds(40, 40, 160, 24);
        cartPanel.add(fullNameLabel);

        fullNameText = new JTextField("");
        fullNameText.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        fullNameText.setBounds(200, 40, 240, 30);
        cartPanel.add(fullNameText);

        JLabel fullNumberLabel = new JLabel("Contact Number:");
        fullNumberLabel.setFont(new Font("ROBOTO", Font.BOLD, 16));
        fullNumberLabel.setBounds(40, 80, 160, 24);
        cartPanel.add(fullNumberLabel);

        fullNumberText = new JTextField("");
        fullNumberText.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        fullNumberText.setBounds(200, 80, 240, 30);
        cartPanel.add(fullNumberText);

        JLabel promocodeLabel = new JLabel("Promo Code:");
        promocodeLabel.setFont(new Font("ROBOTO", Font.BOLD, 16));
        promocodeLabel.setBounds(40, 120, 160, 24);
        cartPanel.add(promocodeLabel);

        promoText = new JTextField("");
        promoText.setFont(new Font("ROBOTO", Font.PLAIN, 14));
        promoText.setBounds(200, 120, 240, 30);

        cartPanel.add(promoText);

        JLabel TitleName = new JLabel("<HTML><U>Product Name<HTML><U>");
        TitleName.setFont(new Font("ROBOTO", Font.BOLD, 14));
        TitleName.setBounds(40, 244, 200, 20);
        // TitleName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cartPanel.add(TitleName);

        JLabel TitleStock = new JLabel("<HTML><U>Quantity<HTML><U>");
        TitleStock.setFont(new Font("ROBOTO", Font.BOLD, 14));
        TitleStock.setBounds(240, 244, 200, 20);
        // TitleName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cartPanel.add(TitleStock);

        JLabel TitlePrice = new JLabel("<HTML><U>Product Price<HTML><U>");
        TitlePrice.setFont(new Font("ROBOTO", Font.BOLD, 14));
        TitlePrice.setBounds(420, 244, 200, 20);
        cartPanel.add(TitlePrice);

        JLabel crossLabel = new JLabel("<HTML><U>Action<HTML><U>");
        crossLabel.setFont(new Font("ROBOTO", Font.BOLD, 14));
        crossLabel.setBounds(560, 244, 200, 20);
        cartPanel.add(crossLabel);

        int y = 0;
        for (int j = 0; j < productCartOptimized.length; j++) {
            int x = 40;
            y = j * 24 + 272;
            int width = 200;
            int height = 20;

            JLabel pName = new JLabel(productCartOptimized[j].getProductName());
            pName.setBounds(x, y, width, height);
            cartPanel.add(pName);

            int x2 = 420;

            JLabel pPrice = new JLabel(productCartOptimized[j].getPrice());
            pPrice.setBounds(x2, y, width, height);
            cartPanel.add(pPrice);

            int x3 = 244;

            JLabel pQuantity = new JLabel("x1");
            pQuantity.setBounds(x3, y, width, height);
            cartPanel.add(pQuantity);

            int x4 = 560;
            JButton crossBtn = new JButton("X");
            crossBtn.setName(productCartOptimized[j].getPidNo());
            crossBtn.setForeground(Color.WHITE);
            crossBtn.setBorderPainted(false);
            crossBtn.setBackground(new Color(30, 138, 227));
            crossBtn.setBounds(x4, y, 60, height);
            // cartPanel.add(crossBtn);

            crossBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(crossBtn.getName());
                }
            });

        }
        JLabel pBorder = new JLabel("");
        pBorder.setBounds(20, (y + 30), 500, 2);
        pBorder.setOpaque(true);
        pBorder.setBackground(Color.BLACK);
        pBorder.setForeground(Color.BLACK);
        cartPanel.add(pBorder);

        totalPrice = 0;
        for (int j = 0; j < productCartOptimized.length; j++) {
            totalPrice += Integer.parseInt(productCartOptimized[j].getPrice());
        }
        totalPriceHolder = totalPrice;
        JLabel pTotal = new JLabel("Total Price: ");
        pTotal.setBounds(300, (y + 40), 100, 20);
        cartPanel.add(pTotal);
        cartPanel.setPreferredSize(new Dimension(cartPanel.getWidth(), 2000));
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(20, 20, 760, 520);
        cartFrame.add(scrollPane);
        cartFrame.setVisible(true);
        pAmount = new JLabel(String.valueOf(totalPrice));
        pAmount.setBounds(420, (y + 40), 100, 20);
        cartPanel.add(pAmount);

    }
}