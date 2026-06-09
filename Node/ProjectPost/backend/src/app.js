import express from 'express'
import postRouter from './routes/posts.route.js'
import cors from 'cors'
const app = express();

app.use(cors({
    origin: 'http://localhost:5173'
}))
app.use(express.json())
app.use('/api/v1/post',postRouter);

export { app };