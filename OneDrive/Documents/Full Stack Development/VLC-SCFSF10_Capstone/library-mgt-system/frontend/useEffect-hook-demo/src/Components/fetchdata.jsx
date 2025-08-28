import {useEffect} from 'react'

function FetchData() {

    //call the getData() only once as there is no dependency
    useEffect (()  => {
        getData()
    },[])
    console.log(`Component called`)

    async function getData() {
        console.log(`fetching data...`)
        const url = "https://www.themealdb.com/api/json/v1/1/categories.php"
        //const url = "https://jsonplaceholder.typicode.com/posts";
        try {
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(`Response status: ${response.status}`);
            }

            const json = await response.json();
            console.log(json);
        } catch (error) {
            console.error(error.message);
        }
    }
    
}

export {FetchData}

/**
 * https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch
 * https://jsonplaceholder.typicode.com/posts
 */