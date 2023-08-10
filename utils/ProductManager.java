package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
// import java.util.Arrays;
import java.io.IOException;

// import java.time.LocalDate;
import java.util.Arrays;

public class ProductManager implements ProductManagerInterface {

    // e.g. getObjectFromText("./files/itemList.sms")

    public Product[] getObjectFromText(String filename) {
        int counter = 0;
        Product[] productManagerList = new Product[500];
        try {
            FileReader txtReaderFile = new FileReader(filename);
            BufferedReader txtReader = new BufferedReader(txtReaderFile);
            String ReadByLine;
            while ((ReadByLine = txtReader.readLine()) != null) {
                String[] headerArr = ReadByLine.split("#brkptr#");

                if (filename.indexOf("itemRegularList.sms") > -1) {
                    productManagerList[counter] = new RegularProduct(headerArr[0], headerArr[1], headerArr[2],
                            headerArr[3],
                            headerArr[4], headerArr[5], headerArr[6]);
                } else if (filename.indexOf("itemDiscountList.sms") > -1) {
                    productManagerList[counter] = new DiscountProduct(headerArr[0], headerArr[1], headerArr[2],
                            headerArr[3],
                            headerArr[4], headerArr[5], headerArr[6]);
                } else if (filename.indexOf("itemImportedList.sms") > -1) {
                    productManagerList[counter] = new ImportedProduct(headerArr[0], headerArr[1], headerArr[2],
                            headerArr[3],
                            headerArr[4], headerArr[5], headerArr[6]);
                } else {
                    productManagerList[counter] = new Product(headerArr[0], headerArr[1], headerArr[2], headerArr[3],
                            headerArr[4], headerArr[5]);
                    // System.out.println("OK");
                }
                counter++;
            }
            txtReader.close();
            txtReaderFile.close();
        } catch (IOException e) {
            System.out.println("Unable to Read File.");
            System.out.println(e);
        }
        counter = 0;
        for (int i = 0; i < productManagerList.length; i++) {
            if (productManagerList[i] != null) {
                counter++;
            }
        }
        Product[] tempProducts = new Product[counter];
        for (int i = 0, j = 0; i < tempProducts.length; i++) {
            if (productManagerList[i] != null) {
                tempProducts[j] = productManagerList[i];
                j++;
            }
        }
        return tempProducts;
    }

    // e.g. showAllProductDetails(productList)
    public void showAllProductDetails(Product[] productList) {
        for (int i = 0; i < productList.length; i++) {
            if (productList[i] != null) {
                productList[i].showProductDetails();
            }
        }
    }

    // e.g. sortedProductsList("Stocks", productList)
    public Product[] sortedProductsList(String optionTitle, Product[] productManagerList) {

        if (optionTitle == "name" || optionTitle == "Name") {
            try {
                Arrays.sort(productManagerList, (a, b) -> Integer.compare(Integer.parseInt(a.getProductName()),
                        Integer.parseInt(b.getProductName())));
            } catch (Exception e) {
                Arrays.sort(productManagerList,
                        (a, b) -> new String(a.getProductName().toLowerCase())
                                .compareTo(new String(b.getProductName().toLowerCase())));
            }
        } else if (optionTitle == "id" || optionTitle == "ID") {
            try {
                Arrays.sort(productManagerList, (a, b) -> Integer.compare(Integer.parseInt(a.getPidNo()),
                        Integer.parseInt(b.getPidNo())));
            } catch (Exception e) {
                Arrays.sort(productManagerList,
                        (a, b) -> new String(a.getPidNo().toLowerCase())
                                .compareTo(new String(b.getPidNo().toLowerCase())));
            }
        } else if (optionTitle == "Price" || optionTitle == "price") {
            try {
                Arrays.sort(productManagerList, (a, b) -> Integer.compare(Integer.parseInt(a.getRealPrice()),
                        Integer.parseInt(b.getRealPrice())));
            } catch (Exception e) {
                Arrays.sort(productManagerList,
                        (a, b) -> new String(a.getRealPrice().toLowerCase())
                                .compareTo(new String(b.getRealPrice().toLowerCase())));
            }
        }
        return productManagerList;
    }

    // e.g. ProductItemTypeList(productList)
    public String[] ProductItemTypeList(Product[] productList) {
        String[] arrItemList;
        String[] arrList = new String[99];
        int j = 0;
        for (int i = 0; i < productList.length; i++) {

            if (!Arrays.asList(arrList).contains(productList[i].getType())) {
                arrList[j] = productList[i].getType();
                j++;
            }
        }
        arrItemList = new String[j + 1];
        for (int i = 0; i < j; i++) {
            arrItemList[i] = arrList[i];
        }
        arrItemList[j] = "All Products";
        return arrItemList;
    }

    // e.g. filterProductByType("Monitor", productList)
    public Product[] filterProductByType(String typeName, Product[] productList) {
        Product[] tempProductHolder = new Product[productList.length];
        int j = 0;
        for (int i = 0; i < productList.length; i++) {
            if (productList[i].getType().contains(typeName)) {
                tempProductHolder[j] = productList[i];
                j++;
            }
        }
        System.out.println(j);
        Product[] filteredList = new Product[j];
        for (int i = 0; i < j; i++) {
            filteredList[i] = tempProductHolder[i];
        }

        return filteredList;
    }

    // e.g. filteritemByPrice("20000", "40000", productList)
    public Product[] filteritemByPrice(String minPrice, String maxPrice, Product[] productList) {

        Product[] tempProductHolder = new Product[productList.length];
        int j = 0;
        for (int i = 0; i < productList.length; i++) {
            try {
                if ((Double.parseDouble(productList[i].getRealPrice()) >= Double.parseDouble(minPrice))
                        && (Double.parseDouble(productList[i].getRealPrice()) <= Double.parseDouble(maxPrice))) {
                    tempProductHolder[j] = productList[i];
                    j++;
                }
            } catch (Exception e) {
                System.out.println("Only number is accepted.");
                System.out.println(e);
            }
        }
        Product[] filterData = new Product[j];
        for (int i = 0; i < j; i++) {
            filterData[i] = tempProductHolder[i];
        }

        return filterData;
    }

    // e.g. searchByName("Gtx", productList)
    public Product[] searchByName(String name, Product[] productList) {
        Product[] tempProductHolder = new Product[productList.length];
        int j = 0;
        for (int i = 0; i < productList.length; i++) {
            if (productList[i].getProductName().toLowerCase().indexOf(name.toLowerCase()) > -1) {
                tempProductHolder[j] = productList[i];
                j++;
            }
        }
        Product[] filterData = new Product[j];
        for (int i = 0; i < j; i++) {
            filterData[i] = tempProductHolder[i];
        }

        return filterData;
    }

    // e.g. searchByID("30", productList)
    public Product[] searchByID(String pid, Product[] productList) {
        Product[] tempProductHolder = new Product[productList.length];
        int j = 0;
        for (int i = 0; i < productList.length; i++) {
            if (productList[i].getPidNo().toLowerCase().equals(pid.toLowerCase())) {
                tempProductHolder[j] = productList[i];
                j++;
            }
        }
        Product[] filterData = new Product[j];
        for (int i = 0; i < j; i++) {
            filterData[i] = tempProductHolder[i];
        }

        return filterData;
    }

    // e.g. addNewProduct(new Product(.,.,....), productList)
    public void addNewProduct(Product product, String filename) {
        String id = product.getPidNo();
        String name = product.getProductName();
        String type = product.getType();
        String stocks = product.getStocks();
        String price = product.getRealPrice();
        String createdAt = product.getPublishedAt();
        // String catagory = product.getCatagory();
        String percentage = product.getPercentage();

        String newData = id + "#brkptr#" + name + "#brkptr#" + type + "#brkptr#" + stocks + "#brkptr#" + price
                + "#brkptr#" + createdAt + "#brkptr#" + percentage;

        try {
            FileReader txtReaderFile = new FileReader(filename);
            BufferedReader txtReader = new BufferedReader(txtReaderFile);

            String oldData = "";
            String ReadByLine = txtReader.readLine();
            boolean isFirstLine = true;
            while (ReadByLine != null) {
                if (isFirstLine) {
                    oldData += ReadByLine;
                    isFirstLine = false;
                } else {
                    oldData += "\n" + ReadByLine;
                }
                ReadByLine = txtReader.readLine();

            }
            txtReader.close();
            txtReaderFile.close();

            String newText = newData + "\n" + oldData;

            txtReader.close();
            txtReaderFile.close();

            FileWriter writerFile = new FileWriter(filename);
            BufferedWriter writer = new BufferedWriter(writerFile);
            writer.write(newText);
            writer.close();
            writerFile.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // e.g. getTextFromObject("./files/itemList.sms", productList)
    public void getTextFromObject(String filename, Product[] productList) {
        try {
            FileWriter writerfile = new FileWriter(filename);
            BufferedWriter txtWriter = new BufferedWriter(writerfile);

            String data = "";
            for (int i = 0; i < productList.length; i++) {
                if (productList[i] != null) {
                    String id = productList[i].getPidNo();
                    String name = productList[i].getProductName();
                    String type = productList[i].getType();
                    String stocks = productList[i].getStocks();
                    String price = productList[i].getRealPrice();
                    String createdAt = productList[i].getPublishedAt();
                    String percentage = productList[i].getPercentage();

                    String newData = id + "#brkptr#" + name + "#brkptr#" + type + "#brkptr#" + stocks + "#brkptr#"
                            + price
                            + "#brkptr#" + createdAt + "#brkptr#" + percentage;
                    if (i != (productList.length - 1)) {
                        newData += "\n";
                    }
                    data += newData;

                }

            }
            txtWriter.write(data);

            txtWriter.close();
            writerfile.close();
        } catch (IOException e) {
            System.out.println("Unable to write file");
            System.out.println(e);
        }
    }

    // e.g. searchByIdAndDelete("./files/itemList.sms", "30", productList)
    public boolean searchByIdAndDelete(String filename, String id, Product[] productList) {
        boolean bool = false;
        for (int i = 0; i < productList.length; i++) {
            if (productList[i] != null) {
                if (productList[i].getPidNo().equals(id)) {
                    productList[i] = null;
                    bool = true;
                }
            }
        }
        System.out.println(bool);
        this.getTextFromObject(filename, productList);
        return bool;
    }

    // e.g. searchByIdAndUpdate("./files/itemList.sms", "30", productList,
    // newAddedProductObject)
    public boolean searchByIdAndUpdate(String filename, String id, Product[] productList, Product updatedProduct) {
        boolean bool = false;
        for (int i = 0; i < productList.length; i++) {
            if (productList[i].getPidNo().equals(id)) {

                // productList[i].setPidNo(updateData[0]);
                productList[i].setProductName(updatedProduct.getProductName());
                productList[i].setType(updatedProduct.getType());
                productList[i].setStocks(updatedProduct.getStocks());
                productList[i].setPrice(updatedProduct.getRealPrice());
                // productList[i].setPublishedAt(updateData[0]);
                bool = true;
            }
        }
        System.out.println(bool);
        this.getTextFromObject(filename, productList);
        return bool;
    }
}
