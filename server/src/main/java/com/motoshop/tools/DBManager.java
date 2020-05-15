package com.motoshop.tools;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private final static String createMotorcycleTableSQL = "CREATE TABLE MOTORCYCLES (ID INT, TITLE VARCHAR(256), TYPE INT, MILEAGE INT, YEAR DATE, PRICE DECIMAL, ENGINE_SIZE INT)";
    private final static String createMotoTypesTableSQL = "CREATE TABLE TYPES (ID INT, TITLE VARCHAR(64))";
    private final static String createManufactureTableSQL = "CREATE TABLE MANUFACTURERS (ID INT, TITLE VARCHAR(256), LOCATION VARCHAR(256), PHONE VARCHAR(32), URL VARCHAR(256))";
    private final static String createLeasesTableSQL = "CREATE TABLE LEASES (ID INT, FROM_DATE DATE, TO_DATE DATE, MOTORCYCLE_ID INT, CUSTOMER_ID INT, VENDOR_ID INT)";
    private final static String createCustomersTableSQL = "CREATE TABLE CUSTOMERS (ID INT, NAME VARCHAR(256), AGE INT, PHONE VARCHAR(32), BONUSES INT)";
    private final static String createVendorsTableSQL = "CREATE TABLE VENDORS (ID INT, NAME VARCHAR(256), AGE INT, PHONE VARCHAR(32))";

    private final static String clearMotorcycleTableSQL = "DELETE FROM MOTORCYCLES";
    private final static String clearMotoTypesTableSQL = "DELETE FROM TYPES";
    private final static String clearManufactureTableSQL = "DELETE FROM MANUFACTURERS";
    private final static String clearLeasesTableSQL = "DELETE FROM LEASES";
    private final static String clearVendorsTableSQL = "DELETE FROM VENDORS";
    private final static String clearCustomersTableSQL = "DELETE FROM CUSTOMERS";


    private final static String createDatabaseSQL = "CREATE DATABASE MOTOSHOP";

    private final static String setInitialTypesDataSQL = "INSERT INTO TYPES (ID, TITLE) VALUES " +
            "(1, 'Street'), " +
            "(2, 'Sport'), " +
            "(3, 'Cruiser'), " +
            "(4, 'Dirt bike'), " +
            "(5, 'Enduro')";
    private final static String setInitialManufacturersDataSQL = "INSERT INTO MANUFACTURERS (ID, TITLE) VALUES " +
            "(1, 'Honda'), " +
            "(2, 'Yamaha'), " +
            "(3, 'KTM'), " +
            "(4, 'Bajaj'), " +
            "(5, 'Kawasaki')";
    private final static String setInitialCustomersDataSQL = "INSERT INTO CUSTOMERS (NAME, AGE, PHONE, BONUSES) VALUES" +
            "('Misha', 18, '1231231234', 100), " +
            "('Igor', 34, '332323123', 0)";
    private final static String setInitialVendorsDataSQL = "INSERT INTO VENDORS (NAME, AGE, PHONE) VALUES" +
            "('Ivan', 42, '1231231234')";
    private final static String setInitialLeasesDataSQL = "INSERT INTO LEASES (FROM_DATE, TO_DATE, MOTORCYCLE_ID, CUSTOMER_ID, VENDOR_ID) VALUES " +
            "('2020-06-01', '2020-07-01', 1, 1, 1)";
    private final static String setInitialMotoDataSQL = "INSERT INTO MOTORCYCLES (TITLE, TYPE, MILEAGE, YEAR, PRICE, ENGINE_SIZE) VALUES" +
            " ('Bajaj pulsar ns 200', 1, 0, '2012-12-01', 2700.0, 200), " +
            " ('Yamaha R1', 2, 1900, '2020-01-01', 6500.0, 1500)," +
            " ('Honda CB 300R', 1, 0, '2019-06-01', 4600.0, 300), " +
            " ('Kawasaki ZX-6R', 2, 0, '2019-03-01', 10000.0, 1200), " +
            " ('Yamaha YZF-R3', 2, 10, '2015-01-01', 5000.0, 100), " +
            " ('Ducati Diavel', 3, 19000, '2020-05-05', 4600.0, 1260), " +
            " ('KTM 790 Adventure R', 4, 22000, '2015-06-03', 6000.0, 790), " +
            " ('KTM 500 EXC-F', 5, 2400, '2014-03-01', 7600.0, 540) ";

    public static void initDatabase() {
        Connection connection = SessionFactory.getSystemConnection();
        if (connection != null) {
            try {
                tryToCreateDatabase(connection);
                connection.close();
                connection = SessionFactory.getConnection();

                tryToCreateMotoTable(connection);
                tryToCreateTypesTable(connection);
                tryToCreateCustomersTable(connection);
                tryToCreateLeasesTable(connection);
                tryToCreateManufacturersTable(connection);
                tryToCreateVendorsTable(connection);

                tryToClearMotoTable(connection);
                tryToClearCustomersTable(connection);
                tryToClearLeasesTable(connection);
                tryToClearManufacturersTable(connection);
                tryToClearVendorsTable(connection);
                tryToClearTypesTable(connection);

                tryToSetInitialMotoData(connection);
                tryToSetInitialCustomersData(connection);
                tryToSetInitialLeasesData(connection);
                tryToSetInitialManufacturersData(connection);
                tryToSetInitialTypesData(connection);
                tryToSetInitialVendorsData(connection);

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("connection is null");
        }
    }



    private static void tryToCreateDatabase(Connection connection) throws SQLException {
        executeQueryWithExpectedError(connection, createDatabaseSQL, "DATABASE WAS NOT CREATED");
    }

    // Tables
    private static void tryToCreateMotoTable(Connection connection) throws SQLException {
        executeQueryWithExpectedError(connection, createMotorcycleTableSQL, "MOTORCYCLE TABLE WAS NOT CREATED");
    }

    private static void tryToCreateTypesTable(Connection connection) throws SQLException {
        executeQueryWithExpectedError(connection, createMotoTypesTableSQL, "TYPES TABLE WAS NOT CREATED");
    }

    private static void tryToCreateManufacturersTable(Connection connection) throws SQLException {
        executeQueryWithExpectedError(connection, createManufactureTableSQL, "MANUFACTURERS TABLE IS NOT CREATED");
    }

    private static void tryToCreateCustomersTable(Connection connection) throws SQLException {
        executeQueryWithExpectedError(connection, createCustomersTableSQL, "CUSTOMERS TABLE IS NOT CREATED");
    }

    private static void tryToCreateVendorsTable(Connection connection) throws SQLException {
        executeQueryWithExpectedError(connection, createVendorsTableSQL, "VENDORS TABLE IS NOT CREATED");
    }

    private static void tryToCreateLeasesTable(Connection connection) throws SQLException {
        executeQueryWithExpectedError(connection, createLeasesTableSQL, "LEASES TABLE IS NOT CREATED");
    }

    // InitialData
    private static void tryToSetInitialMotoData(Connection connection) throws SQLException {
        executeQuery(connection, setInitialMotoDataSQL);
    }

    private static void tryToSetInitialTypesData(Connection connection) throws SQLException {
        executeQuery(connection, setInitialTypesDataSQL);
    }

    private static void tryToSetInitialManufacturersData(Connection connection) throws SQLException {
        executeQuery(connection, setInitialManufacturersDataSQL);
    }

    private static void tryToSetInitialCustomersData(Connection connection) throws SQLException {
        executeQuery(connection, setInitialCustomersDataSQL);
    }

    private static void tryToSetInitialVendorsData(Connection connection) throws SQLException {
        executeQuery(connection, setInitialVendorsDataSQL);
    }

    private static void tryToSetInitialLeasesData(Connection connection) throws SQLException {
        executeQuery(connection, setInitialLeasesDataSQL);
    }

    // Clear
    private static void tryToClearMotoTable(Connection connection) throws SQLException {
        executeQuery(connection, clearMotorcycleTableSQL);
    }

    private static void tryToClearCustomersTable(Connection connection) throws SQLException {
        executeQuery(connection, clearCustomersTableSQL);
    }

    private static void tryToClearVendorsTable(Connection connection) throws SQLException {
        executeQuery(connection, clearVendorsTableSQL);
    }

    private static void tryToClearTypesTable(Connection connection) throws SQLException {
        executeQuery(connection, clearMotoTypesTableSQL);
    }

    private static void tryToClearManufacturersTable(Connection connection) throws SQLException {
        executeQuery(connection, clearManufactureTableSQL);
    }

    private static void tryToClearLeasesTable(Connection connection) throws SQLException {
        executeQuery(connection, clearLeasesTableSQL);
    }



    private static void executeQueryWithExpectedError(Connection connection, String query, String errorMessage) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
        } catch (PSQLException e) {
            System.out.println(errorMessage);
        }
    }

    private static void executeQuery(Connection connection, String query ) throws SQLException {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
    }
}
