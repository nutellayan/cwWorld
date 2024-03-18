package com.napier.sem;

public class City {
    private String name;
    private String countryName;
    private String district;
    private String continent; // Add continent field
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

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
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