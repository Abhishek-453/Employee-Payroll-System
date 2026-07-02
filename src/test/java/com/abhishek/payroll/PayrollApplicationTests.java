package com.abhishek.payroll;

import com.abhishek.payroll.dto.FullTimeEmployeeRequest;
import com.abhishek.payroll.dto.PartTimeEmployeeRequest;
import com.abhishek.payroll.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PayrollApplicationTests {

    @Autowired MockMvc         mockMvc;
    @Autowired ObjectMapper    mapper;
    @Autowired EmployeeRepository repo;

    @BeforeEach
    void cleanUp() { repo.deleteAll(); }

    // ── POST Tests ────────────────────────────────────────────────────────────

    @Test @Order(1)
    void shouldAddFullTimeEmployee() throws Exception {
        FullTimeEmployeeRequest req = new FullTimeEmployeeRequest();
        req.setName("Abhishek Kumar");
        req.setDepartment("Engineering");
        req.setMonthlySalary(75000.0);

        mockMvc.perform(post("/api/employees/fulltime")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Abhishek Kumar"))
                .andExpect(jsonPath("$.employeeType").value("FULL_TIME"))
                .andExpect(jsonPath("$.salary").value(75000.0));
    }

    @Test @Order(2)
    void shouldAddPartTimeEmployee() throws Exception {
        PartTimeEmployeeRequest req = new PartTimeEmployeeRequest();
        req.setName("Rahul Sharma");
        req.setDepartment("Marketing");
        req.setHoursWorked(40);
        req.setHourlyRate(500.0);

        mockMvc.perform(post("/api/employees/parttime")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeType").value("PART_TIME"))
                .andExpect(jsonPath("$.salary").value(20000.0));  // 40 × 500
    }

    @Test @Order(3)
    void shouldReturn400WhenNameBlank() throws Exception {
        FullTimeEmployeeRequest req = new FullTimeEmployeeRequest();
        req.setName("");   // invalid
        req.setDepartment("Engineering");
        req.setMonthlySalary(50000.0);

        mockMvc.perform(post("/api/employees/fulltime")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details.name").exists());
    }

    // ── GET Tests ─────────────────────────────────────────────────────────────

    @Test @Order(4)
    void shouldReturn404ForNonExistentEmployee() throws Exception {
        mockMvc.perform(get("/api/employees/999"))
                .andExpect(status().isNotFound());
    }

    @Test @Order(5)
    void shouldGetAllEmployees() throws Exception {
        // Seed
        FullTimeEmployeeRequest req = new FullTimeEmployeeRequest();
        req.setName("Test User"); req.setDepartment("IT"); req.setMonthlySalary(60000.0);
        mockMvc.perform(post("/api/employees/fulltime")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    // ── DELETE Tests ──────────────────────────────────────────────────────────

    @Test @Order(6)
    void shouldDeleteEmployee() throws Exception {
        FullTimeEmployeeRequest req = new FullTimeEmployeeRequest();
        req.setName("Delete Me"); req.setDepartment("HR"); req.setMonthlySalary(40000.0);

        String body = mockMvc.perform(post("/api/employees/fulltime")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andReturn().getResponse().getContentAsString();

        Long id = mapper.readTree(body).get("id").asLong();

        mockMvc.perform(delete("/api/employees/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/employees/" + id))
                .andExpect(status().isNotFound());
    }
}
