import React from 'react';
import '../styles/TodoFilter.css';

export function TodoFilter({ currentFilter, setFilter }) {
  const filters = ['All', 'Active', 'Completed'];

  return (
    <div className="todo-filter">
      {filters.map((filter) => (
        <button
          key={filter}
          className={`filter-btn ${currentFilter === filter ? 'active' : ''}`}
          onClick={() => setFilter(filter)}
        >
          {filter}
        </button>
      ))}
    </div>
  );
}

