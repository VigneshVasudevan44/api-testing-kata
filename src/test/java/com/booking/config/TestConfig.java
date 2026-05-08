package com.booking.config;
import io.restassured.RestAssured;

public class TestConfig {

    /**
     * Centralized Rest-Assured Configuration
     * Used Across All test scenarios
     */
    public static void setup() {

        // Base URI for booking application APIs
        RestAssured.baseURI = "https://automationintesting.online/api";

        // Logs request and response details automatically on failures
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}