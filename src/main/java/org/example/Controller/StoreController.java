package org.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.example.Exception.ProductException;
import org.example.Exception.VendorException;
import org.example.Model.Product;
import org.example.Model.Vendor;
import org.example.Service.ProductService;
import org.example.Service.VendorService;

import java.util.List;

public class StoreController {
    ProductService productService;
    VendorService vendorService;

    public StoreController(ProductService productService, VendorService vendorService) {
        this.productService = productService;
        this.vendorService = vendorService;
    }

    public Javalin getAPI() {
        Javalin api = Javalin.create();

        api.get("health", context -> {
            context.result("The server is up");
        });

        api.get("/vendor/", context -> {
            List<Vendor> vendors = vendorService.retrieveVendors();
            context.json(vendors);
        });

        api.post("/vendor/", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Vendor v = om.readValue(context.body(), Vendor.class);
                //why don't we have "Vendor newVendor" in front of line 42, like we do with Product on line 72?
                vendorService.addVendor(v);
                context.status(201);
                context.json(v);
            } catch (JsonProcessingException e) {
                context.result(e.getMessage());
                context.status(400);
            }
        });

        api.get("/product/", context -> {
            List<Product> products = productService.viewAllProduct();
            context.json(products);
        });

        api.get("product/{productID}", context -> {
            long productID = Long.parseLong(context.pathParam("productID"));
            Product p = productService.viewProduct(productID);
            if (p == null) {
                context.status(404);
            } else {
                context.json(p);
                context.status(200);
            }
        });

        api.post("/product/", context -> {
            try {
                ObjectMapper om = new ObjectMapper();
                Product p = om.readValue(context.body(), Product.class);
                Product newProduct = productService.addProduct(p);
                context.status(201);
                context.json(newProduct);
            } catch (JsonProcessingException e) {
                context.result(e.getMessage());
                context.status(400);
            }
        });

        api.put("/product/{productID}", context -> {
            //long productID = Long.parseLong(context.pathParam("productID"));
            ObjectMapper om = new ObjectMapper();
            try{
                Product p = om.readValue(context.body(), Product.class);
                productService.updateProduct(p);
                context.json(p);
                context.status(200);
            } catch (JsonProcessingException e) {
                context.result(e.getMessage());
                context.status(400);
            }
        });

        api.delete("/product/{productID}", context -> {
            long productID = Long.parseLong(context.pathParam("productID"));
            productService.deleteProduct(productID);
            context.status(200);
        });

        return api;
    }
}

