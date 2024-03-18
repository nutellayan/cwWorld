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
                String name = rs.getString("CityName");
                String country = rs.getString("Country");
                String district = rs.getString("District");
                                int population = rs.getInt("Population");
                City city = new City(name, country, district, population); // Adjust constructor call
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
            String query = "SELECT city.Name AS CityName, country.Name AS Country, city.District, city.Population " +
                    "FROM city " +
                    "JOIN country ON city.CountryCode = country.Code " +
                    "ORDER BY city.Population DESC " +
                    "LIMIT 50";
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
            String query = "SELECT ranked_cities.CityName, ranked_cities.Country, ranked_cities.District, ranked_cities.Population, ranked_cities.Continent " +
                    "FROM (" +
                    "    SELECT " +
                    "        city.Name AS CityName, " +
                    "        country.Name AS Country, " +
                    "        city.District, " +
                    "        city.Population, " +
                    "        country.Continent, " +
                    "        ROW_NUMBER() OVER (PARTITION BY country.Continent ORDER BY city.Population DESC) AS row_num " +
                    "    FROM city " +
                    "    JOIN country ON city.CountryCode = country.Code " +
                    ") AS ranked_cities " +
                    "WHERE row_num <= 6 " +
                    "ORDER BY ranked_cities.Continent, ranked_cities.Population DESC";
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
            // Construct the SQL query with the region value embedded
            String query = "SELECT ranked_cities.CityName, ranked_cities.Country, ranked_cities.District, " +
                    "ranked_cities.Population, ranked_cities.Region " +
                    "FROM (" +
                    "    SELECT " +
                    "        city.Name AS CityName, " +
                    "        country.Name AS Country, " +
                    "        city.District, " +
                    "        city.Population, " +
                    "        country.Region, " +
                    "        ROW_NUMBER() OVER (PARTITION BY country.Region ORDER BY city.Population DESC) AS row_num " +
                    "    FROM city " +
                    "    JOIN country ON city.CountryCode = country.Code " +
                    ") AS ranked_cities " +
                    "WHERE row_num <= 6 " +
                    "ORDER BY ranked_cities.Region, ranked_cities.Population DESC";
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

    public ArrayList<City> getCitiesByCountry(String country) {
        ArrayList<City> cities = new ArrayList<>();
        try {
            // Construct the SQL query with the region value embedded
            String query = "SELECT ranked_cities.CityName, ranked_cities.Country, ranked_cities.District, " +
                    "       ranked_cities.Population " +
                    "FROM (" +
                    "    SELECT city.Name AS CityName, country.Name AS Country, city.District, city.Population, country.Region, " +
                    "           ROW_NUMBER() OVER (PARTITION BY city.CountryCode ORDER BY city.Population DESC) AS row_num " +
                    "    FROM city " +
                    "    JOIN country ON city.CountryCode = country.Code " +
                    ") AS ranked_cities " +
                    "WHERE row_num <= 2 " +
                    "ORDER BY ranked_cities.Country";

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
            // Construct the SQL query with the region value embedded
            String query = "SELECT ranked_cities.CityName, ranked_cities.Country, ranked_cities.District, " +
                    "       ranked_cities.Population " +
                    "FROM (" +
                    "    SELECT city.Name AS CityName, country.Name AS Country, city.District, city.Population, " +
                    "           ROW_NUMBER() OVER (PARTITION BY city.District ORDER BY city.Population DESC) AS row_num " +
                    "    FROM city " +
                    "    JOIN country ON city.CountryCode = country.Code " +
                    ") AS ranked_cities " +
                    "WHERE row_num <= 2 " +
                    "ORDER BY ranked_cities.District";
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
