import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export function CreateBook () {
  const [book, setBook] = useState({
    isbn: '',
    
    author: '',
    category: '',
    publicationYear: ''
  });

  const [bookId, setBookId] = useState(null);
  const [bookcopy, setBookcopy] = useState({
    copyNumber: '',
    numCopies: 1,
    status: 'AVAILABLE',
    bookId: ''
  });

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [copySuccessMessage, setCopySuccessMessage] = useState("");
  const [copyErrorMessage, setCopyErrorMessage] = useState("");

  const navigate = useNavigate();
    
  const handleNavigation = (path) => {
    navigate(path);
  };

  // Category options: label shown to user, value sent to backend
  const categories = [
    { label: "Fiction", value: "FICTION" },
    { label: "Non-Fiction", value: "NON_FICTION" },
  ];

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/books/create', book);
      console.log("Book created response:", response.data); 
      const newBookId = response.data.id || response.data.bookId; // fallback if your backend uses different field
      setBookId(newBookId);
      
      setSuccessMessage(`Book created successfully! ID: ${newBookId}`);
      setBook({
        isbn: '',
        title: '',
        author: '',
        category: '',
        publicationYear: ''
      });
      // Pre-fill Book Copy form
      setBookcopy({
        bookId: newBookId,
        copyNumber: 1,
        numCopies: 1,
        status: 'AVAILABLE'
      });
    } catch (error) {
      console.error(error);
      setErrorMessage('Error creating book. Check console for details.');
    }
  };

    // Book copy form handlers
  const handleCopyChange = (e) => {
    const { name, value } = e.target;
    setBookcopy((prev) => ({ ...prev, [name]: value }));
  };

  const handleCopySubmit = async (e) => {
    e.preventDefault();

    try {
      const requests = [];
      const start = parseInt(bookcopy.copyNumber, 10);
      const total = parseInt(bookcopy.numCopies, 10) || 1;

      for (let i = 0; i < total; i++) {
        requests.push(
          axios.post('http://localhost:8080/api/bookcopies/create', {
            bookId: bookcopy.bookId,
            copyNumber: start + i,
            status: bookcopy.status || "AVAILABLE"
          })
        );
      }
      const responses = await Promise.all(requests);
      console.log("Book copy/copies created responses:", responses);

       setCopySuccessMessage(
        `Created ${responses.length} copies successfully! (Book ID: ${bookcopy.bookId})`
      );
      setCopyErrorMessage("");

      setBookcopy({
        bookId: bookId, // keep the bookId for adding more copies
        copyNumber: start + total, // increment copy number for next addition
        numCopies: 1,
        status: 'AVAILABLE'
      });
    } catch (error) {
      console.error(error);
      setCopyErrorMessage('Error creating book copy. Check console for details.');
      setCopySuccessMessage("");
    }
  };

  return (
    <div className="create-book-container">
      <h2>Create New Book</h2>
      <p>Fill in the details below. All fields are required.</p>
      <form onSubmit={handleSubmit}>
        <div>
          <label>ISBN: </label>
          <input name="isbn" value={book.isbn} 
          onChange={handleChange} required
          placeholder = "Please omit dashes"
          />
        </div><br/>
        <div>
          <label>Title: </label>
          <input name="title" value={book.title} onChange={handleChange} required />
        </div><br/>
        <div>
          <label>Author: </label>
          <input name="author" value={book.author} onChange={handleChange} required />
        </div><br/>
        <div>
          <label>Category: </label>
          <select
            name="category"
            value={book.category}
            onChange={handleChange}
            required
          >
            <option value="">Select a category</option>
            {categories.map((cat) => (
              <option key={cat.value} value={cat.value}>
                {cat.label}
              </option>
            ))} 
          </select>
        </div><br/>
        <div>
          <label>Publication Year: </label>
          <input
            name="publicationYear"
            type="number"
            value={book.publicationYear}
            onChange={handleChange}
            required
          />
        </div><br/>
        <button type="submit">Create Book</button>
        <button type="submit" onClick={() => handleNavigation('/admin')}>Back to Admin Dashboard</button>
      </form>
      {/* Book Messages */}
        {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}
        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}

      {/* Book Copy Form - only show when book created */}
      {bookId && (
        <div style={{ marginTop: "2rem" }}>
          <h2>Add Book Copies</h2>
          <form onSubmit={handleCopySubmit}>
            <div>
              <label>Book ID: </label>
              <input
                name="bookId"
                type="number"
                value={bookcopy.bookId}
                readOnly
              />
            </div><br/>
            <div>
              <label>Starting Copy Number: </label>
              <input
                name="copyNumber"
                type="number"
                value={bookcopy.copyNumber}
                onChange={handleCopyChange}
                required
              />
            </div><br/>
            <div>
            <label>Number of Copies: </label>
            <input
              name="numCopies"
              type="number"
              min="1"
              value={bookcopy.numCopies || ""}
              onChange={handleCopyChange}
              required
            />
            </div><br/>
            <button type="submit">Add Copies</button>
          </form>

          {/* Book Copy Messages */}
          {copySuccessMessage && <p style={{ color: "green" }}>{copySuccessMessage}</p>}
          {copyErrorMessage && <p style={{ color: "red" }}>{copyErrorMessage}</p>}
        </div>
      )}
    </div>
  );
}



