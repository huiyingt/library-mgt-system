import React from 'react';

// Footer component
const Footer = () => {
  return (
    <footer className="footer-container">
      <div className="footer-text">
        &copy; {new Date().getFullYear()} My Community Library. All rights reserved.
      </div>
    </footer>
  );
};



export default Footer;