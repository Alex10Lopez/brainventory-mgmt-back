package com.brainventory_mgmt.human_resources.dto.jobRole;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobRoleDTO {
    Long id;

    @NotBlank
    String name;

    BigDecimal salaryRangeMin;
    BigDecimal salaryRangeMax;
    String description;
}
