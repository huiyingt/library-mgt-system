import React, { useEffect, useState } from 'react'
import {getTodos, addTodo, updateTodo, deleteTodo} from './api.js'
import './App.css'

function App() {
  const [taskInput, setTaskInput] = useState('')
  const [todos, setTodos] = useState([])
  const [editId, setEditId] = useState(null)

  //load todos
  const loadTodos = async() => {
    const data = await getTodos()
    setTodos(data)
  }

  useEffect(() => {
    loadTodos()
  },[])
  
  //Add Task
  const handleSave = async () => {
    if(!taskInput.trim()) 
      return
    
    if(editId){
      const todo =todos.find(t => t.id === editId)
      await updateTodo(editId,{...todo, task:taskInput})  //wait for update to complete
      setEditId(null)
    } else {
      await addTodo(taskInput)  //API call to add the task
    }

    setTaskInput('')
    loadTodos() // then refresh list
  }

  //Mark as Done
  const toggleDone = async (todo) => {
    await updateTodo(todo.id, {...todo, done: !todo.done})
    loadTodos()

  }

  //Edit
  const handleEdit = (todo) => {
    setTaskInput(todo.task)
    setEditId(todo.id)
  }

  //Delete
  const handleDelete = async (id) => {
    await deleteTodo(id)
    loadTodos()
  }

    return (
    <>
    <div style ={{padding: '2rem'}}>
      <h2>To Do List</h2>
      
      <ul>
        {todos.map(todo => (
          <li key = {todo.id} style = {{marginBottom: '0.5rem'}}>
            <span
            onClick = {() => toggleDone(todo)}
            style = {{
              textDecoration: todo.done ? 'line-through' : 'none',
              cursor: 'pointer'
            }}>{todo.task}
            </span>
            <button onClick ={() => handleEdit(todo)}style ={{marginLeft:10}}>Edit</button>
            <button onClick ={() => handleDelete(todo.id)}style={{marginLeft:5}}>Remove</button>
          </li>
        ))}
      </ul><br/>

      <h3>Add New Task</h3>
      <input
      type = "text"
      name = "task"
      value = {taskInput}
      onChange = {(e) => setTaskInput(e.target.value)} 
      placeholder='e.g. Pay HDB Rent'/><br/><br/>
      <button onClick={handleSave}>{editId? 'Update': 'Add'}</button> 
      
    </div>
    </>
  )
}

export default App
