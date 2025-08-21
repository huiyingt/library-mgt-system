import axios from 'axios'
import {useState} from 'react'

export function SaveEmp(){
   
    const [emp, setEmp] = useState({
        name: '',
        email: '',
        department: '',
    });

    const [submittedEmp, setSubmittedEmp] = useState('');

    //handle input field changes
    function handleChange(e){
        const {name, value}= e.target;
        setEmp(prev => ({... prev, [name]: value}))
    }

    //handle form submission
    async function handleSubmit(e){
        e.preventDefault(); //prevent page reload
    
    //useEffect(() => {
    //    postEmp()
    //},[])

    const url = 'http://localhost:8080/api/employees/create'

        const response = await axios.post(url, emp)
        console.log(response.data)
        setSubmittedEmp(response.data)
        setEmp({name:'', email: '', department: ''})
    }

    return(
        <div>
        <h2>Add New Employee</h2>
        <form onSubmit = {handleSubmit}>
            <div>
                <label>Name: </label>
                <input 
                type = 'text'
                name = 'name' 
                value = {emp.name} 
                onChange = {handleChange} required />
            </div><br/>

            <div>
                <label>Email: </label>
                <input 
                type = 'email' 
                name = 'email'
                value = {emp.email} 
                onChange = {handleChange} required />
            </div><br/>

            <div>
                <label>Department: </label>
                <input 
                type = 'text' 
                name = 'department'
                value = {emp.department} 
                onChange = {handleChange} required />
            </div><br/>         

            <button type = 'submit'>Save Employee</button>
         
        </form>
        {submittedEmp && submittedEmp.name + ' added successfully!'}
        </div>
    )

    
}