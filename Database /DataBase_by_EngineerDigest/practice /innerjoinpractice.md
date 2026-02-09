This is a great dataset for practicing JOINS! You've got a **One-to-Many** relationship (one author, many books) and a **Many-to-Many** relationship (books and categories via a junction table).

Here are some challenges ranging from "warm-up" to "advanced scenario" levels:

---

### **Level 1: The Direct Asks (Warm-up)**

1. **The Price Check:** List all book titles along with the last name of their author, but only for books that cost more than **$13.00**.
2. **The Vintage List:** Show the `first_name` and `last_name` of authors who wrote books published before **1940**. (Ensure each author's name appears only once in the results).
3. **Specific Category Search:** List all book titles that belong to the **'Romance'** category.

---

### **Level 2: Scenario-Based Challenges**

#### **Scenario 1: The "Legacy Report"**

The library wants to know which authors were "young" when they published their books.

* **Task:** Write a query to show the `title`, author `last_name`, and the **age of the author** at the time the book was published.
* *Hint:* You’ll need to subtract `birth_year` from `publication_year`.

#### **Scenario 2: The "Genre Count"**

The marketing team wants to know which genres are the most popular in their collection.

* **Task:** Show a list of `category_name` and a count of how many books are assigned to each category. Sort the list so the category with the most books is at the top.

#### **Scenario 3: The "Mystery Author" Mystery**

An investor is interested in authors who write in the **'Mystery'** category.

* **Task:** Find the `first_name` and `last_name` of authors who have at least one book in the 'Mystery' category.

---

### **Level 3: The "Boss" Challenge**

#### **Scenario 4: The High-Value Classic Audit**

The library manager wants to identify "High-Value Classics."

* **Goal:** Find books that are in the **'Classic'** category AND have a **price higher than the average price** of all books in the database.
* **Output:** `title`, `price`, and `last_name`.
* *Note:* This will require a `JOIN` and a `Subquery` in your `WHERE` clause.

---

**Which one would you like to tackle first? If you get stuck on the syntax for the Many-to-Many joins, let me know!**



