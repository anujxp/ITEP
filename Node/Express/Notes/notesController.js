const express = require("express");
const router = express.Router();

const notes = [];

router.post("/", (req, res) => {
  notes.push(req.body);
  console.log(notes);
  
  res.status(201).json({
    "message": "notes created successfully",
    "number" : 1,
    "data" : req.body,
    "notes" : notes
  });
});

router.get('/',(req,res)=>{
    res.status(200).json({
    message: "notes created successfully",
    data : notes
})
})


router.delete('/:index',(req,res)=>{
    const index = parseInt(req.params.index,10)
    if(isNaN(index) || index<0||index>notes.length){
        res.status(404).json({
            message: "notes not found ",
        })
    }
    notes.splice(index, 1);
    res.sendStatus(204).json({});

})

router.put('/:index',(req, res)=>{
    const index = parseInt(req.params.index,10);
    if(isNaN(index) || index<0||index>notes.length){
        res.status(404).json({
            message: "notes not found ",
        })
    }

    notes[index] = req.body;
    res.status(200).json({ 
        message: "Note replaced completely", 
        data: notes[index] 
    });
} )

router.patch('/:index',(req,res) => {
    const index = parseInt(req.params.index,10);

    if(isNaN(index) || index<0||index>notes.length){
        res.status(404).json({"massage" : "notes not found"})
    }
   res.status(200).json({ 
        message: "Note updated partially", 
        data: notes[index] 
    });
})

module.exports = router;