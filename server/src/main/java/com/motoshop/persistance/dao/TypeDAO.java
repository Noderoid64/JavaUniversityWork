package com.motoshop.persistance.dao;

import com.motoshop.model.Motorcycle;
import com.motoshop.tools.SessionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class TypeDAO {

    private final String getAllTypesSQL = "SELECT * FROM TYPES";

    public Map<Integer, String> getTypes() {
        Connection connection = SessionFactory.getConnection();
        Map<Integer, String> results = new HashMap<>();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(getAllTypesSQL);

                while(rs.next()){
                    results.put(rs.getInt("ID"), rs.getString("TITLE"));
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
