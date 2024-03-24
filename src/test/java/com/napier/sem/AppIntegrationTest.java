package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppIntegrationTest {
    static DatabaseManager dbManager;

    @BeforeAll
    static void init() {
        dbManager = new DatabaseManager();
        dbManager.connect("localhost:33060", 30000);
    }

    @Test
    void testPrintCities() {
        // Call the method under test to retrieve the cities from the database
        ArrayList<City> capitals = dbManager.getCapitalCitiesByRegionPopulation();

        // Assert that the returned list is not null and not empty
        assertNotNull(capitals);
        assertFalse(capitals.isEmpty());
    }

}