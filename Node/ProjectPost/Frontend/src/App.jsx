import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import FeedPage from './pages/FeedPage';
import CreatePostPage from './pages/CreatePostPage';

function App() {
  return (
    <Router>
      <nav style={{ padding: '10px', borderBottom: '1px solid #ccc' }}>
        <Link to="/" style={{ marginRight: '10px' }}>Feed</Link>
        <Link to="/create">Create Post</Link>
      </nav>

      <Routes>
        <Route path="/" element={<FeedPage />} />
        <Route path="/create" element={<CreatePostPage />} />
      </Routes>
    </Router>
  );
}
export default App;