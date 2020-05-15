package com.motoshop.persistance.factories;

import com.motoshop.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFactory {

    private String ID = "ID";
    private String AGE = "AGE";
    private String NAME = "NAME";
    private String PHONE = "PHONE";
    private String BONUSES = "BONUSES";


    public Customer build(ResultSet rs) throws SQLException {
        Customer result = new Customer();
        result.setAge(rs.getInt(AGE));
        result.setId(rs.getInt(ID));
        result.setName(rs.getString(NAME));
        result.setPhone(rs.getString(PHONE));
        result.setBonuses(rs.getInt(BONUSES));
        return result;
    }
}
