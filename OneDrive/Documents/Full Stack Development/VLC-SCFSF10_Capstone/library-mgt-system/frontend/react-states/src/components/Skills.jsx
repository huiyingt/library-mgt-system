import { useState } from 'react'

export function Skills(){
const [skills, setSkills] = useState([]) //collecting skills into an array

const handleSkills = (event) => {
    console.log(event.target.value, event.target.checked)

    if (event.target.checked) {
        setSkills([...skills, event.target.value])
    } else{
        setSkills(skills.filter((item)=> item!==event.target.value))
    }
}

//const showSkills = () => {
  //  setSkills([...skills])
//}

    return(

    <div>
        <h2>Select your skills</h2>

        <input type = "checkbox" id="php" value="PHP" onChange={handleSkills}/>
        <label htmlFor="php">PHP</label>
        <br/><br/>
        <input type = "checkbox" id="js" value="JavaScript" onChange={handleSkills}/>
        <label htmlFor="js">JavaScript</label>
        <br/><br/>        
        <input type = "checkbox" id="node" value="Node" onChange={handleSkills}/>
        <label htmlFor="node">Node</label>
        <br/><br/>
        <input type = "checkbox" id="java" value="Java" onChange={handleSkills}/>
        <label htmlFor="java">Java</label>
        <br/><br/>
        <h3>Selected Skills: {skills.join(', ') || 'Selected skills shown here' }</h3>
    </div>
    )
}
