package com.abhishek.payroll.service;

import com.abhishek.payroll.dto.*;
import com.abhishek.payroll.exception.EmployeeNotFoundException;
import com.abhishek.payroll.model.Employee;
import com.abhishek.payroll.model.FullTimeEmployee;
import com.abhishek.payroll.model.PartTimeEmployee;
import com.abhishek.payroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Business logic layer — equivalent to the original PayrollSystem class,
 * now backed by MySQL via JPA.
 */
@Service
@RequiredArgsConstructor
public class PayrollService {

    private final EmployeeRepository repo;

    // ── Add Employees ─────────────────────────────────────────────────────────

    @Transactional
    public EmployeeResponse addFullTimeEmployee(FullTimeEmployeeRequest req) {
        FullTimeEmployee emp = new FullTimeEmployee(
                req.getName(), req.getDepartment(), req.getMonthlySalary());
        return EmployeeResponse.from(repo.save(emp));
    }

    @Transactional
    public EmployeeResponse addPartTimeEmployee(PartTimeEmployeeRequest req) {
        PartTimeEmployee emp = new PartTimeEmployee(
                req.getName(), req.getDepartment(),
                req.getHoursWorked(), req.getHourlyRate());
        return EmployeeResponse.from(repo.save(emp));
    }

    // ── Read ──────────────────────────────────────────────────────────────────

    public List<EmployeeResponse> getAllEmployees() {
        return repo.findAll().stream()
                .map(EmployeeResponse::from)
                .toList();
    }

    public EmployeeResponse getById(Long id) {
        Employee emp = repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return EmployeeResponse.from(emp);
    }

    public List<EmployeeResponse> getByDepartment(String department) {
        return repo.findByDepartmentIgnoreCase(department).stream()
                .map(EmployeeResponse::from)
                .toList();
    }

    public List<EmployeeResponse> searchByName(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword).stream()
                .map(EmployeeResponse::from)
                .toList();
    }

    public List<EmployeeResponse> getFullTimeEmployees() {
        return repo.findAllFullTimeEmployees().stream()
                .map(EmployeeResponse::from)
                .toList();
    }

    public List<EmployeeResponse> getPartTimeEmployees() {
        return repo.findAllPartTimeEmployees().stream()
                .map(EmployeeResponse::from)
                .toList();
    }

    // ── Update ────────────────────────────────────────────────────────────────

    @Transactional
    public EmployeeResponse updateFullTimeEmployee(Long id, FullTimeEmployeeRequest req) {
        Employee emp = repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        if (!(emp instanceof FullTimeEmployee fte)) {
            throw new IllegalArgumentException(
                    "Employee ID " + id + " is not a Full-Time employee.");
        }

        fte.setName(req.getName());
        fte.setDepartment(req.getDepartment());
        fte.setMonthlySalary(req.getMonthlySalary());
        return EmployeeResponse.from(repo.save(fte));
    }

    @Transactional
    public EmployeeResponse updatePartTimeEmployee(Long id, PartTimeEmployeeRequest req) {
        Employee emp = repo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        if (!(emp instanceof PartTimeEmployee pte)) {
            throw new IllegalArgumentException(
                    "Employee ID " + id + " is not a Part-Time employee.");
        }

        pte.setName(req.getName());
        pte.setDepartment(req.getDepartment());
        pte.setHoursWorked(req.getHoursWorked());
        pte.setHourlyRate(req.getHourlyRate());
        return EmployeeResponse.from(repo.save(pte));
    }

    // ── Delete ────────────────────────────────────────────────────────────────

    @Transactional
    public void deleteEmployee(Long id) {
        if (!repo.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        repo.deleteById(id);
    }

    // ── Summary ───────────────────────────────────────────────────────────────

    public PayrollSummaryResponse getPayrollSummary() {
        List<Employee> all      = repo.findAll();
        int total               = all.size();
        int fullTimeCount       = repo.findAllFullTimeEmployees().size();
        int partTimeCount       = repo.findAllPartTimeEmployees().size();
        double totalPayroll     = all.stream().mapToDouble(Employee::calculateSalary).sum();
        double avgSalary        = total > 0 ? totalPayroll / total : 0.0;

        return new PayrollSummaryResponse(total, fullTimeCount, partTimeCount,
                totalPayroll, avgSalary);
    }
}
