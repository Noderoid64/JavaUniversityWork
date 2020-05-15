package com.motoshop.persistance.dao;

import com.motoshop.model.Customer;
import com.motoshop.model.Vendor;
import com.motoshop.persistance.factories.CustomerFactory;
import com.motoshop.persistance.factories.VendorFactory;
import com.motoshop.tools.SessionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VendorsDAO {

    private final String getAllVendorsDAO = "SELECT * FROM VENDORS";
    private VendorFactory factory = new VendorFactory();

    public ArrayList<Vendor> getAllVendors() {
        Connection connection = SessionFactory.getConnection();
        ArrayList<Vendor> results = new ArrayList<>();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(getAllVendorsDAO);

                while(rs.next()){
                    results.add(factory.build(rs));
                }

                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("connection is null");
        }
        return results;
    }
}
