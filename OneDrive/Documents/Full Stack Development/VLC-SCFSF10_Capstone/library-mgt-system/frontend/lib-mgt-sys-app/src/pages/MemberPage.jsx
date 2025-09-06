import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export function MemberPage({ currentUser }) {
  const navigate = useNavigate();
  const [loanItems, setLoanItems] = useState([]);
  const [totalFine, setTotalFine] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

    useEffect(() => {
        if (!currentUser) {
        navigate('/login'); // redirect if not logged in
        return;
        }

        setLoading(true);
        setError(null);

    // Fetch loans for the current user
    axios.get(`http://localhost:8080/api/loanitems/user/${currentUser.username}`)
      .then((res) => {
        console.log('Loan response data:', res.data);
        
        const allItems = res.data || [];
        setLoanItems(allItems);

        // Calculate total fine safely
        const fineSum = allItems.reduce((sum, item) => sum + (item.fineAmount || 0),0)
        setTotalFine(fineSum);

        setLoading(false);
      })
      .catch(err => {
        console.error('Failed to fetch loans', err);
        setError('Failed to fetch loan items');
        setLoading(false);
      });
    }, [currentUser, navigate]);

    if (!currentUser) return null; // or a loading spinner

    return (
    <div className="page-container">
    <main className="main-content">
        {/* Loading or Error State */}
        {loading && <p>Loading your loans...</p>}
        {error && <p style={{ color: 'red', textAlign: 'center' }}>{error}</p>}

        {!loading && !error && (
          <>

    {/* Dashboard (above loan items) */}
    <div className="grid-container">
      <div className="card hover-card">
        <h2 className="search-title">Welcome, {currentUser?.username}</h2>
        <div className="dashboard-stats">
          <div className="stat-box">
            <h3>Current Loan Items</h3>
            <p>{loanItems.length}</p>
          </div>
          <div className="stat-box">
            <h3>Total Fines</h3>
            <p><strong>${totalFine.toFixed(2)}</strong></p>
            {totalFine > 0 && (
              <div style={{ color: 'red', fontWeight: 'bold' }}>
                Please return your overdue items and/or pay outstanding fines before you can borrow more books.
              </div>
            )}
          </div>
        </div>
      </div>
    </div>

    {/* Loan Items Table */}
    <div className="grid-container">
      <div className="card hover-card">
        <h3 className="search-title">Your Loan Items</h3>
        <table className="table">
          <thead>
            <tr>
              <th>Book Title</th>
              <th>Copy Number</th>
              <th>Due Date</th>
              <th>Return Date</th>
              <th>Renewal Count</th>
              <th>Fine</th>
            </tr>
          </thead>
          <tbody>
            {loanItems.length > 0 ? (
              loanItems.map(item => (
                <tr key={item.id}>
                  <td>{item.bookCopy.title}</td>
                  <td>{item.bookCopy.copyNumber}</td>
                  <td>{item.dueDate}</td>
                  <td>{item.returnDate || '-'}</td>
                  <td>{item.renewalCount}</td>
                  <td>${item.fineAmount.toFixed(2)}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="6" style={{ textAlign: 'center' }}>
                  No current loans
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>

    {/* Actions */}
    <div className="actions-container">
      <button className="action-button" onClick={() => navigate('/books')}>
        Browse Books
      </button>
    </div>
    </>
    )}

  </main>
</div>
  );
}
