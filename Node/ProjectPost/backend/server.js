import 'dotenv/config';
// import dotenv from 'dotenv'
import { app } from "./src/app.js";

import connectDB from "./src/db/db.js";
import { log } from "node:console";

// dotenv.config({
//     path : './.env'
// });

connectDB().then(()=>{
 console.log("DB connected");
        // Start listening using the imported app
        try{
        app.listen(process.env.PORT || 3000, () => {
            console.log(`Server is running at port : ${process.env.PORT || 3000}`);
        });
    }catch(err){
        log(err);
    }
})
  
