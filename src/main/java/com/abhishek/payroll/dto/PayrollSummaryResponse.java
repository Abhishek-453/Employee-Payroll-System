package com.abhishek.payroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Summary DTO returned by GET /api/employees/summary
 */
@Data
@AllArgsConstructor
public class PayrollSummaryResponse {
    private int    totalEmployees;
    private int    fullTimeCount;
    private int    partTimeCount;
    private double totalPayroll;
    private double averageSalary;
}
