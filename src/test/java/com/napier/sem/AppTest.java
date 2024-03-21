package com.napier.sem;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class AppTest {

    @Test
    void printCities() {
        ArrayList<City> cities = new ArrayList<>();
        // Create a sample city object
        City city = new City("Sydney", "Australia", "All", "Freeland", 1, "Free City");
        // Add the city object to the list
        cities.add(city);

        // Call the method under test
        App.printCities(cities, "Test Header");
    }

    @Test
    void printCitiesTestNull() {
        App.printCities(null, "Null Test");
    }

    @Test
    void printCitiesTestEmpty() {
        ArrayList<City> cities = new ArrayList<>();
        App.printCities(cities, "Empty Test");
    }

    @Test
    void printCitiesTestContainsNull() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        App.printCities(cities, "Contains Null Test");
    }
}
