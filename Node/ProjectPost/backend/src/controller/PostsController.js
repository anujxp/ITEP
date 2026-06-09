// src/controller/PostsController.js
import { log } from "node:console";
import { Post } from "../models/post.model.js"; 
import uploadToImageKit from "../services/storage.service.js";

const createPost = async (req, res) => {
    try {
        // 1. Grab the text data
        const { caption } = req.body; 

        // 2. Validate that the user actually sent a file via Multer
        if (!req.file) {
            return res.status(400).json({ message: "Image file is required" });
        }

        // 3. Delegate the upload to our ImageKit Service
        const imageUrl = await uploadToImageKit(req.file.buffer, req.file.originalname);
log(imageUrl)
        if (!imageUrl) {
            return res.status(500).json({ message: "Failed to upload image to cloud" });
        }

        // 4. Save to MongoDB (Ensure your model has 'img' and 'caption' properties)
        const newPost = await Post.create({
          imageUrl: imageUrl ,
            caption: caption
        });

        // 5. Send success response
        return res.status(201).json({
            message: "Post created successfully",
            data: newPost
        });

    } catch (error) {
        console.error("Controller Error:", error);
        return res.status(500).json({ message: "Internal server error" });
    }
};


 const getAllPosts = async (req, res) => {
    try {
        const posts = await Post.find().sort({ createdAt: -1 }); // Sort newest first
        res.status(200).json({ success: true, data: posts });
    } catch (error) {
        res.status(500).json({ success: false, message: "Error fetching posts" });
    }
};

export { createPost,getAllPosts };