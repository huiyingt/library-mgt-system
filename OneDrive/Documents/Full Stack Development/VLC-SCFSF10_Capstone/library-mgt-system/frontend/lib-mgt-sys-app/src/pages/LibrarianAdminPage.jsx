import React from 'react';
import { useNavigate } from 'react-router-dom';

export function LibrarianAdminPage () {
  const navigate = useNavigate();

  const handleNavigation = (path) => {
    navigate(path);
  };

  return (
    <div>
      {/* Librarian Actions */}
      <div className="create-user-container">
        <h2 className="actions-title">Librarian Actions </h2>

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
            Add New User
          </button>
          <button
            className="action-button"
            onClick={() => handleNavigation('/users/update')}
          >
            Update User
          </button>
        </div>
        {/* Books Section */}
        <h4 className="actions-title">Manage Books: </h4>
        <div className="actions-container">
          <button
            className="action-button"
            onClick={() => handleNavigation('/search/books/librarian')}
          >
            Search Book
          </button>
          <button
            className="action-button"
            onClick={() => handleNavigation('/books/create')}
          >
            Add New Book
          </button>
          <button
            className="action-button"
            onClick={() => handleNavigation('/books/update')}
          >
            Update Book
          </button>
        </div>
        {/* Loans Section */}
        <h4 className="actions-title">Manage Loans: </h4>
        <div className="actions-container">
          <button
            className="action-button"
            onClick={() => handleNavigation('/loans/manage')}
          >
            Manage Loans
          </button>
        </div>
      </div>
    </div>
  );
}
