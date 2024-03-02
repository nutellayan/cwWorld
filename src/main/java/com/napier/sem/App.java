package com.napier.sem;

import java.sql.*;

public class App {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException | InterruptedException ex) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Generate and display country report for top 60 countries.
     */
    public void generateCountryReport() {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();

            // SQL query to retrieve country data for top 60 countries
            String query = "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT 60";


            // Execute SQL query
            ResultSet rs = stmt.executeQuery(query);

            // Display country report
            System.out.println("Country Report:");
            System.out.println("==================================================");
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                String capital = rs.getString("Capital");
                System.out.printf("%-10s %-40s %-20s %-20s %-15d %-20s\n", code, name, continent, region, population, capital);
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
                System.out.println("Disconnected from database");
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Create new Application
        App app = new App();

        // Connect to database
        app.connect();

        // Generate and display country report for top 60 countries
        app.generateCountryReport();

        // Disconnect from database
        app.disconnect();
    }
}
