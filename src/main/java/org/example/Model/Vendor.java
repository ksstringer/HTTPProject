package org.example.Model;

import java.util.Objects;

public class Vendor {

    private String vendorName;

    //default constructor required for jackson
    public Vendor() {

    }

    public Vendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "vendorName='" + vendorName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return Objects.equals(vendorName, vendor.vendorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorName);
    }
}
