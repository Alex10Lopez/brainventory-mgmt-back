package com.brainventory_mgmt.assets.enums;

import lombok.Getter;

@Getter
public enum HardwareOperationalStatus {
    OPERATIONAL("operational"),
    INOPERATIVE("inoperative"),
    MAINTENANCE("maintenance"),
    UNDER_REPAIR("under_repair"),
    DEGRADED("degraded"),
    OBSOLETE("obsolete"),
    RETIRED("retired");

    private final String operationalStatus;

    HardwareOperationalStatus(String operationalStatus) {
        this.operationalStatus = operationalStatus;
    }
}
