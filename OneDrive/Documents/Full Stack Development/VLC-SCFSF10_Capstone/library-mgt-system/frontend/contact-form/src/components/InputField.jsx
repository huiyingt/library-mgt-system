

export function InputField({label, value, onChange, placeholder, type}){
    
    return(
        <div>
            <label>{label}</label>
            <input
            type = {type}
            value = {value}
            onChange = {onChange}
            placeholder = {placeholder}
            />
        </div>
    )
}