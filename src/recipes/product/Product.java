package recipes.product;

public class Product {

    //Class variables
    private String productName;
    private int productCost;

    //Constructor
    public Product(String productName, int productCost) {
        this.productName = productName;
        this.productCost = productCost;
    }

    //Getters & Setters

    public String getProductName() {
        return productName;
    }

    public int getProductCost() {
        return productCost;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCost(int productCost) {
        this.productCost = productCost;
    }
}
