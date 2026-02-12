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


You’ve been doing a great job working through these piece by piece! Here are the complete solutions for all the challenges. Compare these with the logic you've been practicing to see how the different parts (Joins, Aggregates, and Subqueries) fit together.

### **Level 1: The Direct Asks**

**1. The Price Check**

```sql
SELECT b.title, a.last_name, b.price
FROM books b
INNER JOIN authors a ON b.author_id = a.author_id
WHERE b.price > 13.00;

```

**2. The Vintage List**
*Note: We use `DISTINCT` to ensure each author appears only once even if they wrote multiple vintage books.*

```sql
SELECT DISTINCT a.first_name, a.last_name
FROM authors a
INNER JOIN books b ON a.author_id = b.author_id
WHERE b.publication_year < 1940;

```

**3. Specific Category Search**

```sql
SELECT b.title
FROM books b
INNER JOIN book_categories bc ON b.book_id = bc.book_id
INNER JOIN categories c ON bc.category_id = c.category_id
WHERE c.category_name = 'Romance';

```

---

### **Level 2: Scenario-Based Challenges**

**Scenario 1: The Legacy Report (Author Age)**

```sql
SELECT b.title, a.last_name, (b.publication_year - a.birth_year) AS age_at_publication
FROM books b
INNER JOIN authors a ON b.author_id = a.author_id;

```

**Scenario 2: The Genre Count**

```sql
SELECT c.category_name, COUNT(bc.book_id) AS book_count
FROM categories c
INNER JOIN book_categories bc ON c.category_id = bc.category_id
GROUP BY c.category_name
ORDER BY book_count DESC;

```

**Scenario 3: The Mystery Author Mystery**

```sql
SELECT DISTINCT a.first_name, a.last_name
FROM authors a
INNER JOIN books b ON a.author_id = b.author_id
INNER JOIN book_categories bc ON b.book_id = bc.book_id
INNER JOIN categories c ON bc.category_id = c.category_id
WHERE c.category_name = 'Mystery';

```

---

### **Level 3: The "Boss" Challenge**

**Scenario 4: High-Value Classic Audit**
This combines a 3-table join with a **Subquery** to find the average price dynamically.

```sql
SELECT b.title, b.price, a.last_name
FROM books b
INNER JOIN authors a ON b.author_id = a.author_id
INNER JOIN book_categories bc ON b.book_id = bc.book_id
INNER JOIN categories c ON bc.category_id = c.category_id
WHERE c.category_name = 'Classic' 
  AND b.price > (SELECT AVG(price) FROM books);

```

### **A quick breakdown of the "Boss" logic:**

1. The **Subquery** `(SELECT AVG(price) FROM books)` runs first to get a single number (the average).
2. The **Joins** connect the books to their authors and categories.
3. The **WHERE** clause filters for 'Classic' AND checks if the price is higher than the number we found in step 1.

**Would you like me to explain how that Subquery works in more detail, or are you ready for a new set of challenges involving LEFT and RIGHT joins?**



select b.book_id,b.title, concat(a.first_name,' ',a.last_name) full_name,GROUP_CONCAT(c.category_name) from books as b inner joi
n authors as a on a.author_id = b.author_id inner join book_categories bc on b.book_id = bc.book_id inner join categories c on c.catego
ry_id = bc. category_id  GROUP BY b.book_id;
