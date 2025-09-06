import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export function UpdateBook() {
  const [bookIdInput, setBookIdInput] = useState("");
  const [book, setBook] = useState(null);
  const [selectedCopyId, setSelectedCopyId] = useState(null);
  const [form, setForm] = useState({
    isbn: "",
    title: "",
    author: "",
    category: "",
    publicationYear: ""
  });
  const [loading, setLoading] = useState(false);
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();
      
  const handleNavigation = (path) => {
    navigate(path);
  };

  const categories = [
    { label: "Fiction", value: "FICTION" },
    { label: "Non-Fiction", value: "NON_FICTION" }
  ];

  // Load book details
  const handleLoadBook = async () => {
    if (!bookIdInput.trim()) {
      alert("Please enter a book ID");
      return;
    }
    setLoading(true);
    try {
      const res = await axios.get(`http://localhost:8080/api/books/${bookIdInput}`);
      const b = res.data;
      setBook(b);
      setForm({
        isbn: b.isbn || "",
        title: b.title || "",
        author: b.author || "",
        category: b.category || "",
        publicationYear: b.publicationYear || ""
      });
      setSelectedCopyId(""); // reset selected copy
      setErrorMessage("");
      setSuccessMessage("");
    } catch (err) {
      console.error("Error fetching book:", err);
      setErrorMessage("Failed to load book data.");
      setBook(null);
    } finally {
      setLoading(false);
    }
  };

  // Delete book
  const handleDeleteBook = async () => {
    if (!bookIdInput.trim()) return;
    if (!window.confirm("Are you sure you want to delete this book?")) return;
    try {
      await axios.delete(`http://localhost:8080/api/books/delete/${bookIdInput}`);
      setBook(null);
      setForm({
        isbn: "",
        title: "",
        author: "",
        category: "",
        publicationYear: ""
      });
      setSelectedCopyId("");
      setSuccessMessage("Book deleted successfully!");
      setErrorMessage("");
    } catch (err) {
      console.error("Error deleting book:", err);
      setErrorMessage("Failed to delete book.");
      setSuccessMessage("");
    }
  };

  // Delete selected book copy
  const handleDeleteBookCopy = async () => {
  if (selectedCopyId === null) {
    alert("Please select a copy to delete.");
    return;
  }

  if (!window.confirm("Are you sure you want to delete this copy?")) return;

  try {
    await axios.delete(`http://localhost:8080/api/bookcopies/delete/${selectedCopyId}`);
    setBook((prev) => ({
      ...prev,
      copies: prev.copies.filter((c) => c.copyId !== selectedCopyId)
    }));
    setSelectedCopyId(null);
    setSuccessMessage("Book copy deleted successfully.");
    setErrorMessage("");
  } catch (err) {
    console.error("Error deleting book copy:", err);
    setErrorMessage("Failed to delete book copy.");
    setSuccessMessage("");
  }
};

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!book) {
      alert("Please load a book first.");
      return;
    }

    const payload = {};
    if (form.isbn && form.isbn !== book.isbn) payload.isbn = form.isbn;
    if (form.title && form.title !== book.title) payload.title = form.title;
    if (form.author && form.author !== book.author) payload.author = form.author;
    if (form.category && form.category !== book.category) payload.category = form.category;
    if (form.publicationYear && form.publicationYear !== book.publicationYear) {
      payload.publicationYear = Number(form.publicationYear);
    }

    if (Object.keys(payload).length === 0) {
      alert("No changes detected.");
      return;
    }

    try {
      const res = await axios.put(
        `http://localhost:8080/api/books/update/${bookIdInput}`,
        payload
      );
      console.log("Book updated successfully:", res.data);
      setSuccessMessage("Book updated successfully!");
      setErrorMessage("");
    } catch (err) {
      console.error("Error updating book:", err.response || err);
      if (err.response && err.response.data) {
        setErrorMessage(`Update failed: ${JSON.stringify(err.response.data)}`);
      } else {
        setErrorMessage("Error updating book.");
      }
      setSuccessMessage("");
    }
  };


  return (
    <div className="create-user-container">
      <h2>Update Book</h2>

      <div>
        <label>Enter Book ID: </label>
        <input
          type="text"
          value={bookIdInput}
          onChange={(e) => setBookIdInput(e.target.value)}
          placeholder="Book ID"
        />
        <button type="button" onClick={handleLoadBook}>
          Load Book
        </button>
        <button
          type="button"
          style={{ backgroundColor: "red" }}
          onClick={handleDeleteBook}
        >
          Delete Book
        </button>
        <button type="submit" onClick={() => handleNavigation('/admin')}>Back</button>
      </div><br/>

      {loading && <p>Loading...</p>}
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      {successMessage && <p className="success-message">{successMessage}</p>}

      {book && (
        <>
          <form onSubmit={handleSubmit}>
            <div>
              <label>ISBN: </label>
              <input
                type="text"
                name="isbn"
                value={form.isbn}
                onChange={handleChange}
              />
            </div>

            <div>
              <label>Title: </label>
              <input
                type="text"
                name="title"
                value={form.title}
                onChange={handleChange}
              />
            </div>

            <div>
              <label>Author: </label>
              <input
                type="text"
                name="author"
                value={form.author}
                onChange={handleChange}
              />
            </div>

            <div>
              <label>Category: </label>
              <select
                name="category"
                value={form.category}
                onChange={handleChange}
              >
                <option value="">Select a category</option>
                {categories.map((cat) => (
                  <option key={cat.value} value={cat.value}>
                    {cat.label}
                  </option>
                ))}
              </select>
            </div>

            <div>
              <label>Publication Year: </label>
              <input
                type="number"
                name="publicationYear"
                value={form.publicationYear}
                onChange={handleChange}
              />
            </div>

            <button type="submit" className="search-button">Update Book</button>
            <button type="submit" onClick={() => handleNavigation('/admin')}>Back to Admin Dashboard</button>
          </form><br/>

{/* Delete Book Copy section */}
            {book.copies && book.copies.length > 0 && (
            <div style={{ marginTop: "0.5rem" }}>
              <label>Select Copy to Delete:</label>
              <select
                value={selectedCopyId}
                onChange={(e) => setSelectedCopyId(e.target.value)}
                style={{ width: "100%", marginTop: "0.25rem", marginBottom: "0.25rem" }}
              >
                <option value="">--Select Copy--</option>
                {book.copies.map((c) => (
                  <option key={c.copyId} value={c.copyId}>
                    Copy #{c.copyNumber} - {c.status}
                  </option>
                ))}
              </select>
              <button
                type="button"
                className="search-button"
                style={{ width: "100%", backgroundColor: "red" }}
                onClick={handleDeleteBookCopy}
              >
                Delete Book Copy
              </button>
            </div>
          )}
        </>
      )}
    </div>
  );
} 