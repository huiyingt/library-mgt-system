import { useState } from 'react';

export function UserReg(){
        const [text, setText] = useState('')
        const [msg, setMsg] = useState('')

        function handleChange(event){
            setText(event.target.value)
        }

        function handleSubmit(event){
            event.preventDefault()
            setMsg('Button clicked!')
            alert('Form submitted!')
        }

        return(
            <>
            <form onSubmit ={handleSubmit}>
                Name: <input type="text" placeholder="Enter your name" value = {text} onChange={handleChange}/>
                <br/><br/>
                <button type='Submit'>Submit</button>
            </form>
            <h2>{msg}</h2>
            </>
        )
    }
