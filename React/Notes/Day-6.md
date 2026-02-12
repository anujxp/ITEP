Conditional rendering in React isn't just about showing or hiding text; it is a powerful way to make your UI **visually dynamic** by swapping CSS classes or inline styles based on the state of your application.

---

# 📜 React Lecture Notes: Dynamic Styling & Conditional Rendering 

**Topic:** Logic-Driven UI and CSS Integration

**Date:** February 12, 2026

### **1. What is Conditional Rendering?**

It is the ability to render different UI elements or components based on certain conditions (like a boolean state, a user role, or data availability). In React, we use standard JavaScript logic (`if`, `ternary`, `&&`) to decide what the DOM should look like.

---

### **2. Applying CSS using Conditional Rendering**

There are three primary ways to change the look of an element based on a condition:

#### **A. Dynamic Inline Styles**

You can use a ternary operator directly inside the `style` object. This is best for specific values like colors or widths that change based on state.

```jsx
const [isActive, setIsActive] = useState(false);

return (
  <div style={{ 
      backgroundColor: isActive ? 'green' : 'red', 
      padding: '10px',
      color: 'white' 
  }}>
    Status: {isActive ? "Online" : "Offline"}
  </div>
);

```

#### **B. Dynamic Class Names (The Professional Way)**

Instead of changing styles one-by-one, you toggle an entire CSS class. This keeps your JavaScript clean and your CSS in the stylesheet.

**CSS File (`App.css`):**

```css
.btn { padding: 10px; border: none; }
.btn-enabled { background-color: blue; cursor: pointer; }
.btn-disabled { background-color: grey; cursor: not-allowed; }

```

**React Component:**

```jsx
<button className={`btn ${isEnabled ? 'btn-enabled' : 'btn-disabled'}`}>
  {isEnabled ? "Submit" : "Please Wait"}
</button>

```

#### **C. Conditional Rendering of Whole Elements**

Sometimes you don't just change the style; you change the whole element. We use the **Logical AND (`&&`)** for this.

```jsx
{showError && <p className="error-message">Invalid Password!</p>}

```

---

### **3. Comparison: When to use which?**

| Method | Use Case | Performance |
| --- | --- | --- |
| **Inline Style** | One-off changes (e.g., a progress bar width). | 🟡 Slower (objects created on every render). |
| **Class Toggling** | Most UI changes (colors, themes, layouts). | 🟢 Best (standard CSS optimization). |
| **Ternary Component** | Swapping entire UI blocks (Login vs. Profile). | 🟢 Good (React only renders one). |

---

### **4. Recall Code Snippet: The "Theme Toggler"**

This example combines `useState`, Conditional Rendering, and Dynamic CSS classes.

```jsx
import React, { useState } from 'react';
import './Theme.css';

const ThemeComponent = () => {
    const [isDarkMode, setIsDarkMode] = useState(false);

    return (
        /* Dynamic Class Assignment */
        <div className={isDarkMode ? "dark-theme" : "light-theme"}>
            <h1>{isDarkMode ? "Dark Mode" : "Light Mode"}</h1>
            
            <button onClick={() => setIsDarkMode(!isDarkMode)}>
                Switch to {isDarkMode ? "Light" : "Dark"}
            </button>

            {/* Logical AND: Only shows in Dark Mode */}
            {isDarkMode && <p>🌙 Moonlight activated!</p>}
        </div>
    );
};

```

---

### **5. Key Rules to Remember**

1. **Template Literals:** Always use backticks (```) when combining a permanent class with a dynamic one: `className={`permanent ${dynamic}`}`.
2. **The "Falsey" Trap:** Be careful with `&&`. If the condition is `0`, React will render `0`. Always use boolean logic like `count > 0 && ...`.
3. **Readability:** If your ternary operator gets too nested (e.g., `a ? b : c ? d : e`), move that logic to a variable outside the `return` statement.

---

### **Summary Checklist**

* [ ] Do I have a state variable to trigger the change?
* [ ] Am I using `style={{ ... }}` for inline or `className={...}` for classes?
* [ ] Did I remember to use curly braces `{}` to write JS inside my JSX?
* [ ] If using classes, are they defined in my `.css` file and imported?


