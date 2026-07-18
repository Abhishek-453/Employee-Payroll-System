import { useNavigate } from 'react-router-dom';
import './Navbar.css';

const Navbar = ({ user, onLogout }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    onLogout();
    navigate('/login', { replace: true });
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <div className="navbar-brand">
          <h1>🏢 Employee Payroll System</h1>
        </div>

        <div className="navbar-right">
          {user && (
            <div className="user-info">
              <span className="welcome-text">👤 Welcome, <strong>{user.username}</strong></span>
            </div>
          )}

          <button className="logout-button" onClick={handleLogout}>
            🚪 Logout
          </button>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;