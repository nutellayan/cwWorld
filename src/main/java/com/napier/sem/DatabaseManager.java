package com.napier.sem;

import java.sql.*;

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

    public void disconnect() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection to database: " + e.getMessage());
            }
        }
    }

    public long getWorldPopulation() {
        long population = 0;
        try {
            String query = "SELECT SUM(Population) AS WorldPopulation FROM country";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                population = rs.getLong("WorldPopulation");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return population;
    }

    public long getContinentPopulation(String continentName) {
        long population = 0;
        try {
            String query = "SELECT SUM(Population) AS ContinentPopulation FROM country WHERE Continent = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, continentName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                population = rs.getLong("ContinentPopulation");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return population;
    }

    public long getRegionPopulation(String regionName) {
        long population = 0;
        try {
            String query = "SELECT SUM(Population) AS RegionPopulation FROM country WHERE Region = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, regionName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                population = rs.getLong("RegionPopulation");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return population;
    }

    public long getCountryPopulation(String countryName) {
        long population = 0;
        try {
            String query = "SELECT Population FROM country WHERE Name = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, countryName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                population = rs.getLong("Population");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return population;
    }

    public long getDistrictPopulation(String districtName) {
        long population = 0;
        try {
            String query = "SELECT SUM(Population) AS DistrictPopulation FROM city WHERE District = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, districtName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                population = rs.getLong("DistrictPopulation");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return population;
    }

    public long getCityPopulation(String cityName) {
        long population = 0;
        try {
            String query = "SELECT Population FROM city WHERE Name = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, cityName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                population = rs.getLong("Population");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return population;
    }
}
