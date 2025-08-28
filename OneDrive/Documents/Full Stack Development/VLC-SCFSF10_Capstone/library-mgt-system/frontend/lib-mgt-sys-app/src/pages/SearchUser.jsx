import React, { useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";

export function SearchUser() {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const initialQuery = queryParams.get("query") || "";
    const [searchTerm, setSearchTerm] = useState(initialQuery);
    const [users, setUsers] = useState([]);
    const [selectedUserId, setSelectedUserId] = useState(null);
    const [loading, setLoading] = useState(false);
    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();
    const handleNavigation = (path) => {
    navigate(path);
    };


  // Handle search
  const handleSearch = async (e) => {
    if (e) e.preventDefault();
    if (!searchTerm.trim()) {
      setUsers([]);
      return;
    }

    setLoading(true);
    try {
      const response = await axios.get(
        `http://localhost:8080/api/users/search?query=${encodeURIComponent(searchTerm)}`
      );
      setUsers(response.data);
      setErrorMessage("");
    } catch (error) {
      console.error("Error searching users:", error);
      setErrorMessage("Error fetching users.");
    } finally {
      setLoading(false);
    }
  };

  // Handle view all
  const handleViewAll = async () => {
    setLoading(true);
    try {
      const response = await axios.get("http://localhost:8080/api/users/all");
      setUsers(response.data);
      setErrorMessage("");
    } catch (error) {
      console.error("Error fetching all users:", error);
      setErrorMessage("Error fetching all users.");
    } finally {
      setLoading(false);
    }
  };

  // Handle delete
  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this user?")) return;
    try {
      await axios.delete(`http://localhost:8080/api/users/delete/${id}`);
      setSuccessMessage("User deleted successfully!");
      setUsers(users.filter((u) => u.id !== id));
      if (selectedUserId === id) setSelectedUserId(null);
    } catch (err) {
      console.error("Error deleting user:", err);
      setErrorMessage("Error deleting user.");
    }
  };

  return (
    <div className="create-user-container">
      <h2>Search User</h2>

      {/* Search Box */}
      <form onSubmit={handleSearch} className="search-box">
        <input
          type="text"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          placeholder="Enter username or email"
          className="search-input"
        />
        <button type="submit" className="search-button">Search</button>
        <button type="button" className="search-button" onClick={handleViewAll}>
          View All Users
        </button>
      </form>

      {loading && <p>Loading...</p>}
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      {successMessage && <p className="success-message">{successMessage}</p>}

    {/* Users list: hidden when updating a single user */}
      {!selectedUserId && (
        <>
          {users && users.length > 0 ? (
            <ul className="results-list">
              {users.map((user) => (                
                  <li key={user.id} className="result-item">
                    <div>
                        <strong>User ID:</strong> {user.id} <br />
                        <strong>Username:</strong> {user.username} <br />
                        <strong>Email:</strong> {user.email} <br />
                        <strong>Contact No:</strong> {user.contactNo} <br />
                        <strong>Address:</strong> {user ? user.address : "N/A"} <br />
                        <strong>Role:</strong> {user.role ? user.role.roleType : "N/A"} <br />
                        <strong>Membership Start Date:</strong> {user.membershipStartDate} <br />
                        <strong>Membership End Date:</strong> {user.membershipEndDate} <br />
                    </div>
                    <div style={{ display: "flex", gap: "0.5rem" }}>
                      <button
                        className="action-button"
                        onClick={() => handleNavigation('/users/update')}
                      >
                        Update
                      </button>
                      <button
                        className="action-button"
                        style={{ backgroundColor: "red" }}
                        onClick={() => handleDelete(user.id)}
                      >
                        Delete
                      </button>
                    </div>
                  </li>
                ))}
            </ul>
          ) : (
            <p className="error-message">No users found.</p>
          )}
        </>
      )}
 
    </div>
  );
}
