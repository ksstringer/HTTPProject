package org.example.Model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Product {
    private long productID;
    private String productName;
    private Double productPrice;
    private String vendorName;

    public Product(String productName, Double productPrice, String vendorName) {
        //this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.vendorName = vendorName;
    }

    //default constructor required for jackson
    public Product(){

    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productID, product.productID) && Objects.equals(productName, product.productName)
                && Objects.equals(productPrice, product.productPrice) && Objects.equals(vendorName, product.vendorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productPrice, vendorName);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", vendorName='" + vendorName + '\'' +
                '}';
    }

}
