import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import AboutUs from './pages/AboutUs';
import { SearchBook } from './pages/SearchBook';
import { LoginPage } from './pages/LoginPage';
import { MemberPage } from './pages/MemberPage';
import { LibrarianAdminPage } from './pages/LibrarianAdminPage';  
import { CreateBook } from './pages/CreateBook';
import { SearchBookLib } from './pages/SearchBookLib';
import { UpdateBook } from './pages/UpdateBook';
import { CreateUser } from './pages/CreateUser';
import { UpdateUser } from './pages/UpdateUser';
import { SearchUser } from './pages/SearchUser';
import { ManageLoan } from './pages/ManageLoan';
import Header from './components/Header';
import Footer from './components/Footer';
import './index.css'
// Import other pages later: BooksPage, ServicesPage, AboutPage

const App = () => {
  const [currentUser, setCurrentUser] = useState(null); // State for the logged-in user

  console.log('Current user:', currentUser); // <-- Add this line

  // login process
  const handleLogin = (user) => {
    setCurrentUser(user);
  };

  const handleLogout = () => {
    setCurrentUser(null);
  };

  return (
    <Router>
      <div className="page-container">
      <Header
        user={currentUser}
        onLogout={handleLogout}
      />
      <main className="main-content">
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/about" element={<AboutUs />} />
        <Route path="/home" element={<HomePage />} />
        <Route path='/books' element={<SearchBook />} /> {/* Placeholder for BooksPage */}
        <Route path="/search" element={<SearchBook />} />
        <Route path="/login" element={<LoginPage onLogin={handleLogin} />}  />
        <Route path="/member" element={<MemberPage currentUser={currentUser} />} /> {/* Member page */}
        <Route path="/admin" element={<LibrarianAdminPage />} /> {/* Librarian/Admin page */}
        <Route path="/books/create" element={<CreateBook />} /> {/* Create Book page */}
        <Route path="/users/create" element={<CreateUser />} /> {/* Create Book page */}
        <Route path="/users/search" element={<SearchUser />} /> {/* Search/Delete User page */}
        <Route path="/users/update" element={<UpdateUser />} /> {/* Update User page */}
        <Route path="/search/books/librarian" element={<SearchBookLib />} /> {/* Search Books for librarian */}
        <Route path="/books/update" element={<UpdateBook />} /> {/* Update Book page */}
        <Route path="/loans/manage" element={<ManageLoan />} /> {/* Manage Loans page */}
        {/* Add other pages later, e.g. /books, /services, /about */}
      </Routes>
      </main>

      <Footer />
      </div>
    </Router>
  );
};

export default App;

