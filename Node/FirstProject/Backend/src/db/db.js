// src/db/db.js
import mongoose from "mongoose";

const connectDB = async () => {
    try {
        console.log("Attempting to connect to:", process.env.MONGODB_URI); // DEBUGGING LINE
        const connectionInstance = await mongoose.connect(process.env.MONGODB_URI);

        console.log(`\n MongoDB Connected! Database Host: ${connectionInstance.connection.host}`);
    } catch (error) {
        console.error("MongoDB Connection FAILED: ", error);
        process.exit(1); 
    }
};

export default connectDB;