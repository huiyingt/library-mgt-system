import React from 'react';
import { useNavigate } from 'react-router-dom';

export function LibrarianAdminPage () {
  const navigate = useNavigate();

  const handleNavigation = (path) => {
    navigate(path);
  };

  // Dashboard stats - replace with API data
  const dashboardStats = {
    booksOnLoan: 12,
    booksOnReservation: 5,
    booksAvailable: 43,
    members: 27,
  };

  return (
    <div className="page-container">
      <main className="main-content">

        {/* Dashboard */}
        <div className="grid-container">
          <div className="card dashboard-card">
            <h2 className="search-title">Dashboard</h2>
            <div className="dashboard-stats">
              <div className="stat-box">
                <h3>Books on Loan</h3>
                <p>{dashboardStats.booksOnLoan}</p>
              </div>
              <div className="stat-box">
                <h3>Books on Reservation</h3>
                <p>{dashboardStats.booksOnReservation}</p>
              </div>
              <div className="stat-box">
                <h3>Books Available</h3>
                <p>{dashboardStats.booksAvailable}</p>
              </div>
              <div className="stat-box">
                <h3>Members</h3>
                <p>{dashboardStats.members}</p>
              </div>
            </div>
          </div>
        </div>

        {/* Librarian Actions */}
        <div>
          <h3 className="actions-title">Librarian Actions</h3>
          <div className="actions-container">
            <h4 className="actions-title">Manage Users: </h4>
          <div className="actions-container">
            <button
              className="action-button"
              onClick={() => handleNavigation('/users/search')}
            >
              Search User
            </button>
                        <button
              className="action-button"
              onClick={() => handleNavigation('/users/create')}
            >
              Add User
            </button>

            <button
              className="action-button"
              onClick={() => handleNavigation('/users/update')}
            >
              Update User
            </button>
            
          {/* Books Section */}
            <div className="actions-container">
              <h4 className="actions-title">Manage Books: </h4>
            <div className="actions-container"> 
            <button
              className="action-button"
              onClick={() => handleNavigation('/search-books')}
            >
              Search Books
            </button>
            
            <button
              className="action-button"
              onClick={() => handleNavigation('/books/create')}
            >
              Add Book
            </button>
            <button
              className="action-button"
              onClick={() => handleNavigation('/books/update')}
            >
              Update Book
            </button>
            </div>
          </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  );
}
