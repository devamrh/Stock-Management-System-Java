package utils;

public interface ProductManagerInterface {
    public Product[] getObjectFromText(String filename);
    public void showAllProductDetails(Product[] productList);
    public Product[] sortedProductsList(String optionTitle, Product[] productManagerList);
    public String[] ProductItemTypeList(Product[] productList);
    public Product[] filterProductByType(String typeName, Product[] productList);
    public Product[] filteritemByPrice(String minPrice, String maxPrice, Product[] productList);
    public Product[] searchByName(String name, Product[] productList);
    public Product[] searchByID(String pid, Product[] productList);
    public void addNewProduct(Product product, String filename);
    public void getTextFromObject(String filename, Product[] productList);
    public boolean searchByIdAndDelete(String filename, String id, Product[] productList);
    public boolean searchByIdAndUpdate(String filename, String id, Product[] productList, Product updatedProduct);
}
