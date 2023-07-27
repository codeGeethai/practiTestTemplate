package com.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PractiTestIntegration {

    private String apiUrl;
    private String apiToken;

    public PractiTestIntegration() {
        // Load properties from the properties file
        loadProperties();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = PractiTestIntegration.class.getClassLoader().getResourceAsStream("practitest.properties")) {
            properties.load(inputStream);
            apiUrl = properties.getProperty("practitest.api.url");
            apiToken = properties.getProperty("practitest.api.token");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file.", e);
        }
    }

    public void createTestRun(String testName, String status) {
        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Basic " + apiToken)
                .contentType(ContentType.JSON);

        String requestBody = "{\n" +
                "    \"data\": {\n" +
                "        \"type\": \"instances\",\n" +
                "        \"attributes\": {\n" +
                "            \"name\": \"" + testName + "\",\n" +
                "            \"status\": \"" + status + "\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        Response response = request.body(requestBody).post(apiUrl);

        if (response.getStatusCode() == 201) {
            System.out.println("Test run created successfully.");
        } else {
            System.err.println("Failed to create test run. Status code: " + response.getStatusCode());
            System.err.println("Response: " + response.getBody().asString());
        }
    }

    // You can add more methods for updating test runs, fetching test data, etc.
}
