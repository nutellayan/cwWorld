package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    private Connection con = null;

    public void connect(String location, int delay) {
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
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    private ArrayList<City> getCitiesFromResultSet(ResultSet rs) {
        ArrayList<City> cities = new ArrayList<>();
        try {
            while (rs.next()) {
                String name = rs.getString("Name");
                String country = rs.getString("Country");
                int population = rs.getInt("Population");
                City city = new City(name, country, "", population); // Adjust constructor call
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println("Error processing ResultSet: " + e.getMessage());
        }
        return cities;
    }

    public ArrayList<City> getCapitalCitiesByPopulation() {
        ArrayList<City> capitals = new ArrayList<>();
        try {
            String query = "SELECT city.Name AS Name, city.CountryCode AS Country, city.Population AS Population " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            capitals = getCitiesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return capitals;
    }

    public ArrayList<City> getCapitalCitiesByContinentPopulation() {
        ArrayList<City> capitals = new ArrayList<>();
        try {
            String query = "SELECT city.Name AS Name, city.Population AS Population, city.CountryCode AS Country, country.Continent AS Continent " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY country.Continent, city.Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            capitals = getCitiesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return capitals;
    }

    public ArrayList<City> getCapitalCitiesByRegionPopulation() {
        ArrayList<City> capitals = new ArrayList<>();
        try {
            String query = "SELECT city.Name AS Name, city.Population AS Population, country.Name AS Country, country.Region AS Region " +
                    "FROM city " +
                    "JOIN country ON city.ID = country.Capital " +
                    "ORDER BY country.Region, city.Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            capitals = getCitiesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return capitals;
    }

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection to database: " + e.getMessage());
            }
        }
    }
}
