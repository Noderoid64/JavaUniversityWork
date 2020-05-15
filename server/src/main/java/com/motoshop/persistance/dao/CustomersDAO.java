package com.motoshop.persistance.dao;

import com.motoshop.model.Customer;
import com.motoshop.persistance.factories.CustomerFactory;
import com.motoshop.tools.SessionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomersDAO {

    private final String getAllCustomersDAO = "SELECT * FROM CUSTOMERS";
    private CustomerFactory factory = new CustomerFactory();

    public ArrayList<Customer> getAllCustomers() {
        Connection connection = SessionFactory.getConnection();
        ArrayList<Customer> results = new ArrayList<>();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(getAllCustomersDAO);

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
