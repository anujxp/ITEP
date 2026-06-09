import express from 'express';
import cors from 'cors'; // Import CORS
import noteRoutes from './routes/notes.route.js';

const app = express();
app.use(cors({
    origin: process.env.cors, // Vite's default port
    credentials: true
}));
app.use(express.json());

// Routes "mounted" here
app.use("/api/v1/notes", noteRoutes);

export { app };