import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { increment, decrement,reset } from '../store/counterSlice' // Import your actions

const Counter = () => {
  
  const count = useSelector((state) => state.counter.value)

  const dispatch = useDispatch()
  
  return (
    <div id="counterCard" className="p-4 border border-zinc-500 rounded-lg text-center">
       <h1 className="text-2xl font-bold mb-4">{count}</h1>
       
       {/* 3. Dispatch the actions when buttons are clicked */}
       <button 
         className="bg-green-500 text-white px-4 py-2 mr-2 rounded"
         onClick={() => dispatch(increment())}
       >
         Increase
       </button>
       
       <button 
         className="bg-red-500 text-white px-4 py-2 rounded"
         onClick={() => dispatch(decrement())}
       >
         Decrease
       </button>
       <button 
         className="bg-blue-500 text-white px-4 py-2 rounded ml-2"
         onClick={() => dispatch(reset())}
       >
         Reset
       </button>
    </div>
  )
}

export default Counter