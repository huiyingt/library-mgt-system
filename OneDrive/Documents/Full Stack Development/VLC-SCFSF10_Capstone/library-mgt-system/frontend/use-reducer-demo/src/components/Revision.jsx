import React, {useState} from 'react'

function Calculator(){}

//arrow function
const Calculator1 = () => {}

//class component
class Calculators extends React.Component {
    a = 0; b = 0;
    sum (a, b){
        return a + b
    }

    render() {
        return (
            <>
            <h1>Sum of 5 and 3 is {this.sum(5,3)}</h1>
            </>
        )
    }
}

//functional component

export function Calculator2 () {
    function sum (a, b) {
        return a + b
}

return (
    <h1>Sum of 5 and 3 is {sum(5,3)}</h1>
)
}

/**
 * create function component for Book and then Author
 */

export function Book(){
    return (
        <>
        <h2>Book ID: 12345</h2>
        <h2>Book Title: Mindset</h2>
        </>
        )
}

export function Books ({bookid, bookname}){
    return(
        <>
        <h2>Book ID: {bookid}</h2>
        <h2>Book Title: {bookname}</h2>
        </>
    )
}

export function Books1 ({props}){
    return(
        <>
        <h2>Book ID: {props.bookid}</h2>
        <h2>Book Title: {props.bookname}</h2>
        </>
    )
}

export function Books2() {
   const [title, setTitle] = useState('')
   const [author, setAuthor] = useState('')

   function Reset(){
    setTitle('')
    setAuthor('')
   }
    return (<>
        <h2>Book Title: {title}</h2>
        <h2>Written by: {author}</h2>
        <button onClick = {Reset}>Reset</button>
    </>)
}


//events
function Login(){
    function showMessage(){
        return `button clicked!`
    }

    return(
        <>
        <button onClick = {showMessage}>Hi</button>
        </>
    )
}