import Counter from './Components/Counter'
import './App.css'
import { useState } from 'react'

function App() {
  const [temp, setTemp] = useState(5)

  return (
    <>
  <Counter temp = {setTemp}/>
  <h1>this is from the h1 on app component {temp}</h1>
    </>
  )
}

export default App
