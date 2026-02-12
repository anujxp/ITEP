It sounds like you’ve moved into the **Lifecycle of a Component** and optimized rendering. These are "deep dive" topics that explain how React manages the birth, life, and death of a component in the DOM.

Here are the notes for your last lecture based on these topics.

---

# 📜 React Lecture Notes: Lifecycle & Pure Components

**Topic:** The Lifecycle Phases and Optimization

**Date:** February 10, 2026

### **1. The Component Lifecycle**

In React, every component goes through a lifecycle. Think of it like a human life: Birth (**Mounting**), Growth (**Updating**), and Death (**Unmounting**).

#### **A. Mounting (The Birth)**

This is the phase when the component is being created and inserted into the DOM for the first time.

* **Key Method (Class):** `componentDidMount()`
* **Use Case:** This is the best place to fetch data from an API or set up timers.
* **Functional Hook:** `useEffect(() => { ... }, [])` (The empty array `[]` means "run only once on birth").

#### **B. Updating (The Life)**

This happens whenever a component's **State** or **Props** change. React re-renders to reflect the new data.

* **Key Method (Class):** `componentDidUpdate()`
* **Use Case:** Updating the DOM in response to prop changes or saving data to local storage when state changes.
* **Functional Hook:** `useEffect(() => { ... }, [stateVariable])`

#### **C. Unmounting (The Death)**

This is the phase when the component is being removed from the DOM.

* **Key Method (Class):** `componentWillUnmount()`
* **Use Case:** Cleaning up "messy" things like stopping a timer or canceling a network request so the app doesn't crash (Memory Leaks).
* **Functional Hook:** The "Cleanup" function inside `useEffect`.

---

### **2. Pure Components**

A **Pure Component** is an optimization tool in React classes.

* **The Problem:** Normally, a component re-renders every time its parent re-renders, even if the data (props/state) hasn't changed. This is a waste of performance.
* **The Solution:** A `PureComponent` performs a **Shallow Comparison** of props and state. If the data is the same as before, React skips the re-render.

**Comparison:**
| Feature | `React.Component` | `React.PureComponent` |
| :--- | :--- | :--- |
| **Re-render** | Re-renders every time parent does. | Only re-renders if props/state change. |
| **Check** | No automatic check. | Performs "Shallow Comparison." |
| **Use Case** | Most components. | Performance-heavy components with simple data. |

---

### **3. Recall Code Snippet: Class vs. Functional Lifecycle**

Here is how you handle the "Birth" and "Death" in both styles:

**Class Style:**

```javascript
class Timer extends React.PureComponent {
  componentDidMount() {
    console.log("Timer started!");
    this.timerID = setInterval(() => console.log("Tick"), 1000);
  }

  componentWillUnmount() {
    console.log("Cleaning up timer...");
    clearInterval(this.timerID);
  }

  render() { return <h1>Check the Console</h1>; }
}

```

**Functional Style (Modern):**

```javascript
const Timer = () => {
  useEffect(() => {
    console.log("Birth!");
    const timer = setInterval(() => console.log("Tick"), 1000);

    // This is the Unmounting/Cleanup part
    return () => {
      console.log("Death!");
      clearInterval(timer);
    };
  }, []); // Empty array ensures this only runs on Mount/Unmount

  return <h1>Check the Console</h1>;
};

```

---

### **4. Key Technical Terms for Interviews**

* **Shallow Comparison:** React only checks the top-level values. If you have a nested object, a Pure Component might not detect changes inside the object.
* **Reconciliation:** The process where React compares the old Virtual DOM with the new one.
* **Side Effects:** Things that happen outside the return of a component (like API calls or manual DOM changes).

---

### **Summary Checklist**

* [ ] Do I understand that **Mounting** is the first time the UI appears?
* [ ] Can I explain why we need to "Clean up" in **Unmounting**?
* [ ] Do I know that **Pure Components** help save processing power?
* [ ] Have I practiced using `useEffect` with and without a dependency array?

**Would you like me to show you a practical example of a "Memory Leak" that happens if you forget to clean up during Unmounting?**
