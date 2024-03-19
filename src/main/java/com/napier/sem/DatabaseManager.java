package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {

    private Connection con = null;

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
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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
            String query = "SELECT City.Name AS Name, City.CountryCode AS Country, City.Population AS Population " +
                    "FROM City " +
                    "JOIN Country ON City.ID = Country.Capital " +
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
            String query = "SELECT City.Name AS Name, City.Population AS Population, City.CountryCode AS Country, Country.Continent AS Continent " +
                    "FROM City " +
                    "JOIN Country ON City.ID = Country.Capital " +
                    "ORDER BY Country.Continent, City.Population DESC";
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
            String query = "SELECT City.Name AS Name, City.Population AS Population, Country.Name AS Country, Country.Region AS Region " +
                    "FROM City " +
                    "JOIN Country ON City.ID = Country.Capital " +
                    "ORDER BY Country.Region, City.Population DESC";
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
