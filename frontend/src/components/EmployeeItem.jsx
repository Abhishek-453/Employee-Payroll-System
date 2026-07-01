function EmployeeItem({ employee, onEdit, onDelete }) {
    return (
        <tr>
            <td>{employee.id}</td>
            <td>{employee.name}</td>
            <td>{employee.email}</td>
            <td>{employee.department}</td>
            <td>
                <span className={`badge ${employee.employeeType === 'FULL_TIME' ? 'badge-full' : 'badge-part'}`}>
                    {employee.employeeType === 'FULL_TIME' ? 'Full Time' : 'Part Time'}
                </span>
            </td>
            <td>₹{employee.salary?.toLocaleString() || 'N/A'}</td>
            <td className="actions">
                <button onClick={() => onEdit(employee)} className="btn-edit">
                    ✏️ Edit
                </button>
                <button onClick={() => onDelete(employee.id)} className="btn-delete">
                    🗑️ Delete
                </button>
            </td>
        </tr>
    );
}

export default EmployeeItem;
