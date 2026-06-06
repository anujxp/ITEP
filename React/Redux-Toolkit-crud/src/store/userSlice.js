import { createSlice } from "@reduxjs/toolkit";

const intitialState = {
    userArray : [],
    user: {},
    index = -1
}

const userSlice  = createSlice({
    name : 'userSlice',
    initialState,
    reducers:{
        addUser : (state,action) =>{
            state.userArray.push(action.payload);
        },
        deleteUser: (state,action) => {state.userArray.splice(action.payload,1)},
        updateUser : (state,action) => {
             console.log("-------------> ",action.payload);
            state.userObj = action.payload.user;
            state.index = action.payload.index;
        }
    }
})

export const {addUser,deleteUser,updateUser} = userSlice.actions;
export default userSlice.reducer;
 