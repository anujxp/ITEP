import { useState, useEffect } from 'react';
import axios from 'axios';

function FeedPage() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get('http://localhost:3000/api/v1/post/all');
        setPosts(response.data.data);
      } catch (err) {
        console.error("Failed to fetch posts", err);
      }
    };
    fetchPosts();
  }, []);

  return (
    <div>
      <h2>Your Feed</h2>
      <div style={{ display: 'grid', gap: '20px' }}>
        {posts.map((post) => (
          <div key={post._id} style={{ border: '1px solid #ddd', padding: '10px' }}>
            <img src={post.imageUrl} alt="post" style={{ width: '100%', maxWidth: '300px' }} />
            <p>{post.caption}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
export default FeedPage;