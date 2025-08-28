import axios from 'axios'

const BASE_URL = 'http://localhost:3000/todos'

export const getTodos = async() => {
    const res = await axios.get(BASE_URL)
    return res.data
}

export const addTodo = async (task) => {
    const res = await axios.post(BASE_URL, {task, done:false})
    return res.data
}

export const updateTodo = async (id, updatedTodo) =>{
    const res = await axios.put(`${BASE_URL}/${id}`, updatedTodo)
    return res.data
}

export const deleteTodo = async (id) =>{
    const res = await axios.delete(`${BASE_URL}/${id}`)
    return res.data
}