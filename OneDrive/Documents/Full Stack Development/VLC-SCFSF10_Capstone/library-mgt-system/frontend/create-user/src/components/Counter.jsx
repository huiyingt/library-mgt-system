import React, { useState, useEffect, useRef } from 'react';

export function Timer() {
  const [count, setCount] = useState(0);
  const [isRunning, setIsRunning] = useState(false);
  const timerRef = useRef(null);

  useEffect(() => {
    if (isRunning) {
        timerRef.current = setInterval(() => {
      setCount(prevCount => prevCount + 1);
    }, 1000);
}
    return () => clearInterval(timerRef.current); 
  }, [isRunning]); 

  function handleStart() {
    setIsRunning(true);
  }

  function handleStop() {
    setIsRunning(false);
    clearInterval(timerRef.current)
  }

  function handleReset() {
    setIsRunning(false);
    clearInterval(timerRef.current)
    setCount(0)
  }

  return (
  <div style={{ display: 'flex', gap: '10px' }}>
  <h1>Count: {count}</h1>
  <br/>
  <button onClick = {handleStart}>Start</button>
  <button onClick = {handleStop}>Stop</button>
  <button onClick = {handleReset}>Reset</button>
  </div>
  );
}
