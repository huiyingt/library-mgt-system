import React from 'react'

//const buttonProps = {
//    caption: String,
//    onClick: () => void 
//}

export const Button = ({caption, onClick}) => {
    return <button onClick = {onClick}>{caption}</button> 
}