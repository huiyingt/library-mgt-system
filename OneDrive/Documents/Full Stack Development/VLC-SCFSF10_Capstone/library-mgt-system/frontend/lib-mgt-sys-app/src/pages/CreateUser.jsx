import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

export function CreateUser () {
  const [roles, setRoles] = useState([]);
  const [form, setForm] = useState({
    username: "",
    email: "",
    password: "",
    contactNo: "",
    address: "",
    roleId: "" // selected role ID
  });
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();
  
    const handleNavigation = (path) => {
      navigate(path);
    };

  // Fetch roles on mount
  useEffect(() => {
  axios
    .get("http://localhost:8080/api/roles/all")
    .then((res) => {
      console.log("Fetched roles:", res.data); // <-- inspect roles here
      setRoles(res.data);
    })
    .catch((err) => console.error(err));
}, []);

  const handleChange = (e) => {
  const { name, value } = e.target;
  setForm((prev) => ({
    ...prev,
    [name]: name === "roleId" ? Number(value) : value,
  }));
};

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.roleId) {
      alert("Please select a role");
      return;
    }

    const payload = {
      username: form.username,
      email: form.email,
      password: form.password,
      contactNo: form.contactNo,
      address: form.address,
      roleId: Number(form.roleId),
      membershipStartDate: new Date().toISOString().split("T")[0], // YYYY-MM-DD
      membershipEndDate: new Date(Date.now() + 365*24*60*60*1000).toISOString().split("T")[0] // 1 year later
    };

    try {
      const response = await axios.post("http://localhost:8080/api/users/create", payload);
      console.log("User created:", response.data);
      setSuccessMessage(`User created successfully! ID: ${response.data.id}`); 
      setForm({
        username: "",
        email: "",
        password: "",
        contactNo: "",
        address: "",
        roleId: "",
        });
    } catch (err) {
      console.error(err);
      setErrorMessage("Error creating user");
    }
  };

  return (
    <div className="create-book-container">
      <h2>Create New User</h2>
      <p>Fill in the details below. Fields marked * are required.</p>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username*: </label>
          <input
            type="text"
            name="username"
            value={form.username}
            onChange={handleChange}
            required
          />
        </div><br/>

        <div>
          <label>Password*: </label>
          <input
            type="password"
            name="password"
            value={form.password}
            onChange={handleChange}
            required
          />
        </div><br/>

        <div>
          <label>Email*: </label>
          <input
            type="email"
            name="email"
            value={form.email}
            onChange={handleChange}
            required
          />
        </div><br/>

        <div>
          <label>Contact No*: </label>
          <input
            type="text"
            name="contactNo"
            value={form.contactNo}
            onChange={handleChange}
            required
          />
        </div><br/>

        <div>
          <label>Address*: </label>
          <input
            type="text"
            name="address"
            value={form.address}
            onChange={handleChange}
            required
          />
        </div><br/>

        <div>
        <label>Role*: </label>
        <select
        name="roleId"
        value={form.roleId}
        onChange={handleChange}
        required>
        <option value="">Select a role</option>
        {roles.map((role) => (
        <option key={role.id} value={Number(role.id)}>
        {role.roleType}  {/* This is the displayed text */}
        </option>
        ))}
        </select>
        </div><br/>

        <button type="submit">Create Account</button>
        <button type="submit" onClick={() => handleNavigation('/admin')}>Back to Admin Dashboard</button>
        {/* Messages */}
        {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}
        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
      </form>
    </div>
  );
};




