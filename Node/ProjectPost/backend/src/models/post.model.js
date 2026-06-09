import mongoose from 'mongoose'

const postSchema = new mongoose.Schema({
    imageUrl: {
        type: String,
        required: [true, "Image URL is required"]
    },
    caption: {
        type: String,
        required: [true, "Caption is required"],
        trim: true
    }
}, { timestamps: true });

export const Post = mongoose.model('post',postSchema);