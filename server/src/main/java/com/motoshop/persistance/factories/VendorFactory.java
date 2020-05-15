package com.motoshop.persistance.factories;

import com.motoshop.model.Vendor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorFactory {

    private String ID = "ID";
    private String AGE = "AGE";
    private String NAME = "NAME";
    private String PHONE = "PHONE";


    public Vendor build(ResultSet rs) throws SQLException {
        Vendor result = new Vendor();
        result.setAge(rs.getInt(AGE));
        result.setId(rs.getInt(ID));
        result.setName(rs.getString(NAME));
        result.setPhone(rs.getString(PHONE));
        return result;
    }
}
