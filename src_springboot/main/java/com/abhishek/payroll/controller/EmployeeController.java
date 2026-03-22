package com.abhishek.payroll.controller;

import com.abhishek.payroll.dto.*;
import com.abhishek.payroll.service.PayrollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller — exposes all payroll endpoints under /api/employees
 *
 * Endpoints:
 *   GET    /api/employees              → list all employees
 *   GET    /api/employees/{id}         → get by ID
 *   GET    /api/employees/fulltime      → list full-time employees
 *   GET    /api/employees/parttime      → list part-time employees
 *   GET    /api/employees/department?name=Engineering
 *   GET    /api/employees/search?name=Abhishek
 *   GET    /api/employees/summary      → payroll summary stats
 *   POST   /api/employees/fulltime     → add full-time employee
 *   POST   /api/employees/parttime     → add part-time employee
 *   PUT    /api/employees/{id}/fulltime → update full-time employee
 *   PUT    /api/employees/{id}/parttime → update part-time employee
 *   DELETE /api/employees/{id}         → delete employee
 */
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final PayrollService service;

    // ── GET All ───────────────────────────────────────────────────────────────

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/fulltime")
    public ResponseEntity<List<EmployeeResponse>> getFullTime() {
        return ResponseEntity.ok(service.getFullTimeEmployees());
    }

    @GetMapping("/parttime")
    public ResponseEntity<List<EmployeeResponse>> getPartTime() {
        return ResponseEntity.ok(service.getPartTimeEmployees());
    }

    @GetMapping("/department")
    public ResponseEntity<List<EmployeeResponse>> getByDepartment(
            @RequestParam String name) {
        return ResponseEntity.ok(service.getByDepartment(name));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponse>> searchByName(
            @RequestParam String name) {
        return ResponseEntity.ok(service.searchByName(name));
    }

    @GetMapping("/summary")
    public ResponseEntity<PayrollSummaryResponse> getSummary() {
        return ResponseEntity.ok(service.getPayrollSummary());
    }

    // ── POST (Create) ─────────────────────────────────────────────────────────

    @PostMapping("/fulltime")
    public ResponseEntity<EmployeeResponse> addFullTime(
            @Valid @RequestBody FullTimeEmployeeRequest req) {
        EmployeeResponse created = service.addFullTimeEmployee(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/parttime")
    public ResponseEntity<EmployeeResponse> addPartTime(
            @Valid @RequestBody PartTimeEmployeeRequest req) {
        EmployeeResponse created = service.addPartTimeEmployee(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── PUT (Update) ──────────────────────────────────────────────────────────

    @PutMapping("/{id}/fulltime")
    public ResponseEntity<EmployeeResponse> updateFullTime(
            @PathVariable Long id,
            @Valid @RequestBody FullTimeEmployeeRequest req) {
        return ResponseEntity.ok(service.updateFullTimeEmployee(id, req));
    }

    @PutMapping("/{id}/parttime")
    public ResponseEntity<EmployeeResponse> updatePartTime(
            @PathVariable Long id,
            @Valid @RequestBody PartTimeEmployeeRequest req) {
        return ResponseEntity.ok(service.updatePartTimeEmployee(id, req));
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();   // 204 No Content
    }
}
