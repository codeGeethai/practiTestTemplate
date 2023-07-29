package com.example.practitest.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TestInstance {

    private String id;
    private String name;
    private String status;
    private Integer testId;
    private String assignedTo;
    private Integer buildId;
    private Integer runId;
    private Integer testSetId;
    private Integer parentInstanceId;
    private Integer requirementId;
    private String startDate;
    private String endDate;
    private Integer executionOrder;
    private String comments;
    private List<String> attachments;
    private List<String> customFields;
    // Add more attributes as needed for TestInstance

    // No need to define getters, setters, equals, hashCode, or toString
}
