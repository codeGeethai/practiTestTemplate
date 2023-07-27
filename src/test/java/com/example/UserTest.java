package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testUserAttributes() {
        User user = new User("John", "Doe", "john.doe@example.com");

        Assertions.assertEquals("John", user.getFirstName());
        Assertions.assertEquals("Doe", user.getLastName());
        Assertions.assertEquals("john.doe@example.com", user.getEmail());

        // Integrate with PractiTest to create test run and update its status
        PractiTestIntegration practiTestIntegration = new PractiTestIntegration();
        practiTestIntegration.createTestRun("testUserAttributes", "Passed");
    }
}
