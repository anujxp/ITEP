import React from 'react'

export default function Square({ value, onClick }) {
  // Dynamic glow styling based on whose turn it is
  const colorClass = value === 'X' 
    ? 'text-cyan-400 drop-shadow-[0_0_10px_rgba(34,211,238,0.8)]' 
    : 'text-fuchsia-500 drop-shadow-[0_0_10px_rgba(217,70,239,0.8)]';

  return (
    <button
      onClick={onClick}
      className={`h-24 w-24 sm:h-32 sm:w-32 bg-zinc-900 border border-zinc-700 rounded-xl text-5xl sm:text-7xl font-black flex items-center justify-center transition-all duration-200 hover:bg-zinc-800 hover:border-cyan-500/50 hover:shadow-[0_0_15px_rgba(34,211,238,0.3)] ${colorClass}`}
    >
      {value}
    </button>
  )
}