import { useState } from 'react';

export function CreateUser(){
        const [username, setUsername] = useState('')
        const [password, setPassword] = useState('')
        const [msg, setMsg] = useState('')

        function handleUsernameChange(event){
            setUsername(event.target.value)
        }

        function handlePasswordChange(event) {
            setPassword(event.target.value)
        }

        function handleSubmit(event){
            event.preventDefault()
        
            if (username.trim() === 'admin' && password.length >= 8) {
                setMsg ('Welcome, ' + username)
            } else {
                setMsg('Invalid username or password. Password must be at least 8 characters long.')
            }
        }

        function handleClear() {
            setUsername('');
            setPassword('');
            setMsg('');
        }

        return(
            <>
            <form onSubmit ={handleSubmit}>
                Username: {''}
                <input 
                type="text" 
                placeholder="Enter your name" 
                value = {username} 
                onChange={handleUsernameChange}/>
                <br/><br/>
                Password: {''} 
                <input 
                type="text" 
                placeholder="Enter your password" 
                value ={password} 
                onChange={handlePasswordChange}/>
                <br/><br/>
                <button type='Submit'>Submit</button>
                <button type='Reset'onClick={handleClear}>Reset</button>
            </form>
            <h2>{msg}</h2>
            </>
        )
    }