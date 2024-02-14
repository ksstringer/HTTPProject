import org.example.Exception.ProductException;
import org.example.Exception.VendorException;
import org.example.Model.Product;
import org.example.Model.Vendor;
import org.example.Service.ProductService;
import org.example.Service.VendorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ServicesTest {
    ProductService productService;
    VendorService vendorService;
    Vendor testVendor;

    @Before
    public void setUp() throws VendorException {
        productService = new ProductService(new VendorService());
        vendorService = new VendorService();

        testVendor = new Vendor();
        testVendor.setVendorName("vendor");
        vendorService.addVendor(testVendor);

        productService.vendorService = vendorService;
    }

    @Test
    public void setServicesEmptyAtStart() {
        List<Product> products = productService.viewAllProduct();
        Assert.assertTrue(products.isEmpty());
    }

    @Test
    public void vendorServiceAddVendor() {
        List<Vendor> vendors = vendorService.retrieveVendors();
        Vendor actual = vendors.get(0);
        Assert.assertEquals("vendor", actual.getVendorName());
    }

    @Test
    public void vendorServiceViewVendorByName() throws VendorException {
        List<Vendor> vendors = vendorService.getVendorByName("mark");
        Assert.assertTrue(vendors.isEmpty());
    }

    @Test
    public void vendorServiceViewVendors() {
        String testVendorName = "sally";
        String testVendorName2 = "mark";
        Vendor v = new Vendor(testVendorName);
        Vendor v2 = new Vendor(testVendorName2);
        try {
            vendorService.addVendor(v);
            vendorService.addVendor(v2);
        } catch (VendorException e){
            e.printStackTrace();
            Assert.fail("Vendor exception incorrectly thrown");
        }
        List<Vendor> vendors = vendorService.retrieveVendors();
        Assert.assertTrue(vendors.contains(v));
        Assert.assertTrue(vendors.contains(v2));
    }

    @Test
    public void vendorServiceAddVendorAlreadyExists() {
        String testVendorName = "vendor";
        Vendor v = new Vendor(testVendorName);
        try {
            vendorService.addVendor(v);
            Assert.fail("Exception not thrown");
        }catch (VendorException e){
        }
    }

    @Test
    public void vendorServiceAddVendorBlankVendorName() {
        String testVendorName = null;
        Vendor v = new Vendor(testVendorName);
        try {
            vendorService.addVendor(v);
            Assert.fail("Exception not thrown");
        }catch (VendorException e){
        }
    }

    @Test
    //Add new product & confirm added
    public void productServiceAddProduct() throws VendorException, ProductException {
        String testProductName = "product";
        Double testProductPrice = 2.25;
        String testVendorName = "vendor";
        Product p = new Product(testProductName, testProductPrice, testVendorName);
        try{
            productService.addProduct(p);
        } catch (VendorException e) {
            e.printStackTrace();
            Assert.fail("Product Exception incorrectly thrown");
        }
        List<Product> products = productService.viewAllProduct();
        Product actual = products.get(0);
        Assert.assertEquals(testProductName, actual.getProductName());
        Assert.assertEquals(testProductPrice, actual.getProductPrice());
        Assert.assertEquals(testVendorName, actual.getVendorName());
    }

    @Test
    //Attempt to add product with blank values
    public void productServiceAddBlankValues() {
        String testProductName = "";
        Double testProductPrice = null;
        String testVendorName = "";
        Product p = new Product(testProductName,testProductPrice,testVendorName);
        try{
            productService.addProduct(p);
            Assert.fail("Exception not thrown");
        } catch (ProductException | VendorException e){
        }
    }

    @Test
    //Attempt to add product with invalid values
    public void productServiceAddInvalidProductPrice() {
        String testProductName = "product";
        Double testProductPrice = 0.00;
        String testVendorName = "vendor";
        Product p = new Product(testProductName,testProductPrice,testVendorName);
        try{
            productService.addProduct(p);
            Assert.fail("Exception not thrown");
        } catch (ProductException | VendorException e){
        }
    }

    @Test
    //Attempt to add product with a vendor that doesn't already exist
    public void productServiceAddNonExistentVendor() {
        String testProductName = "product";
        Double testProductPrice = 2.25;
        String testVendorName = "matt";
        Product p = new Product(testProductName,testProductPrice,testVendorName);
        try{
            productService.addProduct(p);
            Assert.fail("Exception not thrown");
        } catch (ProductException | VendorException e){
        }
    }

    @Test
    //Delete product
    public void productServiceDeleteProduct() {
        String testProductName = "product";
        Double testProductPrice = 2.25;
        String testVendorName = "vendor";
        Product p = new Product(testProductName, testProductPrice, testVendorName);
        try{
            productService.addProduct(p);
        } catch (VendorException | ProductException e) {
            e.printStackTrace();
            Assert.fail("Product Exception incorrectly thrown");
        }
        productService.deleteProduct(p.getProductID());
    }

    @Test
    public void productServiceViewProductByID() {
        String testProductName = "product";
        Double testProductPrice = 2.25;
        String testVendorName = "vendor";
        Product p = new Product(testProductName, testProductPrice, testVendorName);
        try{
            productService.addProduct(p);
        } catch (VendorException | ProductException e) {
            e.printStackTrace();
            Assert.fail("Product Exception incorrectly thrown");
        }
        productService.viewProduct(p.getProductID());
    }

    @Test
    //Attempt to update product with blank values
    public void productServiceUpdateBlankValues() {
        String testProductName = "";
        Double testProductPrice = null;
        String testVendorName = "";
        Product p = new Product(testProductName,testProductPrice,testVendorName);
        try{
            productService.updateProduct(p);
            Assert.fail("Exception not thrown");
        } catch (ProductException | VendorException e){
        }
    }

    @Test
    //Attempt to update product with invalid values
    public void productServiceUpdateInvalidProductPrice() {
        String testProductName = "product";
        Double testProductPrice = 0.00;
        String testVendorName = "vendor";
        Product p = new Product(testProductName,testProductPrice,testVendorName);
        try{
            productService.updateProduct(p);
            Assert.fail("Exception not thrown");
        } catch (ProductException | VendorException e){
        }
    }

    //how to add product with id # and then retrieve it to view by id??
    //how to update product with id # and then retrieve it to view by id??
}
