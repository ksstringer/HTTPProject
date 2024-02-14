package org.example;


import io.javalin.Javalin;
import org.example.Controller.StoreController;
import org.example.Service.ProductService;
import org.example.Service.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {

    public static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        VendorService vendorService = new VendorService();
        ProductService productService = new ProductService(vendorService);
        StoreController storeController = new StoreController(productService, vendorService);

        Javalin api = storeController.getAPI();
        api.start(9003);

    }
}