package utils;


public class Product {
    private String pidNo;
    private String productName;
    private String type;
    private String stocks;
    private String price;
    private String publishedAt;
    private String percentage = "0";
    private String catagory;



    public Product(String pidNo, String productName, String type, String stocks, String price, String publishedAt, String percentage) {

        pidNo = Validation.tryParseInt(pidNo);
        stocks = Validation.tryParseInt(stocks);
        price = Validation.tryParseInt(price);
        percentage = Validation.tryParseInt(percentage);

        this.pidNo = pidNo;
        this.productName = productName;
        this.type = type;
        this.stocks = stocks;
        this.price = price;
        this.publishedAt = publishedAt;
        this.percentage = percentage;
        this.catagory = "Unknown Product";
    }

    public Product(String pidNo, String productName, String type, String stocks, String price, String publishedAt) {

        pidNo = Validation.tryParseInt(pidNo);
        stocks = Validation.tryParseInt(stocks);
        price = Validation.tryParseInt(price);
        percentage = Validation.tryParseInt(percentage);

        this.pidNo = pidNo;
        this.productName = productName;
        this.type = type;
        this.stocks = stocks;
        this.price = price;
        this.publishedAt = publishedAt;
        this.percentage = "0";
        this.catagory = "Unknown Products";
    }

    public String getPercentage(){
        return percentage;
    }
    public void setPercentage(String percentage){
        this.percentage = percentage;
    }

    public String getCatagory(){
        return catagory;
    }
    public void setCatagory(String catagory){
        this.catagory = catagory;
    }
    
    public String getPidNo() {
        return pidNo;
    }

    public void setPidNo(String pidNo) {
        pidNo = Validation.tryParseInt(pidNo);
        this.pidNo = pidNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        stocks = Validation.tryParseInt(stocks);
        this.stocks = stocks;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        price = Validation.tryParseInt(price);
        this.price = price;
    }
    public String getRealPrice() {
        return price;
    }
    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void showProductDetails() {
        System.out.println("ID: " + this.pidNo);
        System.out.println("Name: " + this.productName);
        System.out.println("Type: " + this.type);
        System.out.println("Stocks: " + this.stocks);
        System.out.println("Prices: " + this.price);
        System.out.println("Created At: " + this.publishedAt);
        System.out.println("-------------------------------");
    }

    
}
