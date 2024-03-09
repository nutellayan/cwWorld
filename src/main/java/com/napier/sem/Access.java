package com.napier.sem;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Access {

    private static final Logger LOGGER = Logger.getLogger(Access.class.getName());
    private static final String DB_URL = "jdbc:mysql://localhost:3306/world";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "example";

    private Connection con;

    public Access() {
        connectToDatabase();
    }

    public void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            LOGGER.info("Successfully connected to database");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to connect to database", e);
        }
    }

    /**
     * Gets the population of the specified entity.
     *
     * @param entityName The name of the entity.
     * @param entityType The type of the entity (city, continent, region, country, or district).
     * @return The population of the entity, or 0 if not found or entity name is blank.
     * @since 11+
     */
    public long getPopulation(String entityName, String entityType) {
        if (entityName.isEmpty()) {
            LOGGER.warning("Entity name cannot be blank");
            return 0;
        }

        String query;
        switch (entityType) {
            case "city":
                query = "SELECT population FROM city WHERE name = ?";
                break;
            case "continent":
                query = "SELECT SUM(population) FROM city WHERE countrycode IN (SELECT code FROM country WHERE continent = ?)";
                break;
            case "region":
                query = "SELECT SUM(population) FROM city WHERE countrycode IN (SELECT code FROM country WHERE region = ?)";
                break;
            case "country":
                query = "SELECT population FROM country WHERE name = ?";
                break;
            case "district":
                query = "SELECT SUM(population) FROM city WHERE district = ?";
                break;
            default:
                LOGGER.warning("Invalid entity type");
                return 0;
        }
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, entityName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve population data", e);
        }
        return 0;
    }

    public static void main(String[] args) {
        Access access = new Access();

        // Example usage:
        System.out.println("Population of London: " + access.getPopulation("London", "city"));
        System.out.println("Population of Europe: " + access.getPopulation("Europe", "continent"));
        System.out.println("Population of Central Africa: " + access.getPopulation("Central Africa", "region"));
        System.out.println("Population of United States: " + access.getPopulation("United States", "country"));
        System.out.println("Population of New York District: " + access.getPopulation("New York", "district"));

        // Blank entity name example:
        System.out.println("Population with blank entity name: " + access.getPopulation("Kabol", "district"));
    }
}
