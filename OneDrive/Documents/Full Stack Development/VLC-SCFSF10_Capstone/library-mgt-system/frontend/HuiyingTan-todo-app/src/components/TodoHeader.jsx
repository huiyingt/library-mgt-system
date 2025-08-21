import React from 'react';
import '../styles/TodoHeader.css'

export function TodoHeader() {

    const today = new Date().toLocaleDateString(undefined,{
        day: 'numeric',
        month: 'short',
        year: 'numeric',
    })

  return (
    <header className="todo-header">
      <h1 className = "app-title">Our Task Ninja</h1>
      <p className = "app-subtitle">Let's stay organised and focused!</p>
      <p className = "app-date">Date: {today}</p>
    </header>
  )
}

