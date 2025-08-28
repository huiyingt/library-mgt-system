import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useLocation } from 'react-router-dom';

export function SearchBook () {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const initialQuery = queryParams.get('query') || '';
    const [searchTerm, setSearchTerm] = useState(initialQuery);
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleSearch = React.useCallback(async (e) => {
        if (e) e.preventDefault();
        if (!searchTerm.trim()) {
            setBooks([]);
            return;
        }
        
        setLoading(true);
        setError(null);

        try {
            const response = await axios.get(`http://localhost:8080/api/books/search?query=${searchTerm}`);
            setBooks(response.data);
        }   catch (error) {
            setError(`Error fetching books. Please try again. ${error.message}`);
        }   finally {
            setLoading(false);
        }
    }, [searchTerm]);

    // Auto search on page load if query exists
    useEffect(() => {
        if (initialQuery) handleSearch();
    }, [initialQuery, handleSearch]);

    return (
    <div className="form-wrapper">
      <h2 className="search-title">Search Books</h2>
      <form onSubmit={handleSearch} className="search-form">
        <input
          type="text"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          placeholder="Enter book title or author"
          className="search-input-box"
        />
        <button type="submit" className="search-submit-btn">Search</button>
      </form>

        {loading && <p>Loading...</p>}
        {error && <p className="error-message">{error}</p>}
        {!loading && !error && books.length === 0 && searchTerm.trim() && (
            <p className="error-message">Book not found.</p>
        )}
        
    <ul className="results-list">
        {books.map((book) => {
        const categoryMap = {
        FICTION: 'Fiction',
        NON_FICTION: 'Non-Fiction',
        };

        return (
            <>
                <li key={book.bookid} className="result-item">
                    <strong>ISBN:</strong> {book.isbn} <br />
                    <strong>Title:</strong> {book.title} <br />
                    <strong>Author:</strong> {book.author} <br />
                    <strong>Year:</strong> {book.publicationYear} <br />
                    <strong>Category:</strong> {categoryMap[book.category] || book.category} <br />
                </li>
                <br/>
            </>
        );
        })}
    </ul>
    </div>
);
}