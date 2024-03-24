package com.napier.sem;

import java.util.ArrayList;


public class App {

    public static void main(String[] args) {
        // Create new DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();
        if (args.length < 1) {
            dbManager.connect("db:33060", 30000);
        } else {
            dbManager.connect(args[0], Integer.parseInt(args[1]));
        }

        // Retrieve and print all cities
        ArrayList<City> allCities = dbManager.getAllCities();
        printCities(allCities, "World");

        // Retrieve and print cities by continent
        ArrayList<City> citiesByContinent = dbManager.getCitiesByContinent("your_continent");
        printCities(citiesByContinent, "Continent");

        // Retrieve and print cities by region
        ArrayList<City> citiesByRegion = dbManager.getCitiesByRegion("your_region");
        printCities(citiesByRegion, "Region");

        // Retrieve and print cities by country
        ArrayList<City> citiesByCountry = dbManager.getCitiesByCountry("your_country");
        printCities(citiesByCountry, "Country");

        // Retrieve and print cities by district
        ArrayList<City> citiesByDistrict = dbManager.getCitiesByDistrict("your_district");
        printCities(citiesByDistrict, "District");


        // Retrieve and print cities by country

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
        System.out.println("City Report by " + header);
        System.out.println("===========================");
        System.out.printf("%-40s %-40s %-30s %-15s%n",
                "City Name", "Country", "District", "Population");
        for (City city : cities) {
            if (city == null)
                continue;
            String cityInfo = String.format("%-40s %-40s %-30s %-15d",
                    city.getName(), city.getCountryName(), city.getDistrict(), city.getPopulation());
            System.out.println(cityInfo);
        }
    }
}
