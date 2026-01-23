

## I. Triggers: The Automated "Gatekeepers"

Triggers are event-driven programs. They don't wait for you to call them; they watch the table and act when data changes.

### 1. The Core Components

* **Timing:** `BEFORE` (change data or validate it) vs. `AFTER` (log the change or update other tables).
* **Event:** `INSERT`, `UPDATE`, or `DELETE`.
* **Row-Level Logic:** We use `FOR EACH ROW` to ensure the trigger runs for every single record modified, not just the whole batch.

### 2. The "OLD" and "NEW" Pseudo-Tables

This is the most critical technical detail. Think of them as temporary "snapshots":

* **`INSERT`**: Only has `NEW` (the data coming in).
* **`UPDATE`**: Has `OLD` (original data) and `NEW` (proposed change).
* **`DELETE`**: Only has `OLD` (the data being removed).

### 3. Professional Use Case: The "Soft Delete" Enforcer

In production, we don't want a junior dev running `DELETE FROM accounts`. We use a trigger to block it and force a "Soft Delete" (updating a flag instead).

```sql
DELIMITER //
CREATE TRIGGER block_hard_delete
BEFORE DELETE ON accounts
FOR EACH ROW
BEGIN
    -- SIGNAL is the SQL way of 'throwing an Exception'
    SIGNAL SQLSTATE '45000' 
    SET MESSAGE_TEXT = 'Hard Deletes Forbidden! Update is_active = FALSE instead.';
END //
DELIMITER ;

```

---

## II. Stored Procedures: Server-Side Programming

While a Function calculates a value (like `ROUND`), a Procedure executes a **Process** (like `Sign_Up`).

### 1. Parameters (The Connection to Java)

* **`IN`**: Read-only. The procedure uses this but can't change the original variable.
* **`OUT`**: Write-only. This is how you "Return" data back to your Java application.
* **`INOUT`**: Read and Write.

### 2. Local Variables (`DECLARE` vs `SET`)

* You must `DECLARE` all variables at the very top of the `BEGIN...END` block.
* Use `SET` for math: `SET v_interest = v_bal * 0.05;`
* Use `SELECT...INTO` to pull data from a table into a variable: `SELECT balance INTO v_bal FROM accounts;`

---

## III. Control Flow: Logic & Loops

This turns your database into a programming engine.

### 1. The `IF` Statement

Used for branching logic. In our **Bank Interest** example, we use it to check if an account is "Active" before adding money.

```sql
IF v_is_active = TRUE THEN
    -- Update balance
ELSE
    -- Error message
END IF;

```

### 2. The `WHILE` Loop

Crucial for **Batch Processing**.

* **The Trap:** If you forget to increment your counter (`SET i = i + 1`), the database will freeze in an **Infinite Loop**.
* **The Use Case:** Calculating a 12-month loan EMI schedule and inserting 12 separate rows.

---

## IV. Defensive Coding: Error Handlers & Temporary Tables

This is what separates a student from a Senior DBA.

### 1. The `try-catch` (DECLARE HANDLER)

If your procedure tries to insert a duplicate email, it will normally crash. A `HANDLER` catches that error and lets you finish gracefully.

* **`EXIT HANDLER`**: "Catch and Stop."
* **`CONTINUE HANDLER`**: "Catch and Keep Going."

### 2. Temporary Tables (The "Scratchpad")

* Created with `CREATE TEMPORARY TABLE`.
* They are **private** to your session. If two users run the same procedure, they each get their own separate temp table.
* They vanish automatically when you disconnect.

---

## V. Efficiency: Pagination & Batching

Handling millions of rows without crashing the system.

### 1. Pagination (The UI King)

* `LIMIT 10`: Show 10 rows.
* `OFFSET 20`: Skip the first 20.
* **Java Formula:** `OFFSET = (PageNumber - 1) * PageSize`.

### 2. Batch Processing (The System King)

Instead of updating 1 million rows in one query (which locks the table for everyone), we use a `WHILE` loop + `LIMIT` to update 1,000 at a time and `COMMIT` after each batch.

---

## VI. Data Migration: Import & Export

Getting data across the "border."

* **`mysqldump`**: Command-line tool. It exports the structure + data into a `.sql` file. Best for backups.
* **`SELECT ... INTO OUTFILE`**: Exports a specific query result into a `.csv` file.
* **`LOAD DATA INFILE`**: The **fastest** way to get data into MySQL. It bypasses much of the SQL overhead that a normal `INSERT` has.
* **Security:** `secure_file_priv` is the variable that tells you which folder MySQL is allowed to "see."

---

## Final Recap Table

| Feature | Key Syntax | Professional Purpose |
| --- | --- | --- |
| **Trigger** | `NEW.col_name` | Automatic validation/audit logs. |
| **Procedure** | `CALL name(IN, OUT)` | Wrapping complex business logic (Banking/LMM). |
| **Variable** | `DECLARE v_name TYPE` | Temporary storage during a calculation. |
| **Handler** | `DECLARE EXIT HANDLER` | "Try-Catch" for database safety. |
| **Temp Table** | `CREATE TEMPORARY` | A private scratchpad for complex reports. |
| **Pagination** | `LIMIT x OFFSET y` | Preventing memory crashes in Java UIs. |
| **Import** | `LOAD DATA INFILE` | Moving massive Excel data into SQL. |

**Would you like me to prepare a "Master Challenge" script that uses a Trigger to watch a table, a Procedure to process it in Batches, and a Handler to catch any errors?**
