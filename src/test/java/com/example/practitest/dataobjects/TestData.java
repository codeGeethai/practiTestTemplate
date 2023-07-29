package com.example.practitest.dataobjects;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@Getter
@Setter
@UtilityClass
public class TestData {

    private TestInstance.TestInstanceBuilder testInstanceBuilder;
    private int testId;

    public static void setTestInstance(TestInstance testInstance) {

    }
}
