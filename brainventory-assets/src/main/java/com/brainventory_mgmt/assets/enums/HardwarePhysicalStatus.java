package com.brainventory_mgmt.assets.enums;

import lombok.Getter;

@Getter
public enum HardwarePhysicalStatus {
    NEW("new"),
    LIKE_NEW("like_new"),
    USED("used"),
    REFURBISHED("refurbished"),
    DAMAGED("damaged"),
    DEFECTIVE("defective"),
    REPAIRED("repaired"),
    DECOMMISSIONED("decommissioned");

    private final String physicalStatus;

    HardwarePhysicalStatus(String physicalStatus) {
        this.physicalStatus = physicalStatus;
    }
}
