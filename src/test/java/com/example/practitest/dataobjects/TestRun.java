package com.example.practitest.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TestRun {

    private String name;
    private String status;
    private Integer testId;
    private String assignedTo;
    private Integer testInstanceId;
    private String startDate;
    private String endDate;
    private String runBy;
    private String runDuration;
    private Integer buildId;
    private Integer parentRunId;
    private List<String> tags;
    private List<String> customFields;
    // Add more attributes as needed for TestRun

    // No need to define getters, setters, equals, hashCode, or toString
}
