package com.motoshop.persistance.factories;

import com.motoshop.model.Customer;
import com.motoshop.model.Lease;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaseFactory {

    private String ID = "ID";
    private String VENDOR_ID = "VENDOR_ID";
    private String CUSTOMER_ID = "CUSTOMER_ID";
    private String MOTORCYCLE_ID = "MOTORCYCLE_ID";

    public Lease build(ResultSet rs) throws SQLException {
        Lease result = new Lease();
        result.setId(rs.getInt(ID));
        result.setCustomer_id(rs.getInt(CUSTOMER_ID));
        result.setMotorcycle_id(rs.getInt(MOTORCYCLE_ID));
        result.setVendor_id(rs.getInt(VENDOR_ID));
        return result;
    }
}
