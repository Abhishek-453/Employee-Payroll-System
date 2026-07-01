import EmployeeItem from './EmployeeItem';

function EmployeeList({ employees, onEdit, onDelete }) {
    if (employees.length === 0) {
        return (
            <div className="empty-state">
                <h2>📋 No Employees Found</h2>
                <p>Click "Add New Employee" to get started!</p>
            </div>
        );
    }

    return (
        <div className="employee-list">
            <h2>👥 All Employees ({employees.length})</h2>
            <div className="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Department</th>
                            <th>Type</th>
                            <th>Salary</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {employees.map(emp => (
                            <EmployeeItem
                                key={emp.id}
                                employee={emp}
                                onEdit={onEdit}
                                onDelete={onDelete}
                            />
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default EmployeeList;
