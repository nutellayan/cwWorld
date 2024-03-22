package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect(); // Ensure connection is established before tests
    }

    @Test
    void printLanguageStatisticsTest() {
        app.printLanguageStatistics();
        // Check if the method executes without throwing an exception
    }

    @Test
    void printLanguageStatisticsTestEmpty() {
        // Since there's no specific input data, test what happens when the result set is empty
        // Disconnecting and reconnecting to reset the state
        app.disconnect();
        app.connect();
        app.printLanguageStatistics();
    }

    @Test
    void printLanguageStatisticsTestContainsNull() {
        // Test what happens when the result set contains null values
        // Disconnecting and reconnecting to reset the state
        app.disconnect();
        app.connect();
        // Call printLanguageStatistics with a null ResultSet
    }
}
