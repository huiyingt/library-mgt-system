import React, { useState } from 'react';
import axios from 'axios';

export function CreateBook () {
  const [book, setBook] = useState({
    isbn: '',
    title: '',
    author: '',
    category: '',
    publicationYear: ''
  });

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

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
      setSuccessMessage(`Book created successfully! ID: ${response.data.id}`);
      setBook({
        isbn: '',
        title: '',
        author: '',
        category: '',
        publicationYear: ''
      });
    } catch (error) {
      console.error(error);
      setErrorMessage('Error creating book. Check console for details.');
    }
  };

  return (
    <div className="create-book-container">
      <h2>Create New Book</h2>
      <p>Fill in the details below. All fields are required.</p>
      <form onSubmit={handleSubmit}>
        <div>
          <label>ISBN: </label>
          <input name="isbn" value={book.isbn} onChange={handleChange} required />
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
      </form>
      {/* Messages */}
        {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}
        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
    </div>
  );
};


