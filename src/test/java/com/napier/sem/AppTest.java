package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AppTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
    }

    @Test
    void printCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        // Create a sample country object
        Country country = new Country("LIL", "Liberty Lab", "All", "Freeland", 1, "Free City");
        // Add the country object to the list
        countries.add(country);

        // Call the method under test
        app.printCountries(countries);
    }

    @Test
    void printAllCountriesTestNull() {
        app.printCountries(null);
    }

    @Test
    void printAllCountriesTestEmpty() {
        ArrayList<Country> countries = new ArrayList<>();
        app.printCountries(countries);
    }
    @Test
    void printAllCountriesTest() {
        ArrayList<Country> countries = new ArrayList<>();
        // Create a sample country object
        Country country = new Country("LIL", "Liberty Lab", "All", "Freeland", 1, "Free City");
        countries.add(country);

        // Assuming `app` is an instance of your application class
        // Call the method you want to test
        app.printCountries(countries);
    }

}