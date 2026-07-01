import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/employees';

class EmployeeService {
    getAllEmployees() {
        return axios.get(API_BASE_URL);
    }

    getEmployeeById(id) {
        return axios.get(`${API_BASE_URL}/${id}`);
    }

    addEmployee(employee) {
        // Backend expects different format for Full Time vs Part Time
        if (employee.employeeType === 'FULL_TIME') {
            // POST to /api/employees/fulltime
            return axios.post(`${API_BASE_URL}/fulltime`, {
                name: employee.name,
                email: employee.email,
                department: employee.department,
                monthlySalary: parseFloat(employee.salary)
            });
        } else {
            // POST to /api/employees/parttime
            return axios.post(`${API_BASE_URL}/parttime`, {
                name: employee.name,
                email: employee.email,
                department: employee.department,
                hoursWorked: parseInt(employee.hoursWorked),
                hourlyRate: parseFloat(employee.hourlyRate)
            });
        }
    }

    updateEmployee(id, employee) {
        if (employee.employeeType === 'FULL_TIME') {
            return axios.put(`${API_BASE_URL}/${id}/fulltime`, {
                name: employee.name,
                email: employee.email,
                department: employee.department,
                monthlySalary: parseFloat(employee.salary)
            });
        } else {
            return axios.put(`${API_BASE_URL}/${id}/parttime`, {
                name: employee.name,
                email: employee.email,
                department: employee.department,
                hoursWorked: parseInt(employee.hoursWorked),
                hourlyRate: parseFloat(employee.hourlyRate)
            });
        }
    }

    deleteEmployee(id) {
        return axios.delete(`${API_BASE_URL}/${id}`);
    }

    searchEmployees(keyword) {
        return axios.get(`${API_BASE_URL}/search?name=${keyword}`);
    }
}

export default new EmployeeService();