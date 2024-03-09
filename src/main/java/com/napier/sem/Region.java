package com.napier.sem;

import java.util.List;

/**
 * Represents a region
 */
public class Region {
    /**
     * Region name
     */
    public String name;

    /**
     * Countries within the region
     */
    public List<Country> countries;

    // Constructor
    public Region(String name, List<Country> countries) {
        this.name = name;
        this.countries = countries;
    }
}
