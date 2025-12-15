package entity;

public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private String productColor;
    private String productSize;
    private double productPrice;

    public Product() {
    }

    public Product(int productId, String productName, String productDescription,
                   String productColor, String productSize, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productColor = productColor;
        this.productSize = productSize;
        this.productPrice = productPrice;
    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }

    public String getProductColor() { return productColor; }
    public void setProductColor(String productColor) { this.productColor = productColor; }

    public String getProductSize() { return productSize; }
    public void setProductSize(String productSize) { this.productSize = productSize; }

    public double getProductPrice() { return productPrice; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName +
                ", productDescription=" + productDescription + ", productColor=" + productColor +
                ", productSize=" + productSize + ", productPrice=" + productPrice + '}';
    }
}