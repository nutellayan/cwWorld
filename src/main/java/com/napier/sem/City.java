package com.napier.sem;

/**
 * Represents a city
 */
public class City {
    /**
     * City ID
     */
    public int id;

    /**
     * City name
     */
    public String name;

    /**
     * Country code
     */
    public String CountryCode;

    /**
     * District where the city is located
     */
    public String district;

    /**
     * Population of the city
     */
    public int population;

    // Constructor
    public City(int id, String name, String code, String district, int population) {
        this.id = id;
        this.name = name;
        this.CountryCode = code;
        this.district = district;
        this.population = population;
    }
}
