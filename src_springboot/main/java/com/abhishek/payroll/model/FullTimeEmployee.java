package com.abhishek.payroll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Full-time employee: earns a fixed monthly salary.
 * Maps to `full_time_employees` table (JOINED inheritance).
 */
@Entity
@Table(name = "full_time_employees")
@DiscriminatorValue("FULL_TIME")
@Getter
@Setter
@NoArgsConstructor
public class FullTimeEmployee extends Employee {

    @Positive(message = "Monthly salary must be positive")
    @Column(name = "monthly_salary", nullable = false)
    private double monthlySalary;

    public FullTimeEmployee(String name, String department, double monthlySalary) {
        super(name, department);
        this.monthlySalary = monthlySalary;
    }

    /**
     * Full-time salary = fixed monthly salary.
     */
    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}
