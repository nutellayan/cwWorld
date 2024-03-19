package com.napier.sem;


public class PopulationData {
    private String name; // Name of the continent/region/country
    private final long totalPopulation;
    private final long populationInCities;
    private final double percentageInCities;
    private final long populationNotInCities;
    private final double percentageNotInCities;

    public PopulationData(String name, long totalPopulation, long populationInCities,
                          double percentageInCities, long populationNotInCities, double percentageNotInCities) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.populationInCities = populationInCities;
        this.percentageInCities = percentageInCities;
        this.populationNotInCities = populationNotInCities;
        this.percentageNotInCities = percentageNotInCities;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public long getPopulationInCities() {
        return populationInCities;
    }

    public double getPercentageInCities() {
        return percentageInCities;
    }

    public long getPopulationNotInCities() {
        return populationNotInCities;
    }

    public double getPercentageNotInCities() {
        return percentageNotInCities;
    }
}