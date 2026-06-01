import { createSlice } from '@reduxjs/toolkit'

export const counterSlice = createSlice({
  name: 'counter',       // 1. Name of the slice
  initialState: {        // 2. The initial data
    value: 0
  },
  reducers: {            // 3. The functions that change the data
    increment: (state) => {
      state.value += 1 
    },
    decrement: (state) => {
      if (state.value > 0) {
        state.value -= 1
      }
    }
  }
})

// Export the Actions (so your buttons can use them)
export const { increment, decrement } = counterSlice.actions

// Export the Reducer (so the Store can use it)
export default counterSlice.reducer