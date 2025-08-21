import React from 'react';
import '../styles/TodoStats.css';


export function TodoStats({ todos }) {
  const total = todos.length;
  const completed = todos.filter((t) => t.completed).length;

  return (
    <div className="todo-stats">
      <h2>To-do Dashboard</h2>
      <p>Total To-dos: <strong>{total}</strong></p>
      <p>Completed: <strong>{completed}</strong></p>
    </div>
  );
}


