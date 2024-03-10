package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

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
                Thread.sleep(30000);
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException | InterruptedException ex) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(ex.getMessage());
            }
        }
    }

    public ArrayList<Country> getAllCountries() {
        ArrayList<Country> countries = new ArrayList<>();
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
                    country.getCode(), country.getName(), country.getContinent(),
                    country.getRegion(), country.getPopulation(), country.getCapital());
            System.out.println(countryInfo);
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

        ArrayList<Country> countries = app.getAllCountries();

        // Print Countries
        app.printCountries(countries);

        // Test the size of the returned data
        System.out.println("Number of countries retrieved: " + countries.size());

        app.disconnect();
    }
}
