import { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function CreatePostPage() {
  const [caption, setCaption] = useState('');
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file || !caption) return alert("Please select an image and write a caption");

    // 1. FormData is mandatory for Multer/File uploads
    const formData = new FormData();
    // 'image' MUST match the key in your router: upload.single("image")
    formData.append('image', file); 
    formData.append('caption', caption);

    setLoading(true);
    try {
      // 2. Send the request
      await axios.post('http://localhost:3000/api/v1/post/create', formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });

      alert("Post created successfully!");
      navigate('/'); // Redirect to FeedPage after success
    } catch (error) {
      console.error("Upload error:", error);
      alert("Failed to create post. Check console.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '400px', margin: '0 auto' }}>
      <h1>Create New Post</h1>
      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: '15px' }}>
          <label>Select Image:</label>
          <input 
            type="file" 
            onChange={(e) => setFile(e.target.files[0])} 
            accept="image/*"
            style={{ display: 'block', marginTop: '5px' }}
          />
        </div>
        <div style={{ marginBottom: '15px' }}>
          <label>Caption:</label>
          <input 
            type="text" 
            placeholder="What's on your mind?" 
            value={caption}
            onChange={(e) => setCaption(e.target.value)}
            style={{ width: '100%', padding: '8px', marginTop: '5px' }}
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? 'Uploading...' : 'Submit Post'}
        </button>
      </form>
    </div>
  );
}

export default CreatePostPage;