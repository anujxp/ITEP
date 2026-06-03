import { configureStore } from "@reduxjs/toolkit";
import ticTacToeReducer from "./ticTacToeSlice";

export const store =  configureStore({
    reducer : {
        ticTacToe: ticTacToeReducer
    }
})