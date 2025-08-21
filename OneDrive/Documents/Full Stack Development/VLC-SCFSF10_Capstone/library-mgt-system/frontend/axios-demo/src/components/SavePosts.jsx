import axios from 'axios'
import {useEffect, useState} from 'react'

export function SavePost(){
   
    const [posts, setPost] = useState([])

    useEffect(() => {
        fetchData()
    },[])

    async function fetchData(){
        const url = 'https://jsonplaceholder.typicode.com/posts';

        const dummy = {
            userId: 111,
            id: 111,
            title: 'dummy data',
            body: 'this is done using axios and react with dummy data.'          
        }

        const response = await axios.post(url, dummy)
        console.log(response.data)
        setPost(response.data)
    }

    return (
        <div>
            <h2>Post Response</h2>
            <pre>{JSON.stringify(posts, null, 2)}</pre>
        </div>
    );
}