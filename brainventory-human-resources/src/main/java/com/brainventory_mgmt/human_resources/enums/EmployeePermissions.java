package com.brainventory_mgmt.human_resources.enums;

import lombok.Getter;

@Getter
public enum EmployeePermissions {
    GLOBAL_ADMIN("global_admin"),
    ASSETS_ADMIN("assets_admin"),
    INFRASTRUCTURE_ADMIN("infrastructure_admin"),
    HR_ADMIN("hr_admin"),
    NO_ADMIN("no_admin");

    private final String permissions;

    EmployeePermissions(String permissions){
        this.permissions = permissions;
    }
}
