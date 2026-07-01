import { useState, useEffect } from 'react';
import EmployeeService from './services/EmployeeService';
import EmployeeList from './components/EmployeeList';
import EmployeeForm from './components/EmployeeForm';
import './App.css';

function App() {
    const [employees, setEmployees] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [editingEmployee, setEditingEmployee] = useState(null);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        // eslint-disable-next-line react-hooks/immutability
        loadEmployees();
    }, []);

    const loadEmployees = async () => {
        setLoading(true);
        try {
            const response = await EmployeeService.getAllEmployees();
            setEmployees(response.data);
        } catch (error) {
            console.error('Error loading employees:', error);
            alert('Failed to load employees. Make sure backend is running on port 8080');
        } finally {
            setLoading(false);
        }
    };

    const handleAddEmployee = async (employeeData) => {
        try {
            await EmployeeService.addEmployee(employeeData);
            loadEmployees();
            setShowForm(false);
            alert('Employee added successfully!');
        } catch (error) {
            console.error('Error adding employee:', error);
            alert('Failed to add employee');
        }
    };

    const handleUpdateEmployee = async (id, employeeData) => {
        try {
            await EmployeeService.updateEmployee(id, employeeData);
            loadEmployees();
            setEditingEmployee(null);
            setShowForm(false);
            alert('Employee updated successfully!');
        } catch (error) {
            console.error('Error updating employee:', error);
            alert('Failed to update employee');
        }
    };

    const handleDeleteEmployee = async (id) => {
        if (window.confirm('Are you sure you want to delete this employee?')) {
            try {
                await EmployeeService.deleteEmployee(id);
                loadEmployees();
                alert('Employee deleted successfully!');
            } catch (error) {
                console.error('Error deleting employee:', error);
                alert('Failed to delete employee');
            }
        }
    };

    const handleEdit = (employee) => {
        setEditingEmployee(employee);
        setShowForm(true);
    };

    return (
        <div className="app-container">
            <header className="app-header">
                <h1>🏢 Employee Payroll Management System</h1>
                <button 
                    onClick={() => {
                        setShowForm(!showForm);
                        setEditingEmployee(null);
                    }} 
                    className="btn-primary"
                >
                    {showForm ? '❌ Cancel' : '➕ Add New Employee'}
                </button>
            </header>

            {showForm && (
                <EmployeeForm
                    employee={editingEmployee}
                    onSubmit={editingEmployee ? handleUpdateEmployee : handleAddEmployee}
                    onCancel={() => {
                        setShowForm(false);
                        setEditingEmployee(null);
                    }}
                />
            )}

            {loading ? (
                <div className="loading">Loading employees...</div>
            ) : (
                <EmployeeList
                    employees={employees}
                    onEdit={handleEdit}
                    onDelete={handleDeleteEmployee}
                />
            )}
        </div>
    );
}

export default App;