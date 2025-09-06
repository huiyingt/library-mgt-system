import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const navigate = useNavigate();

  const handleSearch = () => {
    if (searchQuery.trim()) {
      navigate(`/search?query=${encodeURIComponent(searchQuery)}`);
    }
  };

  return (
    <div className="page-container">
      <main className="main-content">
        {/* Search Collections */}
        <div className="grid-container">
          <div className="card hover-card">
            <h1 className="search-title">
              Search our Collections
            </h1>
            <div className="search-box">
              <input
                type="text"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder="Start your search here"
                className="search-input"
              />
              <button onClick={handleSearch} className="search-button">
                Go
              </button>
            </div>
          </div>

          {/* Plan Your Visit */}
          <div className="visit-card hover-card">
            <h2 className="visit-title">Visit Us</h2>
            <div className="location-container">
              <label className="location-label">My Community Library is located at: </label><br/><br/>
              <label className="location-address"><strong>21 Springdale Road Singapore 111222</strong></label>
            </div>
            <p className="hours-text">Opens daily from <strong>10:00 AM to 07:00 PM</strong></p>
          </div>
        </div>
      </main>
      
      {/* Footer component is rendered in App.jsx */}
    </div>
  );
};
export default HomePage;