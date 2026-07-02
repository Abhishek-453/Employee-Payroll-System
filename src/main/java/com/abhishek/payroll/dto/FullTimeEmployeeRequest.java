package com.abhishek.payroll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * DTO for creating a FullTimeEmployee.
 * Keeps the API contract separate from the DB entity.
 */
@Data
public class FullTimeEmployeeRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Monthly salary is required")
    @Positive(message = "Monthly salary must be positive")
    private Double monthlySalary;
}
