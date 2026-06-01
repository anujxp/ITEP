import React, { useReducer } from 'react'

const ReducerCounter = () => {
    let initialvalue = 0;

    function reducer(state,action){
        switch (action.type){
            case 'increment' : return state +=1;
            case 'decrement' : return state-=1;
            case 'reset' : return state = 0;
            default : return state;
        }
    }
    const [count,dispatch] = useReducer(reducer,initialvalue);

  return (
    <>
              <h1 className="text-4xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-cyan-400 to-blue-500 mb-4">
{count}</h1>
      <button 
        className="bg-blue-600 text-white px-4 py-2 mr-2 rounded"
        onClick={() => dispatch({ type: 'increment' })}
      >
        Increment
      </button>
    </>
  )
}

export default ReducerCounter
