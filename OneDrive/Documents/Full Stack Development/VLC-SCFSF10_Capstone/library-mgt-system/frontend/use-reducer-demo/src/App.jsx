
import './App.css'
import { ReducerCounter } from './components/reducercount'

function App() {
  

  return (
    <>
      <div className="card">
      <ReducerCounter/>
      </div>
    </>
  )
}

export default App

/**
 * Logic behind state
 * 1. Place a button on the screen with caption '+1'
 * 2. Listen to the click event: onClick
 * 3. Write the function to execute when button is clicked: function xx
 * 4. Call the function to change the counter -> onClick = {function}
 * 
 * Small part of e-commerce application / shopping cart module
 * 1. Quantity should adjust according to the buttons
 * 2. Amount should be calculated per item
 * 3. Apply rules like discounts
 * 
 * DOM
 * 
 */