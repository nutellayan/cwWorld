package com.napier.sem;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // Create new DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();

        // Connect to the database
        dbManager.connect();

        // Retrieve and print all capital cities
        ArrayList<PopulationData> populationReportDataByContinent = dbManager.getPopulationReportDataByContinent();
        printPopulationData(populationReportDataByContinent, "Population Report - People Living In/Not Living in Each Continent");

        // Retrieve and print capital cities by continent and population
        ArrayList<PopulationData> populationReportDataByRegion = dbManager.getPopulationReportDataByRegion();
        printPopulationData(populationReportDataByRegion, "Population Report - People Living In/Not Living in Each Region");

        // Retrieve and print capital cities by region and population
        ArrayList<PopulationData> populationReportDataByCountry = dbManager.getPopulationReportDataByCountry();
        printPopulationData(populationReportDataByCountry, "Population Report - People Living In/Not Living in Each Country");

        // Disconnect from the database
        dbManager.disconnect();
    }

        public static void printPopulationData(ArrayList<PopulationData> populationData, String header) {
            if (populationData == null) {
                System.out.println("No Data");
                return;
            }

            if (header == null) {
                System.out.println("Invalid header");
                return;
            }
        System.out.println("===========================");
        System.out.println(header);
        System.out.println("===========================");
        System.out.printf("%-40s %-25s %-25s %-25s %-25s %-20s%n",
                "Name", "Total Population", "Population In Cities", "Percentage In Cities", "Population Not In Cities", "Percentage Not In Cities");
        for (PopulationData data : populationData) {
            if (data == null)
                continue;
            String populationInfo = String.format("%-40s %-25d %-25d %-25f %-25d %-20.2f%n",
                    data.getName(), data.getTotalPopulation(),
                    data.getPopulationInCities(), data.getPercentageInCities(), data.getPopulationNotInCities(), data.getPercentageNotInCities());

            System.out.println(populationInfo);
        }
    }

}
