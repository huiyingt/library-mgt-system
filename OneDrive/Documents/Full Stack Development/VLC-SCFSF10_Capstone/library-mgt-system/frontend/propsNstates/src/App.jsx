import './App.css';
import {Books} from './components/Books';
//passing the name as a prop to the Profile component
//props are used to pass data from parent to child components in React
//props are read-only and should not be modified by the child component
//use {} for numbers
function App() {
return (
  <>
  {/*<Books isbn = '000001' title = 'Mindset' author = 'Carol Dweck'/><br></br>
  <Books isbn = '000002' title = 'Secret Seven' author = 'Enid Blyton'/><br></br>
  <Books isbn = '000003' title = 'Start with Why' author = 'Simon Sinek'/><br></br>*/}

  <Post tags = {[`tech`,`travel`,`food`]} /><br></br>
  <UserPost user = {{name: 'Alice', followers: 3000, city: 'New York'}} /> 
  </>
)
}

export default App;

function UserPost({user}) {
  return (
    <>
    <li>User Name: {user.name}</li>
    <li>Followers: {user.followers}</li>
    <li>City: {user.city}</li>
    </>
  )
} 

function Post({tags}){
  return(
    <>
    <h2>Tags:</h2>
    <ul>
    {
    tags.map((tags,index) =>  //sort through the tags array and return each tag
      <li key ={index}>{tags}</li>
  )}
    </ul>
    </>
  )
}


//must use {} to access the prop value
//props is like a container
//copy and paste this into Profile component so it is reusable
//function Profile(props) {
//  return <h2>Hi, {props.fname} with {props.exp} years of experience from {props.industry}!</h2>
//};



//examples of how to use props in different ways
/**
 * function Profile({fname, industry, exp}) {
  return <h1>Hi, {fname} with {exp} years of experience from {industry}!</h1>
};
 */

/**
 * function Profile() {
  const name = 'Alice';
  return <h1>Hi, {name}!</h1>
};
 */

/**
 * function Profile() {
 *    return <h1>Hi, Alex!</h1>
 * }
 */


