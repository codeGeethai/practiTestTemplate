package com.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class PractiTestIntegration {

    private final String API_TOKEN = "YOUR_PRACTITEST_API_TOKEN";
    private final String BASE_URL = "https://api.practitest.com/api/v2/projects/PROJECT_ID.json"; // Add .json to the URL.

    public void createTestRun(String testName, String status) {
        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Basic " + API_TOKEN)
                .contentType(ContentType.JSON); // Set the correct Content-Type header.

        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("name", testName);
        attributes.put("status", status);
        data.put("type", "instances");
        data.put("attributes", attributes);
        requestBody.put("data", data);

        Response response = request.body(requestBody).post(BASE_URL);

        if (response.getStatusCode() == 201) {
            System.out.println("Test run created successfully.");
        } else {
            System.err.println("Failed to create test run. Status code: " + response.getStatusCode());
            System.err.println("Response: " + response.getBody().asString());
        }
    }

    // You can add more methods for updating test runs, fetching test data, etc.
}
