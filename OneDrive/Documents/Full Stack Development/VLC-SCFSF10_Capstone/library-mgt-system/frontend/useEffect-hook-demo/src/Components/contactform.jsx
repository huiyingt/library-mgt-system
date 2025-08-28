import { useEffect, useState } from 'react'

export function ContactForm() {
  const [name, setName] = useState('');
  const [message, setMessage] = useState('');
  const [isSubmitted, setIsSubmitted] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    // simulate API call or submission
    console.log("Submitted:", { name, message });

    setIsSubmitted(true); // trigger useEffect
  };

  useEffect(() => {
    if (isSubmitted) {
      // clear the form
      setName('');
      setMessage('');
      // reset submission flag
      setIsSubmitted(false);
    }
  }, [isSubmitted]); // runs only when isSubmitted changes

return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Your Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      /><br/><br/>
      <textarea
        placeholder="Your Message"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
      ></textarea><br/><br/>
      <button type="submit">Send</button>
    </form>
  );
}

