// server.js
import dotenv from "dotenv";
import connectDB from "./src/db/db.js";
import { app } from "./src/app.js"; // This is your imported app

dotenv.config({
    path: "./.env"
});

connectDB()
    .then(() => {
        console.log("DB connected");
        // Start listening using the imported app
        app.listen(process.env.PORT || 3000, () => {
            console.log(`Server is running at port : ${process.env.PORT || 3000}`);
        });
    })
    .catch((err) => {
        console.log("MongoDB connection failed! ", err);
    });

