import { useState, useEffect } from 'react';

function App() {
  // State to hold our data
  const [notes, setNotes] = useState([]);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');

  // The Backend URL (matches your Express setup)
  const API_URL = 'http://localhost:3000/api/v1/notes';

  async function fetchNotes(){
    try {
      const response = await fetch(API_URL);
      const data = await response.json();
      
      // Look at noteController.js: we sent { message: "...", data: notes }
      if (data.data) {
        setNotes(data.data);
      }
    } catch (error) {
      console.error("Error fetching notes:", error);
    }
  };
  // 1. READ: Fetch notes when the page loads
  useEffect(() => {
    fetchNotes();
  }, []);


  // 2. CREATE: Send new note to backend
  const handleCreateNote = async (e) => {
    e.preventDefault(); // Stop page refresh

    if (!title || !description) return alert("Please fill all fields");

    try {
      const response = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, description }) // Matches req.body
      });

      if (response.ok) {
        setTitle('');
        setDescription('');
        fetchNotes(); // Refresh the list instantly
      }
    } catch (error) {
      console.error("Error creating note:", error);
    }
  };

  // 3. DELETE: Tell backend to remove a note by ID
  const handleDeleteNote = async (id) => {
    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
      });

      if (response.ok) {
        fetchNotes(); // Refresh the list instantly
      }
    } catch (error) {
      console.error("Error deleting note:", error);
    }
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'system-ui' }}>
      <h1>My legendary Notes</h1>

      {/* Creation Form */}
      <form onSubmit={handleCreateNote} style={{ marginBottom: '30px' }}>
        <input
          type="text"
          placeholder="Note Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          style={{ display: 'block', marginBottom: '10px', padding: '8px' }}
        />
        <textarea
          placeholder="Note Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          style={{ display: 'block', marginBottom: '10px', padding: '8px', width: '200px' }}
        />
        <button type="submit" style={{ padding: '8px 16px', cursor: 'pointer' }}>
          Add Note
        </button>
      </form>

      {/* Display Notes */}
      <div style={{ display: 'grid', gap: '15px' }}>
        {notes.length === 0 ? (
          <p>No notes found. Create one!</p>
        ) : (
          notes.map((note) => (
            <div key={note._id} style={{ border: '1px solid #ccc', padding: '15px', borderRadius: '8px', width: '300px' }}>
              <h3 style={{ margin: '0 0 10px 0' }}>{note.title}</h3>
              <p style={{ margin: '0 0 15px 0' }}>{note.description}</p>
              <button 
                onClick={() => handleDeleteNote(note._id)}
                style={{ background: '#ff4444', color: 'white', border: 'none', padding: '5px 10px', borderRadius: '4px', cursor: 'pointer' }}
              >
                Delete
              </button>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default App;