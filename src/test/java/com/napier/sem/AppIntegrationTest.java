package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("db:33060", 10000);
    }

    @Test
    void testPrintCountries() {
        // Call the method under test to retrieve the countries from the database
        ArrayList<Country> countries = app.getAllCountries();

        // Assert that the returned list is not null and not empty
        assertNotNull(countries);
        assertFalse(countries.isEmpty());

    }

}