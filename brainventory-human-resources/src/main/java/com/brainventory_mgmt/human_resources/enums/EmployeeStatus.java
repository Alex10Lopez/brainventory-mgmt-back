package com.brainventory_mgmt.human_resources.enums;

import lombok.Getter;

@Getter
public enum EmployeeStatus {
    ACTIVE("active"),
    ON_VACATION("on_vacation"),
    SICK_LEAVE("sick_leave"),
    PARENTAL_LEAVE("parental_leave"),
    UNPAID_LEAVE("unpaid_leave"),
    TRAINING("training"),
    TEMPORARY("temporary"),
    PROBATION("probation"),
    PENDING_ASSIGNMENT("pending_assignment"),
    CONTRACT("contract"),
    SUSPENDED("suspended"),
    DISABILITY("disability"),
    RETIRED("retired"),
    TERMINATED("terminated"),
    INTERN("intern");

    private final String status;

    EmployeeStatus(String status) {
        this.status = status;
    }
}
