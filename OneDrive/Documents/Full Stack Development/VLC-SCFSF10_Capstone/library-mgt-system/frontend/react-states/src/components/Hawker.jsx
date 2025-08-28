import { useState } from 'react'

export function HawkerDish(){
const [dish, setDish] = useState('');
const [hawkerDishes, setHawkerDishes] = useState([]); //collecting skills into an array

const handleAddDish = () => {
    const trimmedDish = dish.trim();
    if (trimmedDish === '') return; //prevent empty submission
    setHawkerDishes ([...hawkerDishes, trimmedDish]);
    setDish(''); //clear input field
}

    return(
    <div>
        <h2>Enter your favourite hawker dishes</h2>

        <input 
        type = "text" 
        placeholder = "e.g. Chicken Rice" 
        value={dish} 
        onChange={(e)=> setDish(e.target.value)}
        /><br/><br/>
        <button onClick = {handleAddDish}>Add</button>
        <br/><br/>        
        <h3>Your Favourite Hawker Dishes:</h3>
        {hawkerDishes.length === 0 ? (
            <p>Your favourite hawker dishes are shown here</p>
        ) : (
            <ul>
                {hawkerDishes.map((item,index) => (
                    <li key = {index}>{item}</li>
                ))}
            </ul>
        )}
    </div>
    );
}