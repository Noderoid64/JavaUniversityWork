package com.motoshop.model;

public class Lease {

    private int id;
    private int customer_id;
    private int vendor_id;
    private int motorcycle_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getMotorcycle_id() {
        return motorcycle_id;
    }

    public void setMotorcycle_id(int motorcycle_id) {
        this.motorcycle_id = motorcycle_id;
    }
}
