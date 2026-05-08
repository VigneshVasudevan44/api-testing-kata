package com.booking.hooks;
import com.booking.config.TestConfig;
import io.cucumber.java.Before;

public class Hooks {

    /**
     * Cucumber hooks executed before test scenarios.
     */
    @Before
    public void setup() {

        // Initialize framework configuration before each scenario
        TestConfig.setup();
    }
}