package com.napier.sem;

import java.sql.*;
import java.util.Scanner;

public class App {

    private Connection con = null;

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(10000);
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException | InterruptedException ex) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(ex.getMessage());
            }
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Disconnected from database");
            } catch (SQLException e) {
                System.out.println("Error closing connection to database");
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.connect();

        // Create PopulationReport instance with the established Connection object
        PopulationReport populationReport = new PopulationReport(app.con);

        // Get user input for entity type and entity name
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the entity type (city, district, country, region, continent, or world):");
        String entityType = scanner.nextLine();
        System.out.println("Enter the entity name:");
        String entityName = scanner.nextLine();

        // Generate population report based on user input
        long population = populationReport.getPopulation(entityName, entityType);
        System.out.println("Population of " + entityName + ": " + population);

        app.disconnect();
    }
}

    class PopulationReport {
    private final Connection con;

    public PopulationReport(Connection con) {
        this.con = con;
    }

    public long getPopulation(String entityName, String entityType) {
        long population = 0;

        // Query to get population based on entity type
        String query;
        switch (entityType) {
            case "city":
                query = "SELECT population FROM city WHERE name = ?";
                break;
            case "district":
                query = "SELECT SUM(population) FROM city WHERE district = ?";
                break;
            case "country":
                query = "SELECT population FROM country WHERE name = ?";
                break;
            case "region":
                query = "SELECT SUM(population) FROM city WHERE countrycode IN (SELECT code FROM country WHERE region = ?)";
                break;
            case "continent":
                query = "SELECT SUM(population) FROM city WHERE countrycode IN (SELECT code FROM country WHERE continent = ?)";
                break;
            case "world":
                query = "SELECT SUM(population) FROM city";
                break;
            default:
                System.out.println("Invalid entity type");
                return population;
        }

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            if (!entityType.equals("world")) {
                stmt.setString(1, entityName);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                population = rs.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return population;
    }
}

