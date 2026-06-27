import 'dotenv/config'

import { app } from "./src/app.js";
import connectDB from '../../ProjectPost/backend/src/db/db.js';


connectDB();

app.listen(3000)