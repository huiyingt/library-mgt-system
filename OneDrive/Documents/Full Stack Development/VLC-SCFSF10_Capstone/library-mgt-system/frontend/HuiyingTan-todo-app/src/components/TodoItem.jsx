import React, { useState } from 'react';
import '../styles/TodoItem.css';

export function TodoItem({ todo, updateTodo, deleteTodo }) {
  const [isEditing, setIsEditing] = useState(false);
  const [editTitle, setEditTitle] = useState(todo.title);
  const [editDesc, setEditDesc] = useState(todo.description || '');
  const [editDueDate, setEditDueDate] = useState(todo.dueDate || '');

  const sanitize = (str) => {
    const map = {
      '&': '&amp;',
      '<': '&lt;',
      '>': '&gt;',
      '"': '&quot;',
      "'": '&#39;',
    };
    return str.replace(/[&<>"']/g, (ch) => map[ch]);
  };

  const handleToggleComplete = () => {
    updateTodo(todo.id, { completed: !todo.completed });
  };

  const handleEditSubmit = (e) => {
    e.preventDefault();

    const cleanTitle = sanitize(editTitle.trim());
    const cleanDesc = sanitize(editDesc.trim());

    if (!cleanTitle) return;

    updateTodo(todo.id, {
      title: cleanTitle,
      description: cleanDesc,
      dueDate: editDueDate,
    });

    setIsEditing(false);
  };

  return (
    <li className={`todo-item ${todo.completed ? 'completed' : ''}`}>
      {!isEditing ? (
        <>
          <div className="todo-content">
            <label>
              <input
                type="checkbox"
                checked={todo.completed}
                onChange={handleToggleComplete}
              />
              <span className="todo-title">{todo.title}</span>
            </label>
            {todo.description && (
              <p className="todo-description">{todo.description}</p>
            )}
            {todo.dueDate && (
              <p className = "todo-due-date">Due: {todo.dueDate}</p>
            )}
          </div>
          <div className="todo-actions">
            <button onClick={() => setIsEditing(true)}>Edit</button>
            <button onClick={() => deleteTodo(todo.id)}>Delete</button>
          </div>
        </>
      ) : (
        <form className="todo-edit-form" onSubmit={handleEditSubmit}>
          <input
            type="text"
            value={editTitle}
            onChange={(e) => setEditTitle(e.target.value)}
            required
          />
          <textarea
            value={editDesc}
            onChange={(e) => setEditDesc(e.target.value)}
            placeholder="Edit description if needed"
            rows={2}
          />
          <input
          type = "date"
          value = {editDueDate}
          onChange = {(e) => setEditDueDate(e.target.value)}
          />

          <div className="todo-edit-actions">
            <button type="submit">Save</button>
            <button type="button" onClick={() => setIsEditing(false)}>
              Cancel
            </button>
          </div>
        </form>
      )}
    </li>
  );
}


