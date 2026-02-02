
# 📜 JavaScript Lecture Notes: React Class Components & State

**Topic:** Class Architecture, the Constructor, and State Management

**Date:** February 2, 2026

### **1. What is a Class Component?**

Before React 16.8 (Hooks), Class Components were the only way to create components that had "memory" (State) and could handle "Lifecycle" events.

* It is a ES6 Class that **extends** `React.Component`.
* It must have a `render()` method that returns JSX.

### **2. What is "State"?**

**State** is a built-in React object that is used to store data that changes over time.

* **State is Private:** Unlike Props, State is managed entirely within the component.
* **State is Reactive:** When the state changes, React automatically re-renders the component to show the new data.

---

### **3. Managing State in Class Components**

To manage state, we follow a specific pattern in the class:

#### **A. The Constructor**

This is the "Setup" phase. It runs once when the component is created.

* `super()`: You **must** call this to inherit properties from `React.Component`.
* `this.state`: This is where you initialize your data as an object.

#### **B. `this.setState()` (The Golden Rule)**

You **never** change state directly (e.g., `this.state.count = 5` is forbidden). You must use the `this.setState()` method. This method tells React: *"Hey, the data changed! Please update the UI."*

---

### **4. Recall Code Snippet: The Counter Example**

Here is the implementation of a basic Counter, similar to what was practiced in the commit.

```jsx
import React, { Component } from 'react';

class Counter extends Component {
  constructor() {
    super();
    // 1. Initializing State
    this.state = {
      count: 0
    };
  }

  // 2. Logic to update state
  increment = () => {
    this.setState({
      count: this.state.count + 1
    });
  }

  decrement = () => {
    this.setState({
      count: this.state.count - 1
    });
  }

  render() {
    return (
      <div style={{ textAlign: 'center', marginTop: '50px' }}>
        <h1>Counter: {this.state.count}</h1>
        {/* 3. Binding events to methods */}
        <button onClick={this.increment}>Increase</button>
        <button onClick={this.decrement}>Decrease</button>
      </div>
    );
  }
}

export default Counter;

```

---

### **5. Key Technical Takeaways**

| Concept | Explanation |
| --- | --- |
| **`this` Keyword** | In Class Components, you use `this` to access state, props, and methods. |
| **Event Binding** | In the commit, you'll notice using Arrow Functions for methods (like `increment = () => { ... }`). This is a modern way to avoid manually "binding" `this` in the constructor. |
| **Object Merging** | When you call `setState({ count: 5 })`, React doesn't delete your other state variables; it just "merges" the new count into the existing state object. |

---

### **6. Why are we learning this if Functional Components are popular?**

1. **Legacy Code:** Many large companies still use Class Components.
2. **Mental Model:** It teaches you that **State** belongs to the instance of the class.
3. **Foundation:** Understanding `render()` and `this` makes learning `useState` and `useEffect` much easier later.

---

### **Summary Checklist for Practice**

* [ ] Did I extend `Component`?
* [ ] Did I call `super()` in the constructor?
* [ ] Is my state an **Object**?
* [ ] Am I using `this.setState()` to update the UI?

**Would you like me to create a scenario-based task where you build a "Toggle Visibility" feature (Show/Hide text) using a Class Component?**
