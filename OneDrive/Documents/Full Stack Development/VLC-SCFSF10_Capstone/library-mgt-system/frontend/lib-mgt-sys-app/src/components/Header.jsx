import React, { useState } from 'react';
import { User, Menu, LogIn, LogOut } from 'lucide-react'; // lucide-react for icons
import { useNavigate } from 'react-router-dom';

// Header component
const Header = ({user, onLogout }) => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const navigate = useNavigate();

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const goToPage = (path) => {
    navigate(path);
    setIsMenuOpen(false); // Close menu on navigation
  };


  return (
    <header className="header-container">
      {/* Logo - clickable */}
      <div className="logo-link" onClick={() => goToPage('/home')}>
        <img
          src="https://placehold.co/40x40/1E3A8A/FBBF24?text=LIB"
          alt="My Community Library Logo"
          className="logo-image"
          onError={(e) => (e.target.src = "https://placehold.co/40x40/1E3A8A/FBBF24?text=LIB")}
        />
        <span className="logo-text">My Community Library</span>
      </div>

      {/* Desktop Navigation */}
      <nav className="desktop-nav">
        <a href="/books" onClick={() => goToPage('/books')} className="nav-link">Books</a>
        <a href="#" onClick={() => goToPage('/services')} className="nav-link">Services</a>
        <a href="#" onClick={() => goToPage('/about')} className="nav-link">About Us</a>
      </nav>

      {/* User / Login / Mobile Menu */}
      <div className="user-menu-container">
        {user ? (
          <div className="user-logged-in">
            <User className="user-icon" />
            <span className="user-name">Hi, {user.username}</span>
            <button onClick={onLogout} className="logout-button" aria-label="Logout">
              <LogOut className="lucide-icon" />
            </button>
          </div>
        ) : (
          <button
            onClick={() => goToPage('/login')}
            className="login-button"
            aria-label="Login"
          >
            <LogIn className="lucide-icon" />
            <span className="login-text">Login</span>
          </button>
        )}
        
      {/* Mobile Menu Button */}
        <button
          onClick={toggleMenu}
          className="mobile-menu-button"
          aria-label="Toggle menu"
        >
          <Menu className="lucide-icon" />
        </button>
      </div>

      {/* Mobile Navigation Menu */}
      {isMenuOpen && (
        <div className="mobile-nav-menu">
          <a href="books" onClick={() => goToPage('/books')} className="action-button hover-button">Books</a>
          <a href="services" onClick={() => goToPage('/services')} className="action-button hover-button">Services</a>
          <a href="about" onClick={() => goToPage('/about')} className="action-button hover-button">About Us</a>

      </div>
      )}
  </header>
  )
};
export default Header;