package com.napier.sem;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // Create new DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();

        // Connect to the database
        dbManager.connect();

        // Retrieve and print all capital cities
        ArrayList<City> capitalCitiesByPopulation = dbManager.getCapitalCitiesByPopulation();
        printCities(capitalCitiesByPopulation, "World Capitals by Population");

        // Retrieve and print capital cities by continent and population
        ArrayList<City> capitalCitiesByContinentPopulation = dbManager.getCapitalCitiesByContinentPopulation();
        printCities(capitalCitiesByContinentPopulation, "Capital Cities by Continent and Population");

        // Retrieve and print capital cities by region and population
        ArrayList<City> capitalCitiesByRegionPopulation = dbManager.getCapitalCitiesByRegionPopulation();
        printCities(capitalCitiesByRegionPopulation, "Capital Cities by Region and Population");

        // Disconnect from the database
        dbManager.disconnect();
    }

    public static void printCities(ArrayList<City> cities, String header) {
        if (cities == null) {
            System.out.println("No cities");
            return;
        }

        if (header == null) {
            System.out.println("Invalid header");
            return;
        }
        System.out.println("===========================");
        System.out.println(header);
        System.out.println("===========================");
        System.out.printf("%-40s %-40s %-15s%n",
                "City Name", "Country", "Population");
        for (City city : cities) {
            if (city == null)
                continue;
            String cityInfo = String.format("%-40s %-40s %-15d",
                    city.getName(), city.getCountryName(), city.getPopulation());
            System.out.println(cityInfo);
        }
    }
}
