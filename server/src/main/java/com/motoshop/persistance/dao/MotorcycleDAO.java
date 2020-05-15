package com.motoshop.persistance.dao;

import com.motoshop.model.Motorcycle;
import com.motoshop.persistance.factories.MotorcycleFactory;
import com.motoshop.tools.SessionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class MotorcycleDAO {

    private String getAllMotorcyclesSQL = "SELECT * FROM PUBLIC.MOTORCYCLES";
    private String setNewMotorcycleSQL = "INSERT INTO MOTORCYCLES (TITLE, TYPE, MILEAGE, YEAR, PRICE, ENGINE_SIZE) VALUES ";

    private MotorcycleFactory factory = new MotorcycleFactory();
    private TypeDAO typeDAO = new TypeDAO();

    public boolean addNewRecord(Motorcycle motorcycle) {
        Connection connection = SessionFactory.getConnection();
        boolean results = false;
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = setNewMotorcycleSQL +
                        "('" + motorcycle.getTitle() + "', " +
                        motorcycle.getType_id() + ", " +
                        motorcycle.getMileage() + ", '" +
                        motorcycle.getYear() + "'," +
                        motorcycle.getPrice() + ", " +
                        motorcycle.getEngineSize() + ")";
                ResultSet rs = statement.executeQuery(query);

                statement.close();
                connection.close();
                results = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("connection is null");
        }
        return results;
    }

    public ArrayList<Motorcycle> getAllMotorcycles() {

        Map<Integer, String> types = typeDAO.getTypes();

        Connection connection = SessionFactory.getConnection();
        ArrayList<Motorcycle> results= new ArrayList<>();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(getAllMotorcyclesSQL);

                while(rs.next()){
                    results.add(factory.build(rs, types));
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
