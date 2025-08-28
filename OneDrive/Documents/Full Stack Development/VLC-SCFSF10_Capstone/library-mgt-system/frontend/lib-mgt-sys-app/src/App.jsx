import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import { SearchBook } from './pages/SearchBook';
import { LoginPage } from './pages/LoginPage';
import { LibrarianAdminPage } from './pages/LibrarianAdminPage';  
import { CreateBook } from './pages/CreateBook';
import { CreateUser } from './pages/CreateUser';
import { UpdateUser } from './pages/UpdateUser';
import { SearchUser } from './pages/SearchUser';
import Header from './components/Header';
import Footer from './components/Footer';
import './index.css'
// Import other pages later: BooksPage, ServicesPage, AboutPage

const App = () => {
  const [currentUser, setCurrentUser] = useState(null); // State for the logged-in user

  // Simulate a login process
  const handleLogin = () => {
    console.log("Attempting to log in...");
    setTimeout(() => {
      setCurrentUser({ id: 'user123', name: 'John Doe', email: 'john.doe@example.com' });
      console.log("Simulated login successful!");
    }, 1000);
  };

  // Simulate a logout process
  const handleLogout = () => {
    console.log("Attempting to log out...");
    setTimeout(() => {
      setCurrentUser(null);
      console.log("Simulated logout successful!");
    }, 500);
  };

  return (
    <Router>
      <div className="page-container">
      <Header
        currentUser={currentUser}
        onLogin={handleLogin}
        onLogout={handleLogout}
      />
      <main className="main-content">
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path='/books' element={<SearchBook />} /> {/* Placeholder for BooksPage */}
        <Route path="/search" element={<SearchBook />} />
        <Route path="/login" element={<LoginPage onLogin={handleLogin} />}  />
        <Route path="/admin" element={<LibrarianAdminPage />} /> {/* Librarian/Admin page */}
        <Route path="/books/create" element={<CreateBook />} /> {/* Create Book page */}
        <Route path="/users/create" element={<CreateUser />} /> {/* Create Book page */}
        <Route path="/users/search" element={<SearchUser />} /> {/* Search/Delete User page */}
        <Route path="/users/update" element={<UpdateUser />} />
        {/* Add other pages later, e.g. /books, /services, /about */}
      </Routes>
      </main>

      <Footer />
      </div>
    </Router>
  );
};

export default App;

