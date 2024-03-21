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
                String district = rs.getString("District");
                String continent = rs.getString("Continent"); // Add continent
                int population = rs.getInt("Population");
                City city = new City(name, country, district, continent, population, "Free City"); // Adjust constructor call
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println("Error processing ResultSet: " + e.getMessage());
        }
        return cities;
    }


    public ArrayList<City> getAllCities() {
        ArrayList<City> cities = new ArrayList<>();
        try {
            String query = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "ORDER BY country.Continent, city.Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            cities = getCitiesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return cities;
    }
    public ArrayList<City> getCitiesByContinent(String continent) {
        ArrayList<City> cities = new ArrayList<>();
        try {
            // Construct the SQL query with the continent value embedded
            String query = "SELECT city.Name, country.Name AS Country, city.District, city.Population, country.Continent " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Continent IN (SELECT DISTINCT Continent FROM country)" +
                    "ORDER BY country.Continent, city.Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            cities = getCitiesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return cities;
    }

    public ArrayList<City> getCitiesByRegion(String region) {
        ArrayList<City> cities = new ArrayList<>();
        try {
            String query = "SELECT city.Name, country.Name AS Country, city.District, city.Population, country.Region " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Region IN (SELECT DISTINCT Region FROM country)" +
                    "ORDER BY country.Region ASC, city.Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            cities = getCitiesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return cities;
    }



    public ArrayList<City> getCitiesByCountry(String countryName) {
        ArrayList<City> cities = new ArrayList<>();
        try {
            String query = "SELECT city.Name, country.Name AS Country, city.District, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "ORDER BY country.Name ASC, city.Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            cities = getCitiesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return cities;
    }


    public ArrayList<City> getCitiesByDistrict(String district) {
        ArrayList<City> cities = new ArrayList<>();
        try {
            String query = "SELECT city.Name AS City_Name, country.Name AS Country, city.District, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "WHERE country.Continent IN (SELECT DISTINCT Continent FROM country)" +
                    "ORDER BY City.District ASC, City.Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            cities = getCitiesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return cities;
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
