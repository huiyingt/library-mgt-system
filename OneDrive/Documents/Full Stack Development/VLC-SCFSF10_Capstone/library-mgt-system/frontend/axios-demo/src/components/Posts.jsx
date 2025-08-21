import axios from 'axios'
import {useEffect, useState} from 'react'


export function Posts(){
    const [posts, setPosts] = useState([])
   
    useEffect(() => {
        fetchData()
    },[])


    async function fetchData(){
        const url = 'https://jsonplaceholder.typicode.com/posts';
        //res short for response
        const response = await axios.get(url)
        setPosts(response.data)
    }


    return(
        <>
        <h1>Available Posts</h1>
        <ul>
            {
                posts.map(post => (
                    <li key = {post.id}>{post.title}</li>
                ))
            }
        </ul>
        </>
    )
}
