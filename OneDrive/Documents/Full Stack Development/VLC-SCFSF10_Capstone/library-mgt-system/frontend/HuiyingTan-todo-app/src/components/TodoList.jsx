import React from 'react';
import { TodoItem } from './TodoItem';
import '../styles/TodoList.css';

export function TodoList({ todos = [], updateTodo, deleteTodo }) {
  if (!todos.length) {
    return <p className="todo-list-empty">No to-do yet. Add one!</p>;
  }

  return (
    <ul className="todo-list">
      <h2 className="todo-list-title">To-do List</h2>
      {todos.map((todo) => (
        <TodoItem
          key={todo.id}
          todo={todo}
          updateTodo={updateTodo}
          deleteTodo={deleteTodo}
        />
      ))}
    </ul>
  );
}


