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

        // Retrieve and print countries by continent
        ArrayList<Country> countriesByContinent = a.getCountriesByContinent();
        a.printCountriesByContinent(countriesByContinent);

        // Retrieve and print countries by region
        ArrayList<Country> countriesByRegion = a.getCountriesByRegion();
        a.printCountriesByRegion(countriesByRegion);

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

    private ArrayList<Country> getCountriesFromResultSet(ResultSet rs) {
        ArrayList<Country> countries = new ArrayList<>();
        try {
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
            System.out.println("Error processing ResultSet: " + e.getMessage());
        }
        return countries;
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
            countries = getCountriesFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return countries;
    }

    public ArrayList<Country> getCountriesByRegion() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            String query = "WITH RankedCountries AS (\n" +
                    "    SELECT \n" +
                    "        country.Code,\n" +
                    "        country.Name AS CountryName,\n" +
                    "        country.Continent,\n" +
                    "        country.Region,\n" +
                    "        country.Population,\n" +
                    "        city.Name AS Capital,\n" +
                    "        ROW_NUMBER() OVER (PARTITION BY country.Region ORDER BY country.Population DESC) AS RegionRank\n" +
                    "    FROM \n" +
                    "        country\n" +
                    "    JOIN \n" +
                    "        city ON country.Capital = city.ID\n" +
                    ")\n" +
                    "SELECT \n" +
                    "    Code,\n" +
                    "    CountryName,\n" +
                    "    Continent,\n" +
                    "    Region,\n" +
                    "    Population,\n" +
                    "    Capital\n" +
                    "FROM \n" +
                    "    RankedCountries\n" +
                    "WHERE \n" +
                    "    RegionRank <= 4\n" +
                    "ORDER BY \n" +
                    "    Region,\n" +
                    "    Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("CountryName");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                String capital = rs.getString("Capital");
                Country country = new Country(code, name, continent, region, population, capital);
                countries.add(country);
            }
            rs.close(); // Close the ResultSet
            stmt.close(); // Close the Statement
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return countries;
    }

    public ArrayList<Country> getCountriesByContinent() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            String query = "WITH RankedCountries AS (\n" +
                    "    SELECT \n" +
                    "        c.Code, \n" +
                    "        c.Name AS CountryName, \n" +
                    "        c.Continent, \n" +
                    "        c.Region, \n" +
                    "        c.Population, \n" +
                    "        ct.Name AS Capital,\n" +
                    "        ROW_NUMBER() OVER (PARTITION BY c.Continent ORDER BY c.Population DESC) AS ContinentRank\n" +
                    "    FROM \n" +
                    "        country c\n" +
                    "    JOIN \n" +
                    "        city ct ON c.Capital = ct.ID\n" +
                    ")\n" +
                    "SELECT \n" +
                    "    Code, \n" +
                    "    CountryName, \n" +
                    "    Continent, \n" +
                    "    Region, \n" +
                    "    Population, \n" +
                    "    Capital\n" +
                    "FROM \n" +
                    "    RankedCountries\n" +
                    "WHERE \n" +
                    "    ContinentRank <= 10\n" +
                    "ORDER BY \n" +
                    "    Continent, \n" +
                    "    Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String code = rs.getString("Code");
                String name = rs.getString("CountryName");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                int population = rs.getInt("Population");
                String capital = rs.getString("Capital");
                Country country = new Country(code, name, continent, region, population, capital);
                countries.add(country);
            }
            rs.close(); // Close the ResultSet
            stmt.close(); // Close the Statement
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return countries;
    }

    public void printCountries(ArrayList<Country> countries) {
        System.out.println("===========================");
        System.out.println("Country Report by Country");
        System.out.println("===========================");
        System.out.printf("%-10s %-50s %-20s %-40s %-15s %-20s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        for (Country country : countries) {
            String countryInfo = String.format("%-10s %-50s %-20s %-40s %-15d %-20s",
                    country.getCode(), country.getName(), country.getContinent(),
                    country.getRegion(), country.getPopulation(), country.getCapital());
            System.out.println(countryInfo);
        }
    }

    public void printCountriesByContinent(ArrayList<Country> countries) {
        System.out.println("===========================");
        System.out.println("Country Report by Continent");
        System.out.println("===========================");
        System.out.printf("%-10s %-50s %-20s %-40s %-15s %-20s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        for (Country country : countries) {
            String countryInfo = String.format("%-10s %-50s %-20s %-40s %-15d %-20s%n",
                    country.getCode(), country.getName(), country.getContinent(),
                    country.getRegion(), country.getPopulation(), country.getCapital());
            System.out.println(countryInfo);
        }
    }

    public void printCountriesByRegion(ArrayList<Country> countries) {
        System.out.println("========================");
        System.out.println("Country Report by Region");
        System.out.println("========================");
        System.out.printf("%-10s %-50s %-20s %-40s %-15s %-20s%n",
                "Code", "CountryName", "Continent", "Region", "Population", "Capital");
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
            } catch (SQLException e) {
                System.out.println("Error closing connection to database: " + e.getMessage());
            }
        }
    }
}