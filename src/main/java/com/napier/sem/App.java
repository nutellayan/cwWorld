package com.napier.sem;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.SQLException;

public class App
{
    public static void main(String[] args) {
        // Create a new instance of App
        App app = new App();

        // Connect to the database
        app.connect();

        // Check if the connection is successful
        if (app.con != null) {
            // If connected, proceed to fetch countries and print them
            ArrayList<World> countries = app.getAllCountries();
            app.printCountries(countries);
            System.out.println("Number of countries retrieved: " + countries.size());
        } else {
            // If connection fails, print an error message
            System.out.println("Failed to connect to the database.");
        }

        // Disconnect from the database
        app.disconnect();
    }

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
            } catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
    public ArrayList<World> getAllCountries() {
        ArrayList<World> countries = new ArrayList<>();
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
                World country = new World(code, name, continent, region, population, capital);
                countries.add(country);
            }
            return countries;
        }
         catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            return null;
        }

    }
    public void printCountries(ArrayList<World> countries) {
        // Print header
        System.out.printf("%-10s %-50s %-20s %-40s %-15s %-20s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        // Loop over all countries in the list
        for (World country : countries) {
            String countryInfo = String.format("%-10s %-50s %-20s %-40s %-15d %-20s",
                    country.code, country.name, country.continent,
                    country.region, country.population, country.capital);
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


}
