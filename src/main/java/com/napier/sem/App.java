package com.napier.sem;

import java.sql.*;

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
                Thread.sleep(10000);
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException | InterruptedException ex) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(ex.getMessage());
            }
        }
    }

    public void printLanguageStatistics() {
        try {
            String query = "SELECT " +
                    "    Language, " +
                    "    TotalLanguage AS \"Total Number of People\", " +
                    "    TotalLanguage / World.Population AS Percentage " +
                    "FROM " +
                    "    (SELECT " +
                    "        Language, " +
                    "        SUM(countrylanguage.Percentage * country.Population) AS TotalLanguage " +
                    "    FROM " +
                    "        countrylanguage " +
                    "    JOIN " +
                    "        country ON country.Code = countrylanguage.CountryCode " +
                    "    WHERE " +
                    "        countrylanguage.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                    "    GROUP BY " +
                    "        Language) AS languagepopulation " +
                    "JOIN " +
                    "    (SELECT " +
                    "        SUM(Population) AS Population " +
                    "    FROM " +
                    "        country) AS World " +
                    "ORDER BY " +
                    "    TotalLanguage DESC;";

            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("===========================");
            System.out.println("Language Statistics");
            System.out.println("===========================");
            System.out.printf("%-20s %-30s %-20s%n", "Language", "Total Number of People", "Percentage");

            while (rs.next()) {
                String language = rs.getString("Language");
                double totalPopulation = rs.getDouble("Total Number of People"); // Update variable name
                double percentage = rs.getDouble("Percentage");

                System.out.printf("%-20s %-30.0f %-20f%n", language, totalPopulation, percentage); // Adjust format for totalPopulation
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
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
        // Print Countries
        app.printLanguageStatistics();

        app.disconnect();
    }
}