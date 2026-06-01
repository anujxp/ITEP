import React from 'react'
import { useState } from 'react'

const Counter = ({temp}) => {
  const [count, setCount] = useState(0)
    const increase = () => {
        if(count<20){
          temp(count -1)

            setCount(count +1)
        }
    }
    const decrease = () => {
        if(count>0){
      setCount(count -1)
        }
    }
  
    return (
      <>
      <div id = 'counterCard'>
       <h1 >{count}</h1>
       <button onClick={increase} className='bg-grey-900'>{count} </button>
       <button onClick={decrease}>decrease {count}</button>
       </div>
      </>
    )
}

export default Counter
