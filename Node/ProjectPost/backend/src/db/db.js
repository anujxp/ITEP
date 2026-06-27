import mongoose from "mongoose";
import { log } from "node:console";


async function connectDB(){
    try{
        log("Attempting to connect on ",process.env.MONGODB_URI);
        const connectionInstance = await mongoose.connect(process.env.MONGODB_URI);
        log(`MOngo Db connected successfully${connectionInstance.connection.host}`)
        log("helklos")
    }catch(err){
        log(err);
        process.exit(1);
    }
}


export default connectDB;