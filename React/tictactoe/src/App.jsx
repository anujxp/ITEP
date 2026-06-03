import React from 'react'
import Board from './components/Board'
import Box from './components/Box'

export default function App() {
  return (
    <div className="min-h-screen bg-zinc-950 flex flex-col items-center justify-center p-4">
      {/* The Glowing Neon Title */}
      <h1 className="text-5xl md:text-7xl font-black text-transparent bg-clip-text bg-gradient-to-br from-cyan-400 to-fuchsia-600 drop-shadow-[0_0_15px_rgba(34,211,238,0.5)] mb-12">
        NEON TAC TOE
      </h1>

      {/* The Placeholder Board with a deep shadow and glowing border */}
      <div className="w-full max-w-md aspect-square grid grid-col-3bg-zinc-900 border border-cyan-500/30 rounded-2xl shadow-[0_0_30px_-5px_rgba(34,211,238,0.3)] flex items-center justify-center">
        {/* <p className="text-zinc-500 font-mono tracking-widest"></p> */}
       {/* {
        for(let i = 0; i  <9;i++){
      <Box/>
        }
       }  */}
{Array(9).fill(null).map((_, index) => (
  <Box key={index} />
))}

      </div>
    </div>
  )
}