import React, { useEffect, useState } from 'react';

export const RolesPage = () => {
  const [roles, setRoles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/api/roles/all')
      .then((res) => {
        if (!res.ok) throw new Error('Network response was not ok');
        return res.json();
      })
      .then((data) => {
        setRoles(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading roles...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div>
      <h2>Roles List</h2>
      <ul>
        {roles.map((role) => (
          <li key={role.roleid}>{role.roleType}</li>
        ))}
      </ul>
    </div>
  );
};

