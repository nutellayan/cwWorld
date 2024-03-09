package com.napier.sem;

import java.util.List;

/**
 * Represents a continent
 */
public class Continent {
    /**
     * Continent name
     */
    public String name;

    /**
     * Regions within the continent
     */
    public List<Region> regions;

    // Constructor
    public Continent(String name, List<Region> regions) {
        this.name = name;
        this.regions = regions;
    }
}
