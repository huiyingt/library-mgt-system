export function Books(props){
    return (
        <div>
            <h2>ISBN: {props.isbn}</h2>
            <h2>Book Title: {props.title}</h2>
            <h2>Author: {props.author}</h2>
        </div>
    )
}