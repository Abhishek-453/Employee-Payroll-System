package com.abhishek.payroll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Part-time employee: earns hourlyRate × hoursWorked.
 * Maps to `part_time_employees` table (JOINED inheritance).
 */
@Entity
@Table(name = "part_time_employees")
@DiscriminatorValue("PART_TIME")
@Getter
@Setter
@NoArgsConstructor
public class PartTimeEmployee extends Employee {

    @Positive(message = "Hours worked must be positive")
    @Column(name = "hours_worked", nullable = false)
    private int hoursWorked;

    @Positive(message = "Hourly rate must be positive")
    @Column(name = "hourly_rate", nullable = false)
    private double hourlyRate;

    public PartTimeEmployee(String name, String department,
                            int hoursWorked, double hourlyRate) {
        super(name, department);
        this.hoursWorked = hoursWorked;
        this.hourlyRate  = hourlyRate;
    }

    /**
     * Part-time salary = hoursWorked × hourlyRate.
     */
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}
