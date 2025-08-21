import { useState } from 'react'
import { InputField } from './components/InputField.jsx'
import './App.css'

function App() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [success, setSuccess] = useState('');
  const [errors, setErrors] = useState({});

  const validate = () => { 
    const newErrors = {};
  
    if (!username.trim()) newErrors.username = "Username is required.";
    if (!email.includes('@') || !email.endsWith('.sg')) {
      newErrors.email = "Email must contain '@' and end with '.sg'";
    }
    if (!email.trim()) newErrors.email = "Email is required.";
    if (!password.trim()) newErrors.password = "Password is required.";

    return newErrors;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const validationErrors = validate();

    if (Object.keys(validationErrors).length>0) {
      setErrors (validationErrors);
      setSuccess('')
    } else {
      console.log("Submitted:", { username, email })
    
      setUsername('');
      setEmail('');
      setPassword('');
      setErrors({});
      setSuccess('Login successful!');
    }
  };

  
return (
    <form onSubmit={handleSubmit}>
      <h2>Singapore Town Council Login</h2>
      <InputField
        label="Username: "
        type="text"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Enter your username"
      />
      <p>{errors.username}</p>

      <InputField
        label="Email: "
        type="text"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="example@domain.sg"
      />
      <p>{errors.email}</p>

      <InputField
        label="Password: "
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Enter your password"
      />
      <p>{errors.password}</p>

      <button type="submit">Login</button>

      <p>{success}</p>
    </form>
  );
}

export default App;

