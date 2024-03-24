
package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppIntegrationTest {
    static DatabaseManager dbManager;

    @BeforeAll
    static void init() {
        dbManager = new DatabaseManager();
        dbManager.connect("localhost:33060", 30000);
    }

    @Test
    void testGetWorldPopulation() {
        // Call the method under test to retrieve the world population from the database
        long worldPopulation = dbManager.getWorldPopulation();

        // Assert that the returned value is not negative (assuming world population can't be negative)
        assertTrue(worldPopulation >= 0, "World population should be non-negative");
    }


}