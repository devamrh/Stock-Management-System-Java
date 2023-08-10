package utils;

public class DiscountProduct extends Product {
    public DiscountProduct(String pidNo, String productName, String type, String stocks, String price, String publishedAt,
            String percentage) {
        super(pidNo, productName, type, stocks, price, publishedAt, percentage);
        setCatagory("Regular Product");
    }
    
    public DiscountProduct(String pidNo, String productName, String type, String stocks, String price, String publishedAt) {
        super(pidNo, productName, type, stocks, price, publishedAt);
        setCatagory("Regular Product");
        double realPrice = Double.parseDouble(super.getPrice());
        double percentage = (double) Double.parseDouble(super.getPercentage())/100;
        String str = String.valueOf(realPrice*percentage);
        setPrice(str);
    }

    @Override
    public String getPrice() {
        double realPrice = Double.parseDouble(super.getPrice());
        double percentage = (double) (Double.parseDouble(super.getPercentage())/100);
        String str = String.valueOf(Math.round(realPrice - realPrice*(percentage)));
        // setPrice(str);
        return str;
    }

    @Override
    public String getRealPrice() {
        return super.getPrice();
    }


}
