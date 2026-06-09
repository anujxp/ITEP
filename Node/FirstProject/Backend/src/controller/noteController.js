import { Note } from '../models/notes.model.js';

// Create a new note
async function createNote(req, res) {
  try {
    const { title, description } = req.body;

    if (!title || !description) {
      return res.status(400).json({ message: "All fields are required" });
    }

    const newNote = await Note.create({ title, description });
    return res.status(201).json({ message: "Note created successfully", data: newNote });
  } catch (error) {
    return res.status(500).json({ message: "Server error", error: error.message });
  }
}

// Get all notes
async function read(req, res) {
  try {
    const notes = await Note.find();
    
    if (notes.length === 0) {
      return res.status(200).json({ message: "Notes list is empty" });
    }
    
    return res.status(200).json({ message: "Retrieval successful", data: notes });
  } catch (error) {
    return res.status(500).json({ message: "Server error", error: error.message });
  }
}

// Delete a note
async function delete1(req, res) {
  try {
    const { id } = req.params;
    const deletedNote = await Note.findOneAndDelete({ _id: id });
    if (!deletedNote) {
      return res.status(404).json({ message: "Note not found" });
    }

    return res.status(200).json({ message: "Deleted successfully" });
  } catch (error) {
    return res.status(500).json({ message: "Server error", error: error.message });
  }
}

async function getOne(req,res){
try {
    const title = req.params.title;
    const note = Note.findOne({title : title})
    if(!note)
        return res.status(404).json({message : `note not found with${title}`})
    res.status(200).json({message : `note found with title ${title}`,
    data: note})
} catch (error) {
    return res.status(500).json({message : "server error",error : error.message})
}
}

export { createNote, read, delete1 ,getOne};
