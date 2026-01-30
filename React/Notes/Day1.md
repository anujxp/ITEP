
### **1. Detailed Lecture Notes: The React Foundation**

**The Core Philosophy:**
In Vanilla JavaScript, the code is **imperative**—you manually select elements and tell the browser exactly how to change them. React is **declarative**. You describe the "State" of the UI, and React handles the actual DOM updates using a "Virtual DOM" to ensure performance is optimized.

**Key Concepts from the Lecture:**

* **Components:** The building blocks of React. They are JavaScript functions that return JSX.
* **JSX (JavaScript XML):** A syntax extension that allows writing HTML-like code inside JavaScript. It is eventually compiled into standard JavaScript objects.
* **The Virtual DOM:** A lightweight copy of the real DOM. React compares the Virtual DOM with the real DOM and only updates the parts that have actually changed (a process called "Reconciliation").

---

### **2. Steps to Create a React Application (Using Vite)**

Modern development, especially in professional training environments, favors **Vite** over the older `create-react-app` because it is significantly faster.

1. **Open the Terminal:** (On Ubuntu, use `Ctrl+Alt+T`).
2. **Initialize the Project:** Run the following command and follow the prompts (select "React" and "JavaScript"):
```bash
npm create vite@latest my-react-app

```


3. **Navigate into the Project Folder:**
```bash
cd my-react-app

```


4. **Install Dependencies:** This creates the `node_modules` folder, which contains all the libraries React needs to run.
```bash
npm install

```


5. **Start the Development Server:**
```bash
npm run dev

```


*This will provide a local URL (usually `http://localhost:5173`) where the app can be viewed in the browser.*

---

### **3. The Connection: How `App.jsx` connects to the Browser**

The connection happens through a three-step chain involving the **Root Element**, the **Entry Point**, and the **Main Component**.

#### **Step 1: The "Hole" in the HTML (`index.html`)**

Inside the `index.html` file, there is a single, empty `<div>` with a specific ID. This is the only part of the HTML that React will control.

```html
<div id="root"></div>

```

#### **Step 2: The "Bridge" (`main.jsx` or `index.js`)**

This is the most critical file. It acts as the bridge between the React code and the real browser DOM. It performs three main actions:

1. It imports the `ReactDOM` library.
2. It uses the Vanilla JS method `document.getElementById('root')` to find that "hole" in the HTML.
3. It "renders" the main component into that spot.

**Simplified `main.jsx` logic:**

```javascript
import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx' // Importing your main component

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App /> {/* This is where App.jsx is called */}
  </React.StrictMode>,
)

```

#### **Step 3: The "Content" (`App.jsx`)**

This is the primary component where the actual UI code lives. It is a function that returns JSX. When you change something in `App.jsx`, `main.jsx` detects the change and pushes that update into the `root` div of the `index.html`.

**Basic `App.jsx` structure:**

```javascript
function App() {
  return (
    <div>
      <h1>Welcome to React</h1>
      <p>This component is now connected to the index.html!</p>
    </div>
  )
}

export default App; // This allows main.jsx to import it

```

---

### **4. Recall Code Snippet: Component vs. Elements**

When reviewing these notes, remember that React components must always start with a **Capital Letter**. This is how React distinguishes them from standard HTML tags.

```javascript
// A functional component
const MyComponent = () => {
  const name = "Anuj";
  return (
    <div className="container">
      {/* JavaScript variables are used inside curly braces */}
      <h1>Hello, {name}</h1> 
      <p>The DOM is now managed by React.</p>
    </div>
  );
};

```

**Key Observation:**
Notice that we use `className` instead of `class` in JSX because `class` is a reserved keyword in JavaScript. This is a common point of confusion when transitioning from standard HTML manipulation.
