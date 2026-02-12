

# 📜 React Lecture Notes: Advanced Hooks & Data Flow

**Topics:** Props, `useEffect`, and `useReducer`

**Date:** February 12, 2026

### **1. Props: Unidirectional Data Flow**

Props (short for properties) are the primary way to move data through a React application. They act as the "arguments" for components.

* **Immutable:** A child component cannot modify the props it receives.
* **Direction:** Data always flows from Parent to Child.
* **Destructuring:** Modern practice involves destructuring props directly in the function signature for cleaner code.

**Recall Code:**

```jsx
// Parent Component
<UserCard name="Anuj" role="Full Stack Developer" />

// Child Component (using destructuring)
const UserCard = ({ name, role }) => {
  return (
    <div className="card">
      <h3>{name}</h3>
      <p>{role}</p>
    </div>
  );
};

```

---

### **2. `useEffect`: Managing Side Effects**

The `useEffect` hook is the "Swiss Army Knife" for handling tasks that happen outside the normal render cycle, such as API calls, subscriptions, or manual DOM changes.

**The Dependency Array Logic:**

1. **No Array:** Runs after every render. (Rarely used, can cause performance issues).
2. **Empty Array `[]`:** Runs once on **Mounting** (Birth). Ideal for initial data fetching.
3. **With Dependencies `[state]`:** Runs on mounting and whenever the specified state/prop changes.

**The Cleanup Phase:**
To prevent memory leaks (like a timer running after a page is closed), we return a "cleanup" function.

```javascript
useEffect(() => {
    const timer = setInterval(() => {
        console.log("Activity logged");
    }, 1000);

    // Cleanup function (Unmounting)
    return () => clearInterval(timer);
}, []); 

```

---

### **3. `useReducer`: The Logic Architect**

When `useState` becomes too complex (e.g., managing multiple related variables or complicated transitions), `useReducer` is the professional alternative. It centralizes all state transitions into one **Reducer Function**.

**The Pattern:**

1. **State:** The single source of truth (usually an object).
2. **Action:** An object that describes what happened (e.g., `{ type: 'INCREMENT' }`).
3. **Dispatch:** The function used to "send" the action to the reducer.
4. **Reducer:** A pure function that takes the current state and an action, then returns the new state.

**Recall Code:**

```javascript
const reducer = (state, action) => {
    switch (action.type) {
        case 'UPDATE_NAME':
            return { ...state, name: action.payload };
        case 'TOGGLE_STATUS':
            return { ...state, isActive: !state.isActive };
        default:
            return state;
    }
};

// Inside Component:
const [state, dispatch] = useReducer(reducer, { name: '', isActive: false });

```

---

### **4. Key Technical Comparison**

| Feature | `useEffect` | `useReducer` |
| --- | --- | --- |
| **Purpose** | Synchronization with external systems. | Complex state logic transitions. |
| **Trigger** | Reacting to a change in dependencies. | Manually triggered via `dispatch`. |
| **Analogy** | A "Reaction" (When X happens, do Y). | A "Manager" (Follow the rules to update state). |

---

### **5. Summary Checklist for Mastery**

* [ ] **Props:** Am I passing data down and keeping it read-only?
* [ ] **useEffect:** Have I correctly defined the dependency array to avoid infinite loops?
* [ ] **useReducer:** Is my reducer function "Pure" (does it return a new state without modifying the old one)?
* [ ] **State:** Am I using `useState` for simple values and `useReducer` for complex objects?


