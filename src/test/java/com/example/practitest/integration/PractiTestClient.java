package com.example.practitest.integration;

import com.example.practitest.dataobjects.TestInstance;
import com.example.practitest.dataobjects.TestRun;
import com.example.practitest.dataobjects.TestSet;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


public class PractiTestClient {

    private String apiUrl;
    private String apiToken;
    private static PractiTestClient instance;

    private List<TestInstance> testInstances;
    private List<TestSet> testSets;

    public PractiTestClient() {
        // Load properties from the properties file
        loadProperties();
    }

    public static PractiTestClient getInstance() {
        if (instance == null) {
            instance = new PractiTestClient();
        }
        return instance;
    }


    private void loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = PractiTestClient.class.getClassLoader().getResourceAsStream("practitest.properties")) {
            properties.load(inputStream);
            apiUrl = properties.getProperty("practitest.api.url");
            apiToken = properties.getProperty("practitest.api.token");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file.", e);
        }
    }

    public void createTestSet(TestSet testSet) {
        // Implement the logic to create a test set in PractiTest using the provided details
        try {
            RequestSpecification request = RestAssured.given()
                    .header("Authorization", "Basic " + apiToken)
                    .contentType(ContentType.JSON);
            //URL url = new URL(apiUrl + "/api/v2/projects/<project_id>/sets");
            //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //conn.setRequestMethod("POST");
            //conn.setRequestProperty("Authorization", "Bearer " + apiToken);
            //conn.setRequestProperty("Content-Type", "application/json");
            //conn.setDoOutput(true);

            // Convert the TestSet object to JSON
            String jsonInputString = convertTestSetToJson(testSet);

            Response response = request.body(jsonInputString).post(apiUrl+ "/api/v2/projects/<project_id>/sets");

            if (response.getStatusCode() == 201) {
                System.out.println("Test instance created successfully.");
            } else {
                System.err.println("Failed to create test instance. Status code: " + response.getStatusCode());
                System.err.println("Response: " + response.getBody().asString());
            }

            // Add the created TestSet to the list
            testSets.add(testSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTestInstance(TestInstance testInstance) {
        // Implement the logic to create a test set in PractiTest using the provided details
        try {
            RequestSpecification request = RestAssured.given()
                    .header("Authorization", "Basic " + apiToken)
                    .contentType(ContentType.JSON);
            // Convert the TestSet object to JSON
            String jsonInputString = convertTestInstanceToJson(testInstance);

            Response response = request.body(jsonInputString).post(apiUrl+"/api/v2/projects/<project_id>/instances");

            if (response.getStatusCode() == 201) {
                System.out.println("Test instance created successfully.");
            } else {
                System.err.println("Failed to create test instance. Status code: " + response.getStatusCode());
                System.err.println("Response: " + response.getBody().asString());
            }

            // Add the created TestSet to the list
            testInstances.add(testInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* public void createTestSet(TestSet testSet) {
        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Basic " + apiToken)
                .contentType(ContentType.JSON);

        String requestBody = "{\n" +
                "    \"name\": \"" + testSet.getName() + "\",\n" +
                "    \"status\": \"" + testSet.getStatus() + "\",\n" +
                "    \"description\": \"" + testSet.getDescription() + "\",\n" +
                "    \"test_instance_ids\": " + testSet.getTestInstanceIds() + ",\n" +
                "    \"assigned_to\": \"" + testSet.getAssignedTo() + "\",\n" +
                "    \"test_case_ids\": " + testSet.getTestCaseIds() + ",\n" +
                "    \"parent_testset_id\": " + testSet.getParentTestSetId() + ",\n" +
                "    \"requirement_ids\": " + testSet.getRequirementIds() + ",\n" +
                "    \"start_date\": \"" + testSet.getStartDate() + "\",\n" +
                "    \"end_date\": \"" + testSet.getEndDate() + "\",\n" +
                "    \"tags\": " + formatTags(testSet.getTags()) + ",\n" +
                "    \"custom_fields\": " + formatCustomFields(testSet.getCustomFields()) + "\n" +
                "}";

        Response response = request.body(requestBody).post(apiUrl);

        if (response.getStatusCode() == 201) {
            System.out.println("Test set created successfully.");
        } else {
            System.err.println("Failed to create test set. Status code: " + response.getStatusCode());
            System.err.println("Response: " + response.getBody().asString());
        }
    }*/

    // Helper method for converting TestSet to JSON string using Gson
    private String convertTestSetToJson(TestSet testSet) {
        Gson gson = new Gson();
        return gson.toJson(testSet);
    }

    // Helper methods for converting TestInstance and TestSet to JSON strings
    private String convertTestInstanceToJson(TestInstance testInstance) {
        Gson gson = new Gson();
        return gson.toJson(testInstance);
    }
    /*public void createTestInstance(TestInstance testInstance) {
        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Basic " + apiToken)
                .contentType(ContentType.JSON);

        String requestBody = "{\n" +
                "    \"name\": \"" + testInstance.getName() + "\",\n" +
                "    \"status\": \"" + testInstance.getStatus() + "\",\n" +
                "    \"test_id\": " + testInstance.getTestId() + ",\n" +
                "    \"assigned_to\": \"" + testInstance.getAssignedTo() + "\",\n" +
                "    \"build_id\": " + testInstance.getBuildId() + ",\n" +
                "    \"run_id\": " + testInstance.getRunId() + ",\n" +
                "    \"test_set_id\": " + testInstance.getTestSetId() + ",\n" +
                "    \"parent_instance_id\": " + testInstance.getParentInstanceId() + ",\n" +
                "    \"requirement_id\": " + testInstance.getRequirementId() + ",\n" +
                "    \"start_date\": \"" + testInstance.getStartDate() + "\",\n" +
                "    \"end_date\": \"" + testInstance.getEndDate() + "\",\n" +
                "    \"execution_order\": " + testInstance.getExecutionOrder() + ",\n" +
                "    \"comments\": \"" + testInstance.getComments() + "\",\n" +
                "    \"attachments\": " + formatAttachments(testInstance.getAttachments()) + ",\n" +
                "    \"custom_fields\": " + formatCustomFields(testInstance.getCustomFields()) + "\n" +
                "}";

        Response response = request.body(requestBody).post(apiUrl);

        if (response.getStatusCode() == 201) {
            System.out.println("Test instance created successfully.");
        } else {
            System.err.println("Failed to create test instance. Status code: " + response.getStatusCode());
            System.err.println("Response: " + response.getBody().asString());
        }
    }*/


    public void createTestRun(TestRun testRun) {
        RequestSpecification request = RestAssured.given()
                .header("Authorization", "Basic " + apiToken)
                .contentType(ContentType.JSON);

        String requestBody = "{\n" +
                "    \"name\": \"" + testRun.getName() + "\",\n" +
                "    \"status\": \"" + testRun.getStatus() + "\",\n" +
                "    \"test_id\": " + testRun.getTestId() + ",\n" +
                "    \"assigned_to\": \"" + testRun.getAssignedTo() + "\",\n" +
                "    \"test_instance_id\": " + testRun.getTestInstanceId() + ",\n" +
                "    \"start_date\": \"" + testRun.getStartDate() + "\",\n" +
                "    \"end_date\": \"" + testRun.getEndDate() + "\",\n" +
                "    \"run_by\": \"" + testRun.getRunBy() + "\",\n" +
                "    \"run_duration\": \"" + testRun.getRunDuration() + "\",\n" +
                "    \"build_id\": " + testRun.getBuildId() + ",\n" +
                "    \"parent_run_id\": " + testRun.getParentRunId() + ",\n" +
                "    \"tags\": " + formatTags(testRun.getTags()) + ",\n" +
                "    \"custom_fields\": " + formatCustomFields(testRun.getCustomFields()) + "\n" +
                "}";

        Response response = request.body(requestBody).post(apiUrl);

        if (response.getStatusCode() == 201) {
            System.out.println("Test run created successfully.");
        } else {
            System.err.println("Failed to create test run. Status code: " + response.getStatusCode());
            System.err.println("Response: " + response.getBody().asString());
        }
    }

    private String formatTags(List<String> tags) {
        StringBuilder sb = new StringBuilder("[");
        for (String tag : tags) {
            sb.append("\"").append(tag).append("\",");
        }
        if (!tags.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    private String formatCustomFields(List<String> customFields) {
        StringBuilder sb = new StringBuilder("{");
        for (String customField : customFields) {
            String[] keyValue = customField.split(":");
            if (keyValue.length == 2) {
                sb.append("\"").append(keyValue[0].trim()).append("\": \"").append(keyValue[1].trim()).append("\",");
            }
        }
        if (!customFields.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("}");
        return sb.toString();
    }

    public String formatAttachments(List<String> attachments) {
        StringBuilder sb = new StringBuilder("[");
        for (String attachment : attachments) {
            sb.append("\"").append(attachment).append("\",");
        }
        if (!attachments.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }


    // You can add more methods for updating test runs, fetching test data, etc.
}
