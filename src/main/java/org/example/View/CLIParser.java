package org.example.View;

import org.example.Exception.CommandException;
import org.example.Exception.VendorException;
import org.example.Model.Product;
import org.example.Model.Vendor;
import org.example.Service.ProductService;
import org.example.Service.VendorService;

import java.util.List;
import java.util.Scanner;
/**
public class CLIParser {

    VendorService vendorService;
    ProductService productService;
    public CLIParser() {
        vendorService = new VendorService();
        productService = new ProductService();
    }

    public String parseCommands(String command) throws CommandException, VendorException {
        if (command.equalsIgnoreCase("product")) {
            return productMenu();
        }
        else if (command.equalsIgnoreCase("vendor")) {
            return vendorMenu();
        }
        else {
            throw new CommandException("Not a valid entry");
        }
    }

    public String vendorMenu() throws CommandException, VendorException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter one: Add or View");
        String vendorMenuInput = sc.nextLine();
        if (vendorMenuInput.equalsIgnoreCase("add")) {
            return addVendor();
        }
        else if (vendorMenuInput.equalsIgnoreCase("view")) {
            return viewVendors();
        }
        else {
            throw new CommandException("Not a valid entry");
        }
    }

    public String addVendor() throws VendorException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter vendor name");
        String vendorNameInput = sc.nextLine();

        vendorService.addVendor(vendorNameInput);
        return "Vendor added to system";
    }

    public String viewVendors() {
        List<Vendor> vendors = vendorService.retrieveVendors();
        return "The vendors on file are: " + "\n" + vendors;
    }

    public String productMenu() throws CommandException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter one: Add, Update, Delete, View, View All");
        String productMenuInput = sc.nextLine();
        if (productMenuInput.equalsIgnoreCase("add")) {
            return productAdd();
        }
        else if (productMenuInput.equalsIgnoreCase("update")) {
            return productUpdate();
        }
        else if (productMenuInput.equalsIgnoreCase("delete")) {
            return productDelete();
        }
        else if (productMenuInput.equalsIgnoreCase("view")) {
            return productView();
        }
        else if (productMenuInput.equalsIgnoreCase("view all")) {
            return productViewAll();
        }
        else {
            throw new CommandException("Not a valid entry");
        }
    }

    public void productAdd() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name");
        String productNameInput = sc.nextLine();
        System.out.println("Enter product price");
        Double productPriceInput = (sc.nextLine());
        System.out.println("Enter vendor name");
        String vendorNameInput = sc.nextLine();
    }

    public String productUpdate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product ID");

    }

    public void productDelete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("To delete a product, enter its product ID");

    }

    public Integer productView() {
        Scanner sc = new Scanner(System.in);
        System.out.println("To view a product, enter its product ID");
    }

    public String productViewAll() {
        List<Product> products = productService.viewAllProduct();
        return "The products on file are: " + "\n" + products;
    }
}
**/