package com.napier.sem;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
public class AppTest {

    @Test
    void printPopulationData() {
        ArrayList<PopulationData> populationData = new ArrayList<>();
        populationData.add(null);

        // Call the method under test
        App.printPopulationData(populationData, "Test Header");
    }

    @Test
    void printPopulationDataTestNull() {
        App.printPopulationData(null, "Null Test");
    }

    @Test
    void printPopulationDataTestEmpty() {
        ArrayList<PopulationData> populationData = new ArrayList<>();
        App.printPopulationData(populationData, "Empty Test");
    }

    @Test
    void printPopulationDataTestContainsNull() {
        ArrayList<PopulationData> populationData = new ArrayList<>();
        populationData.add(null);
        App.printPopulationData(populationData, "Contains Null Test");
    }
}