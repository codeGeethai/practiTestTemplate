package com.example.practitest.dataobjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TestSet {

    private String name;
    private String status;
    private String description;
    private List<Integer> testInstanceIds;
    private String assignedTo;
    private List<Integer> testCaseIds;
    private Integer parentTestSetId;
    private List<Integer> requirementIds;
    private String startDate;
    private String endDate;
    private List<String> tags;
    private List<String> customFields;
    // Add more attributes as needed for TestSet

    // No need to define getters, setters, equals, hashCode, or toString
}
