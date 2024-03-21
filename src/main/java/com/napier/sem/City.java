package com.napier.sem;

public class City {
    private String name;
    private final String countryName;
    private final String district;
    private final String continent; // Add continent field
    private int population;

    public City(String name, String countryName, String district, String continent, int population) {
        this.name = name;
        this.countryName = countryName;
        this.district = district;
        this.continent = continent; // Initialize continent field
        this.population = population;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", countryName='" + countryName + '\'' +
                ", district='" + district + '\'' +
                ", continent='" + continent + '\'' +
                ", population=" + population +
                '}';
    }
}