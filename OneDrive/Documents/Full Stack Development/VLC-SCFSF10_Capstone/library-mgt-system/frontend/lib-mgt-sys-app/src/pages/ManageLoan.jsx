import { useState} from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

export function ManageLoan() {
  const [username, setUsername] = useState("");
  const [loans, setLoans] = useState([]);
  const [bookCopyId, setBookCopyId] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();
    
  const handleNavigation = (path) => {
    navigate(path);
  };
  // Fetch active loans by username
  const fetchLoans = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/api/loanitems/user/${username}`);
      setLoans(res.data);
    } catch (err) {
      console.error("Failed to fetch loans", err);
      setLoans([]);
      setErrorMessage("Failed to fetch loans. Please check username.");
      setSuccessMessage("");
    }
  };

  // Borrow a new book
  const borrowBook = async () => {
    try {
      // Check if user already has 3 active loans
      if (loans.length >= 3) {
      setErrorMessage("Cannot borrow more than 3 books. Return some books first.");
      setSuccessMessage("");
      return; // stop borrowing
      }

      // Check if user has any unpaid fines
      const hasFine = loans.some((loan) => loan.fineAmount > 0);
      if (hasFine) {
      setErrorMessage("Cannot borrow new books until overdue books are returned or outstanding fines are cleared.");
      setSuccessMessage("");
      return; // stop borrowing
      }

      await axios.post(`http://localhost:8080/api/loans/borrow`, {
        username,
        loanItems: [{
          bookCopy: { copyId: bookCopyId}
        }]
        });

        //clear the input
        setBookCopyId("");
        await fetchLoans();
        setSuccessMessage("Book borrowed successfully!");
        setErrorMessage("");
        } catch (err) {
          console.error("Borrow failed", err);
          setErrorMessage("Failed to borrow book. Maximum active loans reached or invalid copy ID.");
          setSuccessMessage("");
      }
    }

    // Return a book
    const returnBook = async (loanItemId) => {
    try {
      await axios.put(`http://localhost:8080/api/loanitems/return/${loanItemId}`);
      fetchLoans();
      setSuccessMessage("Book returned successfully!");
      setErrorMessage("");
    } catch (err) {
      console.error("Return failed", err);
      setErrorMessage("Failed to return book.");
      setSuccessMessage("");
    }
    };

    // Renew a book
    const renewBook = async (loanItemId) => {
    try {
      await axios.put(`http://localhost:8080/api/loanitems/renew/${loanItemId}`);
      fetchLoans();
      setSuccessMessage("Book renewed successfully!");
      setErrorMessage("");
    } catch (err) {
      console.error("Renew failed", err);
      setErrorMessage("Failed to renew book.");
      setSuccessMessage("");
    }
    };

  return (
    <div className="create-user-container">
      <main className="main-content">
        <h2 className="search-title">Manage Loans</h2>
       
        {successMessage && <p style={{ color: "green" }}>{successMessage}</p>}
        {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}
       
        {/* Search user */}
        <div className="card">
          <label>Enter Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          <button onClick={fetchLoans} className="search-button">
            Get Loan
          </button>
        </div>

        {/* Borrow new book */}
        <div className="card">
          <h3>Borrow a Book</h3>
          <input
            type="text"
            placeholder="Book Copy ID"
            value={bookCopyId}
            onChange={(e) => setBookCopyId(e.target.value)}
          />
          <button onClick={borrowBook} className="search-button">
            Borrow
          </button>
        </div>

        {/* Active loans */}
        <div className="card">
          <h3>Active Loans</h3>
          <table className="table">
            <thead>
              <tr>
                <th>Book Title</th>
                <th>Copy Number</th>
                <th>Due Date</th>
                <th>Renewals</th>
                <th>Fine</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {loans.map((item) => (
                <tr key={item.id}>
                  <td>{item.bookCopy?.title}</td>
                  <td>{item.bookCopy?.copyNumber}</td>
                  <td>{item.dueDate}</td>
                  <td>{item.renewalCount}</td>
                  <td><strong>${item.fineAmount.toFixed(2)}</strong></td>
                  <td>
                    <button
                      className="search-button"
                      onClick={() => returnBook(item.id)}
                    >
                      Return
                    </button>
                    <button
                      className="search-button"
                      onClick={() => renewBook(item.id)}
                    >
                      Renew
                    </button>
                  </td>
                </tr>
              ))}
              {loans.length === 0 && (
                <tr>
                  <td colSpan="6" style={{ textAlign: "center" }}>
                    No active loans
                  </td>
                </tr>
              )}
            </tbody>
          </table><br/>
          <button type="submit" onClick={() => handleNavigation('/admin')}>Back to Admin Dashboard</button>
        </div>
      </main>
    </div>
  );
}
