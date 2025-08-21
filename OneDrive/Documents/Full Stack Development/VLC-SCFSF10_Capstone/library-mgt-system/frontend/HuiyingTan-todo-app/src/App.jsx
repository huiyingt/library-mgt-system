import React, { useEffect, useState } from 'react'
import { TodoHeader } from './components/TodoHeader'
import { TodoForm } from './components/TodoForm'
import { TodoList } from './components/TodoList'
import { TodoFilter } from './components/TodoFilter'
import { TodoStats } from './components/TodoStats'
import './App.css'

/* -------------------------
   Mock “in-memory” database
-------------------------- */
let __DB__ = [
  { id: 1, title: 'Learn React', description: 'Hooks, Context', completed: false, dueDate: '' },
  { id: 2, title: 'Build Todo App', description: '', completed: true, dueDate: '' }
];

const delay = (ms = 300) => new Promise((res) => setTimeout(res, ms));
const clone = (v) => JSON.parse(JSON.stringify(v));

/* ---------------------------------
   Mock API (async, returns Promises)
---------------------------------- */
async function getTodos() {
  await delay();
  return clone(__DB__);
}

async function addTodo(todo) {
  await delay();
  const newTodo = { ...todo, id: Date.now() };
  __DB__ = [newTodo, ...__DB__];
  return clone(newTodo);
}

async function updateTodo(id, changes) {
  await delay();
  __DB__ = __DB__.map((t) => (t.id === id ? { ...t, ...changes } : t));
  return clone(__DB__.find((t) => t.id === id));
}

async function deleteTodo(id) {
  await delay();
  __DB__ = __DB__.filter((t) => t.id !== id);
  return true;
}

/* -------------
   The App root
-------------- */
function App() {
  const [todos, setTodos] = useState([]);
  const [filter, setFilter] = useState('All');
  const [loading, setLoading] = useState(true);

  // initial load
  useEffect(() => {
    (async () => {
      setLoading(true);
      const data = await getTodos();
      setTodos(data);
      setLoading(false);
    })();
  }, []);

  // handlers that wrap the mock API
  const handleAddTodo = async (todo) => {
    const created = await addTodo(todo);
    setTodos((prev) => [created, ...prev]);
  };

  const handleUpdateTodo = async (id, changes) => {
    const updated = await updateTodo(id, changes);
    setTodos((prev) => prev.map((t) => (t.id === id ? updated : t)));
  };

  const handleDeleteTodo = async (id) => {
    await deleteTodo(id);
    setTodos((prev) => prev.filter((t) => t.id !== id));
  };

  const filteredTodos = todos.filter((t) => {
    if (filter === 'Active') return !t.completed;
    if (filter === 'Completed') return t.completed;
    return true; // 'All'
  });

  return (
    <div className="app">
      <TodoHeader />

      <main className="app-container">
        <TodoStats todos={todos} />
        <TodoFilter currentFilter={filter} setFilter={setFilter} />
        <TodoForm addTodo={handleAddTodo} />

        {loading ? (
          <p className="loading">Loading…</p>
        ) : (
          <TodoList
            todos={filteredTodos}
            updateTodo={handleUpdateTodo}
            deleteTodo={handleDeleteTodo}
          />
        )}
      </main>
    </div>
  );
}

export default App;