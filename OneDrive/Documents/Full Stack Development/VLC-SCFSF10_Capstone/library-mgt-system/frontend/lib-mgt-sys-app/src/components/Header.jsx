import React, { useState } from 'react';
import { User, Menu, LogIn, LogOut } from 'lucide-react'; // lucide-react for icons
import { useNavigate } from 'react-router-dom';

// Header component
const Header = ({user, onLogout }) => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const navigate = useNavigate();

  console.log('Header user prop:', user);

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const goToPage = (path) => {
    navigate(path);
    setIsMenuOpen(false); // Close menu on navigation
  };

  // Logout handler inside Header
  const handleLogout = () => {
  if (onLogout) onLogout(); // call the parent logout logic
  navigate('/home');         // then redirect to home
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
        <a href="#" onClick={() => goToPage('/about')} className="nav-link">About Us</a>
      </nav>

      {/* User / Login / Member Icon / Mobile Menu */}
      <div className="user-menu-container">
        {user ? (
          <div className="user-logged-in">
            {/* Member Icon - go to MemberPage */}
            <User
              className="user-icon clickable"
              title="My Account"
              onClick={() => goToPage('/member')}
            />
            <span className="user-name">Hi, {user.username}</span>

            {/* Logout Button */}
            <button onClick={handleLogout} className="logout-button" aria-label="Logout">
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
          <a href="Books" onClick={() => goToPage('/books')} className="action-button hover-button">Books</a>
          <a href="About" onClick={() => goToPage('/about')} className="action-button hover-button">About Us</a>
          {user && (
            <a href="Member" onClick={() => goToPage('/member')} className="action-button hover-button">My Account</a>
          )}
          {user && (
            <button
              onClick={onLogout}
              className="action-button hover-button"
            >
              Logout
            </button>
          )}
        </div>
      )}
    </header>
  );
};

export default Header;