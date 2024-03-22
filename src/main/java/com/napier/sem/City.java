package com.napier.sem;

public class City {
    private String name;
    private String countryName;
    private String district;
    private int population;

    public City(String sydney, String name, String countryName, String district, int population, String freeCity) {
        this.name = name;
        this.countryName = countryName;
        this.district = district;
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
                ", population=" + population +
                '}';
    }
}