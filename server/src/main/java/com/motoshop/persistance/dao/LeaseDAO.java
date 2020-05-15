package com.motoshop.persistance.dao;

import com.motoshop.model.Customer;
import com.motoshop.model.Lease;
import com.motoshop.persistance.factories.CustomerFactory;
import com.motoshop.persistance.factories.LeaseFactory;
import com.motoshop.tools.SessionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LeaseDAO {
    private final String getAllLeases = "SELECT * FROM LEASES";
    private LeaseFactory factory = new LeaseFactory();

    public ArrayList<Lease> getAllLease() {
        Connection connection = SessionFactory.getConnection();
        ArrayList<Lease> results = new ArrayList<>();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(getAllLeases);

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
