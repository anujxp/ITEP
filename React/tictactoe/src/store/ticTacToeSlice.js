import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  board: Array(9).fill(null),
  xIsNext: true,
  winner: null,
};

function calculateWinner(squares) {
  const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8], // Rows
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8], // Columns
    [0, 4, 8],
    [2, 4, 6], // Diagonals
  ];

  for (let i = 0; i < lines.length; i++) {
    const [a, b, c] = lines[i];
    // If the square at 'a' exists, and it matches 'b' and 'c', we have a winner!
    if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
      return squares[a]; // Returns 'X' or 'O'
    }
  }
  return null;
}

export const ticTacToeSlice = createSlice({
  name: "tiTacToe",
  initialState,
  reducers: {
    makeMove: (state, action) => {
      const index = action.payload;

      if (state.board[index] || state.winner) {
        return;
      }
      state.board[index] = state.xIsNext ? "X" : "O";

      const winningPlayer = calculateWinner(state.board);

      if (winningPlayer) {
        state.winner = winningPlayer;
      } else if (!state.board.includes(null)) {
        // 3. If there is no winner, but the board is full (no nulls left), it's a draw
        state.winner = "Draw";
      } else {
        // 4. If the game is still going, switch turns
        state.xIsNext = !state.xIsNext;
      }
    },
    resetGame: (state) => {
      // Immer lets us simply overwrite the state to reset it
      state.board = Array(9).fill(null);
      state.xIsNext = true;
      state.winner = null;
    },
  },
}
);

export const {makeMove,resetGame} = ticTacToeSlice.actions

export default ticTacToeSlice.reducer