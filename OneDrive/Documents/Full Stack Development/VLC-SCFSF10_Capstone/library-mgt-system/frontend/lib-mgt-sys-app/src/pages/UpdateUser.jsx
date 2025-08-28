import React, { useState } from "react";
import axios from "axios";

export function UpdateUser() {
  const [userIdInput, setUserIdInput] = useState("");
  const [user, setUser] = useState(null);
  const [roles, setRoles] = useState([]);
  const [form, setForm] = useState({
    username: "",
    email: "",
    password: "",
    contactNo: "",
    address: "",
    roleId: "",
    membershipStartDate: "",
    membershipEndDate: ""
  });
  const [loading, setLoading] = useState(false);
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  // Load user details based on entered ID
  const handleLoadUser = async () => {
    if (!userIdInput.trim()) {
      alert("Please enter a user ID");
      return;
    }
    setLoading(true);
    try {
      const [userRes, rolesRes] = await Promise.all([
        axios.get(`http://localhost:8080/api/users/${userIdInput}`),
        axios.get("http://localhost:8080/api/roles/all")
      ]);

      const u = userRes.data;
      setUser(u);
      setRoles(Array.isArray(rolesRes.data) ? rolesRes.data : []);

      const startDate = u.membershipStartDate
        ? new Date(u.membershipStartDate).toISOString().split("T")[0]
        : "";
      const endDate = u.membershipEndDate
        ? new Date(u.membershipEndDate).toISOString().split("T")[0]
        : "";

      setForm({
        username: u.username || "",
        email: u.email || "",
        password: "",
        contactNo: u.contactNo || "",
        address: u.address || "",
        roleId: u.role ? u.role.id : "",
        membershipStartDate: startDate,
        membershipEndDate: endDate
      });

      setErrorMessage("");
      setSuccessMessage("");
    } catch (err) {
      console.error("Error fetching user or roles:", err);
      setErrorMessage("Failed to load user data.");
      setUser(null);
      setRoles([]);
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: name === "roleId" ? Number(value) : value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!user) {
      alert("Please load a user first.");
      return;
    }

    const payload = {};

    if (form.username && form.username !== user.username) payload.username = form.username;
    if (form.email && form.email !== user.email) payload.email = form.email;
    if (form.password && form.password.trim() !== "") payload.password = form.password;
    if (form.contactNo && form.contactNo !== user.contactNo) payload.contactNo = form.contactNo;
    if (form.address && form.address !== user.address) payload.address = form.address;
    if (form.roleId && form.roleId !== (user.role ? user.role.id : "")) payload.role = { id: Number(form.roleId) };
    if (form.membershipStartDate && form.membershipStartDate !== user.membershipStartDate) payload.membershipStartDate = form.membershipStartDate;
    if (form.membershipEndDate && form.membershipEndDate !== user.membershipEndDate) payload.membershipEndDate = form.membershipEndDate;

    if (Object.keys(payload).length === 0) {
      alert("No changes detected.");
      return;
    }

    try {
      const res = await axios.put(`http://localhost:8080/api/users/update/${userIdInput}`, payload);
      console.log("User updated successfully:", res.data);
      setSuccessMessage("User updated successfully!");
      setErrorMessage("");
    } catch (err) {
      console.error("Error updating user:", err.response || err);
      if (err.response && err.response.data) {
        setErrorMessage(`Update failed: ${JSON.stringify(err.response.data)}`);
      } else {
        setErrorMessage("Error updating user.");
      }
      setSuccessMessage("");
    }
  };

  return (
    <div className="create-user-container">
      <h2>Update User</h2>

      <div>
        <label>Enter User ID: </label>
        <input
          type="text"
          value={userIdInput}
          onChange={(e) => setUserIdInput(e.target.value)}
          placeholder="User ID"
        />
        <button type="button" onClick={handleLoadUser}>Load User</button>
      </div>

      {loading && <p>Loading...</p>}
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      {successMessage && <p className="success-message">{successMessage}</p>}

      {user && (
        <form onSubmit={handleSubmit}>
          <div>
            <label>Username*: </label>
            <input type="text" name="username" value={form.username} onChange={handleChange} placeholder="Required" />
          </div>

          <div>
            <label>Password: </label>
            <input type="password" name="password" value={form.password} onChange={handleChange} placeholder="Leave blank to keep existing" />
          </div>

          <div>
            <label>Email: </label>
            <input type="email" name="email" value={form.email} onChange={handleChange} placeholder="Leave blank to keep existing" />
          </div>

          <div>
            <label>Contact No: </label>
            <input type="text" name="contactNo" value={form.contactNo} onChange={handleChange} placeholder="Leave blank to keep existing" />
          </div>

          <div>
            <label>Address: </label>
            <input type="text" name="address" value={form.address} onChange={handleChange} placeholder="Leave blank to keep existing" />
          </div>

          <div>
            <label>Role: </label>
            <select name="roleId" value={form.roleId} onChange={handleChange}>
              <option value="">Select a role</option>
              {roles.map((role) => (
                <option key={role.id} value={role.id}>{role.roleType}</option>
              ))}
            </select>
          </div>

          <div>
            <label>Membership Start Date: </label>
            <input type="date" name="membershipStartDate" value={form.membershipStartDate} onChange={handleChange} />
          </div>

          <div>
            <label>Membership End Date: </label>
            <input type="date" name="membershipEndDate" value={form.membershipEndDate} onChange={handleChange} />
          </div>

          <button type="submit">Update User</button>
        </form>
      )}
    </div>
  );
}
