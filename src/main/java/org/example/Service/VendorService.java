package org.example.Service;

import org.example.Exception.VendorException;
import org.example.Main;
import org.example.Model.Vendor;

import java.util.ArrayList;
import java.util.List;

public class VendorService {

    List<Vendor> vendors;

    public VendorService() {
        vendors = new ArrayList<>();
    }

    public void addVendor(Vendor vendor) throws VendorException {
        Main.log.info("Adding a new vendor." + vendor);
        if (vendor.getVendorName() == null) {
            Main.log.warn("Throwing Vendor Exception for blank vendor name");
            throw new VendorException("Vendor Name cannot be null");
        }else if (vendors.contains(vendor)){
            Main.log.warn("Throwing Vendor Exception for a vendor that already exists");
            throw new VendorException("Vendor already exists");
        }
        vendors.add(vendor);
        //return vendor;
    }

    public List<Vendor> retrieveVendors() {
        Main.log.info("Retrieving all vendors for view: " + vendors);
        return vendors;
    }

    public List<Vendor> getVendorByName(String vendorName) throws VendorException {
        Main.log.info("Retrieving vendor for view: " + vendorName);
        if(vendorName.isEmpty()) {
            Main.log.warn("Throwing Vendor Exception for a blank vendor name");
            throw new VendorException("Vendor name cannot be blank");
        }
        List<Vendor> vendorByName = new ArrayList<>();
        for (Vendor vendor : vendors) {
            if (vendorName.equalsIgnoreCase(vendor.getVendorName())) {
                vendorByName.add(vendor);
            }
        }
        return vendorByName;
    }
}
