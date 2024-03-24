package com.napier.sem;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Create new DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();

        if (args.length < 1) {
            dbManager.connect("db:33060", 30000);
        } else {
            dbManager.connect(args[0], Integer.parseInt(args[1]));
        }

        // Prompt the user for input
        Scanner scanner = new Scanner(System.in);
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println("-------------------");
            System.out.println("Please select an option:");
            System.out.println("1. Get population of a continent");
            System.out.println("2. Get population of a region");
            System.out.println("3. Get population of a country");
            System.out.println("4. Get population of a district");
            System.out.println("5. Get population of a city");
            System.out.println("6. Get population of the world");
            System.out.println("7. Stop");
            System.out.println("-------------------");
        int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Enter the name of the continent:");
                    String continentName = scanner.nextLine();
                    long continentPopulation = dbManager.getContinentPopulation(continentName);
                    System.out.println("Population of " + continentName + ": " + continentPopulation);
                    break;
                case 2:
                    System.out.println("Enter the name of the region:");
                    String regionName = scanner.nextLine();
                    long regionPopulation = dbManager.getRegionPopulation(regionName);
                    System.out.println("Population of " + regionName + ": " + regionPopulation);
                    break;
                case 3:
                    System.out.println("Enter the name of the country:");
                    String countryName = scanner.nextLine();
                    long countryPopulation = dbManager.getCountryPopulation(countryName);
                    System.out.println("Population of " + countryName + ": " + countryPopulation);
                    break;
                case 4:
                    System.out.println("Enter the name of the district:");
                    String districtName = scanner.nextLine();
                    long districtPopulation = dbManager.getDistrictPopulation(districtName);
                    System.out.println("Population of " + districtName + ": " + districtPopulation);
                    break;
                case 5:
                    System.out.println("Enter the name of the city:");
                    String cityName = scanner.nextLine();
                    long cityPopulation = dbManager.getCityPopulation(cityName);
                    System.out.println("Population of " + cityName + ": " + cityPopulation);
                    break;
                case 6:
                    long worldPopulation = dbManager.getWorldPopulation();
                    System.out.println("Population of the world: " + worldPopulation);
                    break;
                case 7:
                    shouldContinue = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        // Disconnect from the database
        dbManager.disconnect();
    }
}