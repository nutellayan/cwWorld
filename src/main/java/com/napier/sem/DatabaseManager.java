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

    private ArrayList<PopulationData> getPopulationReportDataFromResultSet(ResultSet rs) {
        ArrayList<PopulationData> populationDataList = new ArrayList<>();
        try {
            while (rs.next()) {
                String name = rs.getString("Name");
                long totalPopulation = rs.getLong("TotalPopulation");
                long populationInCities = rs.getLong("PopulationInCities");
                double percentageInCities = rs.getDouble("PercentageInCities");
                long populationNotInCities = rs.getLong("PopulationNotInCities");
                double percentageNotInCities = rs.getDouble("PercentageNotInCities");

                PopulationData populationData = new PopulationData(name, totalPopulation, populationInCities,
                        percentageInCities, populationNotInCities, percentageNotInCities);
                populationDataList.add(populationData);
            }
        } catch (SQLException e) {
            System.out.println("Error processing ResultSet: " + e.getMessage());
        }
        return populationDataList;
    }

    public ArrayList<PopulationData> getPopulationReportDataByContinent() {
        ArrayList<PopulationData> populationDataList = new ArrayList<>();
        try {
            String query = "SELECT c.Continent as Name, " +
                    "       SUM(c.Population) as TotalPopulation, " +
                    "       SUM(ci.Population) as PopulationInCities, " +
                    "       ROUND((SUM(ci.Population) / SUM(c.Population)) * 100, 2) as PercentageInCities, " +
                    "       SUM(c.Population - ci.Population) as PopulationNotInCities, " +
                    "       ROUND((SUM(c.Population - ci.Population) / SUM(c.Population)) * 100, 2) as PercentageNotInCities " +
                    "FROM country c " +
                    "LEFT JOIN ( " +
                    "    SELECT CountryCode, SUM(Population) as Population " +
                    "    FROM city " +
                    "    GROUP BY CountryCode " +
                    ") ci ON c.Code = ci.CountryCode " +
                    "GROUP BY c.Continent";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            populationDataList = getPopulationReportDataFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return populationDataList;
    }
    public ArrayList<PopulationData> getPopulationReportDataByRegion() {
        ArrayList<PopulationData> populationDataList = new ArrayList<>();
        try {
            String query = "SELECT co.Region AS Name, " +
                    "       SUM(c.Population) AS TotalPopulation, " +
                    "       SUM(co.Population) AS PopulationInCountries, " +
                    "       SUM(ci.Population) AS PopulationInCities, " +
                    "       ROUND((SUM(ci.Population) / SUM(c.Population)) * 100, 2) AS PercentageInCities, " +
                    "       SUM(c.Population - ci.Population) AS PopulationNotInCities, " +
                    "       ROUND((SUM(c.Population - ci.Population) / SUM(c.Population)) * 100, 2) AS PercentageNotInCities " +
                    "FROM country c " +
                    "LEFT JOIN ( " +
                    "    SELECT Region, SUM(Population) AS Population " +
                    "    FROM country " +
                    "    GROUP BY Region " +
                    ") co ON c.Region = co.Region " +
                    "LEFT JOIN ( " +
                    "    SELECT CountryCode, SUM(Population) AS Population " +
                    "    FROM city " +
                    "    GROUP BY CountryCode " +
                    ") ci ON c.Code = ci.CountryCode " +
                    "GROUP BY co.Region";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            populationDataList = getPopulationReportDataFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return populationDataList;
    }

    public ArrayList<PopulationData> getPopulationReportDataByCountry() {
        ArrayList<PopulationData> populationDataList = new ArrayList<>();
        try {
            String query = "SELECT co.Name AS Name, " +
                    "       SUM(c.Population) AS TotalPopulation, " +
                    "       SUM(co.Population) AS PopulationInCountries, " +
                    "       SUM(ci.Population) AS PopulationInCities, " +
                    "       ROUND((SUM(ci.Population) / SUM(c.Population)) * 100, 2) AS PercentageInCities, " +
                    "       SUM(c.Population - ci.Population) AS PopulationNotInCities, " +
                    "       ROUND((SUM(c.Population - ci.Population) / SUM(c.Population)) * 100, 2) AS PercentageNotInCities " +
                    "FROM country c " +
                    "LEFT JOIN ( " +
                    "    SELECT Name, SUM(Population) AS Population " +
                    "    FROM country " +
                    "    GROUP BY Name " +
                    ") co ON c.Name = co.Name " +
                    "LEFT JOIN ( " +
                    "    SELECT CountryCode, SUM(Population) AS Population " +
                    "    FROM city " +
                    "    GROUP BY CountryCode " +
                    ") ci ON c.Code = ci.CountryCode " +
                    "GROUP BY co.Name";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            populationDataList = getPopulationReportDataFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return populationDataList;
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
