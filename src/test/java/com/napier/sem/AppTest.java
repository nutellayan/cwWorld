package com.napier.sem;

import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    void getContinentPopulationTest() {
        // Create new DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();
        // Connect to the database
        dbManager.connect("localhost:33060", 30000);

        // Call the method under test
        long population = dbManager.getContinentPopulation("Africa");

        // Disconnect from the database
        dbManager.disconnect();

        // Assert the result
        // You can use your preferred assertion library to perform assertions
        // For example, JUnit provides assertions in the Assertions class
        // Here, we're just printing the result for demonstration
        System.out.println("Population of Africa: " + population);
    }

    @Test
    void getRegionPopulationTest() {
        // Create new DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();
        // Connect to the database
        dbManager.connect("localhost:33060", 30000);

        // Call the method under test
        long population = dbManager.getRegionPopulation("Central Africa");

        // Disconnect from the database
        dbManager.disconnect();

        // Assert the result
        System.out.println("Population of Central Africa: " + population);
    }

    // Add similar test methods for other functionalities such as getCountryPopulation, getDistrictPopulation, etc.
}
