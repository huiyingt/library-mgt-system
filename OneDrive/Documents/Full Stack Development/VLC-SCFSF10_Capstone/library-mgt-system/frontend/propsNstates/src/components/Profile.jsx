//must use {} to access the prop value
//props is like a container
export function Profile(props) {
  return <h2>Hi, {props.fname} with {props.exp} years of experience from {props.industry}!</h2>
};
