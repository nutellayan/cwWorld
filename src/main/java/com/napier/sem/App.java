package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    private Connection con = null;

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Retrieve and print countries
        ArrayList<Country> countries = a.getAllCountries();
        a.printCountries(countries);

        // Display the number of countries retrieved
        System.out.println("Number of countries retrieved: " + countries.size());

        // Disconnect from database
        a.disconnect();
    }

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
                Thread.sleep(10000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public ArrayList<Country> getAllCountries() {
        ArrayList<Country> countries = null;
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();

            // SQL query to retrieve country data with the name of their capital cities
            String query = "SELECT country.Code, country.Name, country.Continent, country.Region, " +
                    "country.Population, city.Name AS Capital " +
                    "FROM country " +
                    "JOIN city ON country.Capital = city.ID " +
                    "ORDER BY country.Population DESC " +
                    "LIMIT 60";

            // Execute SQL query
            ResultSet rs = stmt.executeQuery(query);
            countries = new ArrayList<>();
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                String capital = rs.getString("Capital");
                Country country = new Country(code, name, continent, region, population, capital);
                countries.add(country);
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return countries;
    }

    public void printCountries(ArrayList<Country> countries) {
        // Print header
        System.out.printf("%-10s %-50s %-20s %-40s %-15s %-20s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        // Loop over all countries in the list
        for (Country country : countries) {
            String countryInfo = String.format("%-10s %-50s %-20s %-40s %-15d %-20s",
                    country.code, country.name, country.continent,
                    country.region, country.population, country.capital);
            System.out.println(countryInfo);
        }
    }

    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
