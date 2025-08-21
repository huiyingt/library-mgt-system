import {useEffect, useState} from 'react'
import axios from 'axios'

export function EmpData(){
    const [emps, setEmployees] = useState([])
   
    useEffect(() => {
        fetchEmp()
    },[])

    async function fetchEmp(){
        const url = 'http://localhost:8080/api/employees/all';
        //res short for response
        const response = await axios.get(url)
        console.log(response.data)
        setEmployees(response.data)
    }

    return (
    <>
      <h2>Employee Details</h2>
      {emps.map(emps => (
        <div>
          <div>ID: {emps.id}</div>
          <div>Name: {emps.name}</div>
          <div>Email: {emps.email}</div>
          <div>Department: {emps.department}</div>
        <br/>
        </div>
      ))} 
    </>
  )
}