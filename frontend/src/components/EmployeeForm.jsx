import { useState, useEffect } from 'react';

function EmployeeForm({ employee, onSubmit, onCancel }) {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        department: '',
        employeeType: 'FULL_TIME',
        salary: '',
        hoursWorked: '',
        hourlyRate: ''
    });

    useEffect(() => {
        if (employee) {
            // eslint-disable-next-line react-hooks/set-state-in-effect
            setFormData(employee);
        }
    }, [employee]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!formData.name || !formData.email || !formData.department) {
            alert('Please fill all required fields');
            return;
        }

        if (employee) {
            onSubmit(employee.id, formData);
        } else {
            onSubmit(formData);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="employee-form">
            <h3>{employee ? '✏️ Edit Employee' : '➕ Add New Employee'}</h3>

            <div className="form-grid">
                <div className="form-group">
                    <label>Full Name *</label>
                    <input
                        type="text"
                        name="name"
                        placeholder="Enter full name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Email *</label>
                    <input
                        type="email"
                        name="email"
                        placeholder="email@example.com"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Department *</label>
                    <input
                        type="text"
                        name="department"
                        placeholder="e.g., Engineering, HR"
                        value={formData.department}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-group">
                    <label>Employee Type *</label>
                    <select
                        name="employeeType"
                        value={formData.employeeType}
                        onChange={handleChange}
                    >
                        <option value="FULL_TIME">Full Time</option>
                        <option value="PART_TIME">Part Time</option>
                    </select>
                </div>

                {formData.employeeType === 'FULL_TIME' ? (
                    <div className="form-group">
                        <label>Monthly Salary (₹) *</label>
                        <input
                            type="number"
                            name="salary"
                            placeholder="50000"
                            value={formData.salary}
                            onChange={handleChange}
                            required
                        />
                    </div>
                ) : (
                    <>
                        <div className="form-group">
                            <label>Hours Worked *</label>
                            <input
                                type="number"
                                name="hoursWorked"
                                placeholder="160"
                                value={formData.hoursWorked}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Hourly Rate (₹) *</label>
                            <input
                                type="number"
                                name="hourlyRate"
                                placeholder="500"
                                value={formData.hourlyRate}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </>
                )}
            </div>

            <div className="form-actions">
                <button type="submit" className="btn-submit">
                    {employee ? '💾 Update Employee' : '➕ Add Employee'}
                </button>
                <button type="button" onClick={onCancel} className="btn-cancel">
                    ❌ Cancel
                </button>
            </div>
        </form>
    );
}

export default EmployeeForm;
