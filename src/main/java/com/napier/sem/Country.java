package com.napier.sem;

/**
 * Represents a country
 */
public class Country {
    /**
     * Country code
     */
    public String code;

    /**
     * Country name
     */
    public String name;

    /**
     * Continent where the country is located
     */
    public String continent;

    /**
     * Region where the country is located
     */
    public String region;

    /**
     * Population of the country
     */
    public long population;

    /**
     * Capital city of the country
     */
    public String capital;

    // Constructor
    public Country(String code, String name, String continent, String region, int population, String capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }


}
