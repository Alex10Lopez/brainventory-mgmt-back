package com.brainventory_mgmt.human_resources.enums;

import lombok.Getter;

@Getter
public enum EmployeeSex {
    MALE("male"),
    FEMALE("female");

    private final String sex;

    EmployeeSex(String sex){
        this.sex = sex;
    }
}
