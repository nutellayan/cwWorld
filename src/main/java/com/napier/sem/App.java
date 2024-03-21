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
            // SQL query 1
            String query = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "ORDER BY Population DESC " +
                    "LIMIT 60";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            countries = getCountriesFromResultSet(rs);
            rs.close();
            stmt.close();
            // Execute SQL query
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return countries;
    }

    public ArrayList<Country> getCountriesByContinent() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            String query = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM country " +
                    "WHERE (" +
                    "   SELECT COUNT(*) " +
                    "   FROM country AS c " +
                    "   WHERE c.Continent = country.Continent " +
                    "       AND c.Population >= country.Population" +
                    ") <= 10 " +
                    "AND Continent IN ('North America', 'Asia', 'Africa', 'Europe', 'Oceania', 'Antarctica') " +
                    "ORDER BY Continent, Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            countries = getCountriesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return countries;
    }

    public ArrayList<Country> getCountriesByRegion() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            // SQL query 3
            String query = "SELECT Code, Name, Continent, Region, Population, Capital " +
                    "FROM (" +
                    "    SELECT " +
                    "        Code, Name, Continent, Region, Population, Capital, " +
                    "        ROW_NUMBER() OVER (PARTITION BY Region ORDER BY Population DESC) AS row_num " +
                    "    FROM country " +
                    "    WHERE Region IN (" +
                    "        'Caribbean', 'Southern and Central Asia', 'Central Africa', " +
                    "        'Central America', 'Southern Europe', 'Middle East', 'Polynesia', " +
                    "        'Antarctica', 'Australia and New Zealand', 'Western Europe', " +
                    "        'Eastern Africa', 'Nordic Countries', 'South America', " +
                    "        'Western Africa', 'British Islands', 'Baltic Countries', " +
                    "        'Micronesia', 'Melanesia', 'Eastern Asia', 'Eastern Europe', " +
                    "        'Southeast Asia'" +
                    "    )" +
                    ") AS ranked_countries " +
                    "WHERE row_num <= 4 " +
                    "ORDER BY Region, Population DESC";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            countries = getCountriesFromResultSet(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
        return countries;
    }

    public void printCountries(ArrayList<Country> countries) {
        if (countries == null)
        {
            System.out.println("No countries");
            return;
        }
        System.out.println("===========================");
        System.out.println("Country Report by Country");
        System.out.println("===========================");
        System.out.printf("%-10s %-20s %-20s %-20s %-15s %-20s%n",
                "Code", "Name", "Continent", "Region", "Population", "Capital");
        for (Country country : countries) {
            if (country == null)
                continue;
            String countryInfo = String.format("%-10s %-20s %-20s %-20s %-15d %-20s",
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
