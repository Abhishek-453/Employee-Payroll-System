package com.abhishek.payroll;

import com.abhishek.payroll.model.FullTimeEmployee;
import com.abhishek.payroll.model.PartTimeEmployee;
import com.abhishek.payroll.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Seeds the database with sample employees on first startup.
 * Skips seeding if data already exists.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository repo;

    @Override
    public void run(String... args) {
        if (repo.count() > 0) {
            log.info("Database already has data — skipping seed.");
            return;
        }

        repo.save(new FullTimeEmployee("Abhishek Kumar",  "Engineering", 75000.0));
        repo.save(new FullTimeEmployee("Priya Gupta",     "HR",          65000.0));
        repo.save(new FullTimeEmployee("Sneha Patel",     "Engineering", 80000.0));
        repo.save(new PartTimeEmployee("Rahul Sharma",    "Marketing",   40,  500.0));
        repo.save(new PartTimeEmployee("Ananya Singh",    "Support",     60,  350.0));

        log.info("✅ Seeded 5 demo employees into the database.");
    }
}
