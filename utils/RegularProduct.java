package utils;

public class RegularProduct extends Product{
    public RegularProduct(String pidNo, String productName, String type, String stocks, String price, String publishedAt, String percentage){
        super(pidNo, productName, type, stocks, price, publishedAt, percentage);
    }
    public RegularProduct(String pidNo, String productName, String type, String stocks, String price, String publishedAt){
        super(pidNo, productName, type, stocks, price, publishedAt);
    }
}
