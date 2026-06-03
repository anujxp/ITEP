import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { makeMove, resetGame } from '../store/ticTacToeSlice'
import Square from './Square'

export default function Board() {
  // 1. Snipers: Grab exactly what we need from Redux
  const { board, xIsNext, winner } = useSelector((state) => state.ticTacToe)
  const dispatch = useDispatch()

  // 2. Determine what the status text should say
  let statusText;
  if (winner === 'Draw') {
    statusText = "SYSTEM DRAW";
  } else if (winner) {
    statusText = `WINNER: ${winner}`;
  } else {
    statusText = `NEXT MOVE: ${xIsNext ? 'X' : 'O'}`;
  }

  // Dynamic status color
  const statusColor = winner ? 'text-green-400 drop-shadow-[0_0_8px_rgba(74,222,128,0.8)]' : 'text-zinc-400';

  return (
    <div className="flex flex-col items-center">
      
      {/* The Status Readout */}
      <div className={`mb-8 text-2xl font-black tracking-[0.2em] ${statusColor}`}>
        {statusText}
      </div>

      {/* The 3x3 Grid */}
      <div className="grid grid-cols-3 gap-3 sm:gap-4 p-4 bg-zinc-950 border border-cyan-500/30 rounded-2xl shadow-[0_0_30px_-5px_rgba(34,211,238,0.2)]">
        {board.map((squareValue, index) => (
          <Square 
            key={index} 
            value={squareValue} 
            // 3. Walkie-Talkie: Tell Redux which index was clicked
            onClick={() => dispatch(makeMove(index))} 
          />
        ))}
      </div>

      {/* Reboot Button */}
      <button 
        onClick={() => dispatch(resetGame())}
        className="mt-12 px-8 py-3 bg-zinc-900 border border-fuchsia-500 text-fuchsia-400 rounded-full font-bold tracking-widest hover:bg-fuchsia-900/20 hover:shadow-[0_0_20px_rgba(217,70,239,0.5)] transition-all"
      >
        REBOOT SYSTEM
      </button>

    </div>
  )
}