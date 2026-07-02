package com.abhishek.payroll.dto;

import com.abhishek.payroll.model.Employee;
import com.abhishek.payroll.model.FullTimeEmployee;
import com.abhishek.payroll.model.PartTimeEmployee;
import lombok.Data;

/**
 * Unified response DTO — both FullTime and PartTime employees
 * are returned in this consistent shape.
 */
@Data
public class EmployeeResponse {

    private Long   id;
    private String name;
    private String department;
    private String employeeType;
    private double salary;

    // Full-time specific
    private Double monthlySalary;

    // Part-time specific
    private Integer hoursWorked;
    private Double  hourlyRate;

    /**
     * Static factory — converts any Employee subclass to a response DTO.
     * Uses polymorphism: instanceof check to fill type-specific fields.
     */
    public static EmployeeResponse from(Employee emp) {
        EmployeeResponse res = new EmployeeResponse();
        res.setId(emp.getId());
        res.setName(emp.getName());
        res.setDepartment(emp.getDepartment());
        res.setSalary(emp.calculateSalary());   // polymorphic call

        if (emp instanceof FullTimeEmployee fte) {
            res.setEmployeeType("FULL_TIME");
            res.setMonthlySalary(fte.getMonthlySalary());

        } else if (emp instanceof PartTimeEmployee pte) {
            res.setEmployeeType("PART_TIME");
            res.setHoursWorked(pte.getHoursWorked());
            res.setHourlyRate(pte.getHourlyRate());
        }

        return res;
    }
}
