package com.example;


import com.example.practitest.dataobjects.TestRun;
import com.example.practitest.integration.PractiTestClient;

public class TestClassB {

    PractiTestClient practiTestClient = new PractiTestClient();

    public static void testMethodB()
    {
        PractiTestClient practiTestClient = new PractiTestClient();
        TestRun testRunB = TestRun.builder()
                .name("Test Run B")
                .status("Failed")
                .assignedTo("Jane Smith")
                // Set other attributes
                .build();
        practiTestClient.createTestRun(testRunB);
    }



    /*public static void testMethodB() {
        // Create a TestInstance using the builder pattern
        TestInstance testInstance = TestInstance.builder()
                .name("Sample Test Instance B")
                .status("active")
                .assignedTo("Jane Smith")
                .startDate("2023-08-01")
                .endDate("2023-08-02")
                .build();

        // Call the createTestInstance method to create the TestInstance in PractiTest
        PractiTestClient practiTestClient = new PractiTestClient();
        PractiTestClient.getInstance();

        // Create a TestRun using the builder pattern
        TestRun testRun = TestRun.builder()
                .name("Test Run B")
                .status("Failed")
                .testId(testInstance.getId())
                .assignedTo("Jane Smith")
                .testInstanceId(testInstance.getId())
                .startDate("2023-08-01")
                .endDate("2023-08-02")
                .tag("Smoke")
                .tag("Sanity")
                .customField("Priority: Low")
                .customField("Environment: QA")
                .build();

        // Call the createTestRun method to create the TestRun in PractiTest
        practiTestClient.createTestRun(testRun);

        // Create a TestSet using the builder pattern
        TestSet testSet = TestSet.builder()
                .name("Test Set B")
                .status("Active")
                .description("Sample Test Set B")
                .testInstanceId(testInstance.getId())
                .assignedTo("John Smith")
                .startDate("2023-08-01")
                .endDate("2023-08-02")
                .build();

        // Call the createTestSet method to create the TestSet in PractiTest
        practiTestClient.createTestSet(testSet);
    }*/
}

