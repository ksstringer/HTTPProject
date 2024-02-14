package org.example.Service;

import org.example.Exception.ProductException;
import org.example.Exception.VendorException;
import org.example.Main;
import org.example.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public VendorService vendorService;
    List<Product> products;

    //dependency injection: injecting Vendor Service into Product Service
    public ProductService(VendorService vendorService) {
        this.vendorService = vendorService;
        products = new ArrayList<>();
    }

    public Product addProduct(Product p) throws ProductException, VendorException {
        Main.log.info("Adding a new product: " + p);
        if (p.getProductName() == null || p.getProductPrice() == null || p.getVendorName() == null) {
            Main.log.warn("Throwing Product Exception for blank product name, product price, and/or vendor name");
            throw new ProductException("Name, Price, and Vendor Name cannot be null");
        } else if (p.getProductPrice() <= 0) {
            Main.log.warn("Throwing Product Exception for invalid product price");
            throw new ProductException("Price must be greater than 0");
        } else if (vendorService.getVendorByName(p.getVendorName()).isEmpty()){
            Main.log.warn("Throwing Product Exception for vendor name does not exist");
            throw new ProductException("Vendor must exist in order to add product");
        }
        long productID = (long) (Math.random() * Long.MAX_VALUE);
        p.setProductID(productID);
        products.add(p);
        return p;
    }

    public Product updateProduct(Product p) throws ProductException, VendorException {
        Main.log.info("Updating product: " + p);
        if (p.getProductName() == null || p.getProductPrice() == null || p.getVendorName() == null) {
            Main.log.warn("Throwing Product Exception for blank product name, product price, and/or vendor name");
            throw new ProductException("Name, Price, and Vendor Name cannot be null");
        } else if (p.getProductPrice() <= 0) {
            Main.log.warn("Throwing Product Exception for invalid product price");
            throw new ProductException("Price must be greater than 0");
        } else if (vendorService.getVendorByName(p.getVendorName()).isEmpty()){
            Main.log.warn("Throwing Product Exception for vendor name does not exist");
            throw new ProductException("Vendor must exist in order to add product");
        }
        for(int i =0; i < products.size(); i++){
            Product currentProduct = products.get(i);
            if(currentProduct.getProductID() == p.getProductID()) {
                p.setProductName(p.getProductName());
                p.setProductPrice(p.getProductPrice());
                p.setVendorName(p.getVendorName());

                //when i run PUT in PM, it's giving me a 200, but i also get the "Product not found" log from below
                //it doesn't seem like my code is actually retrieving the product by id?
                products.add(p);
                return p;
            }
        }
        Main.log.info("Product not found");
        return null;
    }

    public void deleteProduct(Long p) {
        Main.log.info("Deleting product: " + p);
        for(int i = 0; i < products.size(); i++){
            Product currentProduct = products.get(i);
            if(currentProduct.getProductID() == p)
                products.remove(currentProduct);
        }
    }

    public Product viewProduct(Long p) {
        Main.log.info("Retrieving product for view: " + p);
        for(int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);
            if(currentProduct.getProductID() == p){
                return currentProduct;
            }
        }
        Main.log.info("Product not found");
        return null;
    }

    public List<Product> viewAllProduct() {
        Main.log.info("Retrieving all products for view: " + products);
        return products;
    }
}
