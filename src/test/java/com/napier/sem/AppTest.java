package com.napier.sem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {
    private App app;

    @BeforeEach
    void init() {
        app = new App();
        app.connect("localhost:33060", 30000); // Ensure connection is established before tests
    }

    @Test
    void printLanguageStatisticsTest() {
        app.printLanguageStatistics();
        // Check if the method executes without throwing an exception
    }

    @Test
    void printLanguageStatisticsTestEmpty() {
        // Since there's no specific input data, test what happens when the result set is empty
        app.printLanguageStatistics();
    }

    @Test
    void printLanguageStatisticsTestContainsNull() {
        // Test what happens when the result set contains null values
        // You can add your test logic here
    }
}
