package com.example.practitest.dataobjects;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Status {

    public static final String ACTIVE = "active";
    public static final String FAILED = "failed";
    public static final String BLOCKED = "blocked";
    public static final String NA = "na";
    public static final String NOT_COMPLETED = "not_completed";
    public static final String PASSED = "passed";
}