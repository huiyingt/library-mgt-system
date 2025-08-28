import {FetchData} from './Components/fetchdata.jsx'
import './App.css'


function App() {
  return(
  <>
  <FetchData/>
  </>
  )
}

export default App

/**
 * 1. State change re-render the component
 * 2. it will execute all other functions (if there are any) along with state
 * 3. May cause infinite loop, costly operation
 */