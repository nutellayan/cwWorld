package com.napier.sem;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
public class AppTest
{
    static App app;

    // Initialize the App object before running any tests
    static void init() {
        app = new App();
    }

    @Test
    void printCountriesTestNull() {
        init(); // Initialize the App object
        app.printCountries(null); // Test with null input
        // No assertions needed as this is just a method call
    }
    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<>();
        app.printCountries(countries);
    }
    @Test
    void printCountriesTestContainsNull() {
        init(); // Initialize the App object
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null); // Add null element to the list
        app.printCountries(countries); // Test with list containing null
        // No assertions needed as this is just a method call
    }

    @Test
    void printCountries() {
        // Create an ArrayList to hold Country objects
        ArrayList<Country> countries = new ArrayList<>();

        // Create a sample Country object
        Country country = new Country("CN", "China", "Asia", "Eastern Asia", 1277558000
                , "Peking");

        // Add the sample Country object to the list
        countries.add(country);

        // Create an instance of the App class to call the printCountries method
        App app = new App();

        // Call the printCountries method with the list of countries
        app.printCountries(countries);
    }
}