import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export function LoginPage({onLogin}) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate(); 
  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    
    try {
      // Call your backend login API
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        username,
        password
        });
        const data = response.data;
        
        // const userRole = data.role?.roleType || data.roles?.[0]?.roleType;

        // Call App.jsx onLogin to set currentUser
        onLogin(data.user);

        // Redirect based on role
        if (data.role === 'Librarian') {
          navigate('/admin'); // <-- go to LibrarianAdminPage
        } else {
          navigate('/'); // <-- go to HomePage for normal users
        }
      
    } catch (error) {
        console.error("Login error:", error);
        if (error.response && error.response.data) {
        setError(error.response.data.message || 'Login failed');
      } else {
        setError('Login failed. Please try again.');
      }
    }
    };

  return (
    <div className="login-page-container">
      <h2>Login</h2>
      {/* Reuse form-wrapper for styling */}
      <div className="form-wrapper">
      <form onSubmit={handleSubmit} className="login-form">
        <input
          type="username"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit" className="login-button">Login</button>
      </form>
      {error && <p className="login-error">{error}</p>}
      </div>
    </div>
  );
};
