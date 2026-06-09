const express = require('express')
const app = express();
const PORT = 3000;

const notesRouter = require('./Notes/notesController.js');
app.use(express.json())
app.use('/notes', notesRouter);


app.listen(PORT,() => {
    console.log("server is started on port number ",PORT);
});

