package com.abhishek.payroll.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract base class — mirrors your original OOP design.
 * JPA JOINED inheritance: each subclass gets its own table,
 * linked by foreign key to the parent `employees` table.
 */
@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "employee_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department;

    @Column(name = "employee_type", insertable = false, updatable = false)
    private String employeeType;

    protected Employee(String name, String department) {
        this.name       = name;
        this.department = department;
    }

    /**
     * Abstract salary method — each subclass implements its own logic.
     * Mirrors the original abstract calculateSalary() exactly.
     */
    public abstract double calculateSalary();
}
