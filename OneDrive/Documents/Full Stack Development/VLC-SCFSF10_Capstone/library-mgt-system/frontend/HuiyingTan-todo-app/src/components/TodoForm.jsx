import React, { useState } from 'react';
import '../styles/TodoForm.css';

export function TodoForm({ addTodo }) {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [dueDate, setDueDate] = useState('');

  const sanitize = (str) => {
    const map = { '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' };
    return str.replace(/[&<>"']/g, (ch) => map[ch]);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const cleanTitle = sanitize(title.trim());
    const cleanDescription = sanitize(description.trim());
    const cleanDueDate = sanitize(dueDate);

    if (!cleanTitle) return;

    addTodo({
      id: Date.now(),
      title: cleanTitle,
      description: cleanDescription,
      dueDate: cleanDueDate || null,
      completed: false,
    });

    // reset
    setTitle('');
    setDescription('');
    setDueDate('');
  };

  return (
    <form className="todo-form" onSubmit={handleSubmit}>
      <div className="form-field">
        <label htmlFor="title">Title: *</label>
        <input
          id="title"
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="e.g. Clear debug list"
          required
        />
      </div>

      <div className="form-field">
        <label htmlFor="description">Description: </label>
        <textarea
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          placeholder="Input details (optional)"
          rows={3}
        />
      </div>

      <div className="form-field">
        <label htmlFor="dueDate">Due date: </label>
        <input
          id="dueDate"
          type="date"
          value={dueDate}
          onChange={(e) => setDueDate(e.target.value)}
        />
      </div>

      <button type="submit" className="btn-submit">Add To-do</button>
    </form>
  );
}

