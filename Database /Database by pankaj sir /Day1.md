Here is the complete content from today's session formatted as a clean **Markdown (.md)** file. You can copy and paste this directly into tools like **Notion, Obsidian, GitHub, or VS Code**.

---

# SQL & Database Fundamentals Cheat Sheet

## 1. SQL Joins: Connecting Tables

Joins are used to combine rows from two or more tables based on a related column between them.

### Join Types & Logic

| Join Type | Result | Behavior |
| --- | --- | --- |
| **INNER JOIN** | **Intersection** | Returns records with matching values in both tables. |
| **LEFT JOIN** | **Left + Matches** | Returns all records from the left table and matched records from the right. |
| **RIGHT JOIN** | **Right + Matches** | Returns all records from the right table and matched records from the left. |
| **FULL JOIN** | **Everything** | Returns all records when there is a match in either table. |

### Join Syntax (Explicit vs Implicit)

**Modern Syntax (Preferred):**

```sql
SELECT * FROM Recipe AS R
INNER JOIN Billdetail AS bd ON R.id = bd.id;

```

**Legacy "Where Clause" Syntax:**

```sql
SELECT * FROM Recipe R, Billdetail bd
WHERE R.id = bd.id;

```

---

## 2. Database Design & Principles

### ACID Properties (Transactions)

Guarantees that database transactions are processed reliably.

* **Atomicity:** The "All or Nothing" rule. If one part fails, the whole transaction fails.
* **Consistency:** Data must meet all validation rules after a transaction.
* **Isolation:** Transactions do not interfere with each other while running.
* **Durability:** Once committed, data remains saved even if the system crashes.

### Normalization

The process of organizing data to reduce redundancy and improve integrity.

* **1NF (First Normal Form):** Atomic values; no repeating groups in a cell.
* **2NF (Second Normal Form):** 1NF + all non-key columns depend on the **entire** Primary Key.
* **3NF (Third Normal Form):** 2NF + non-key columns do not depend on other non-key columns.

### Keys

* **Primary Key (PK):** A unique identifier for a record (cannot be NULL).
* **Foreign Key (FK):** A column that links to a Primary Key in another table.

---

## 3. Database Objects: Views

A **View** is a virtual table based on the result-set of an SQL statement. It does **not** store data itself.

**Key Benefits:**

1. **Security:** Restricts access to specific columns or rows.
2. **Simplicity:** Simplifies complex joins for the end-user.
3. **Level Access Management:** Users can be given permission to a view without having access to the base tables.

**Example:**

```sql
CREATE VIEW v_cheap_rooms AS
SELECT name, price FROM rooms
WHERE price < 1000;

SELECT * FROM v_cheap_rooms;

```

---

## 4. Logical Execution Order

When you run a query, the database processes it in this order:

1. `FROM` / `JOIN`
2. `WHERE`
3. `GROUP BY`
4. `HAVING`
5. `SELECT`
6. `ORDER BY`

---

## 5. Practical Implementation (Hostel Finder)

### Table Schema

**Table: `hostels**`
| hostel_id (PK) | name | city |
| :--- | :--- | :--- |
| 101 | Sunrise Stay | Mumbai |

**Table: `rooms**`
| room_id (PK) | hostel_id (FK) | price |
| :--- | :--- | :--- |
| 1 | 101 | 1200 |

### Complex Join Query

```sql
-- Link Recipes, Bill Details, and Bill Master
SELECT R.name, bd.quantity, bm.total_amount
FROM Recipe AS R
INNER JOIN Billdetail AS bd ON R.id = bd.id
INNER JOIN Billmaster AS bm ON bm.id = bd.orderid
WHERE bd.orderid = 101;

```

---

**Would you like me to convert this into a PDF format for you, or should we start building the SQL table creation scripts for your project?**
