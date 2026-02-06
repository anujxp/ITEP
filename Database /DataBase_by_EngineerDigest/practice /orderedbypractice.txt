This is an excellent set of practice scripts! You’ve covered the core of sorting, from the simple `ASC/DESC` to the more professional `CASE` logic and `EXPLAIN` plans used for performance tuning.

To help you master these, I’ve prepared two parts: **Scenario-Based Challenges** for your current table and a **New Dataset** with its own unique challenges.

---

### **Part 1: Scenario Challenges (Using your `products` table)**

Try to write the queries for these real-world business requirements:

1. **The "Urgent Restock" Report:** Find all products in the 'Furniture' or 'Electronics' category. Sort them so that the items with the **lowest** stock appear first. If two items have the same stock, show the more expensive one first.
2. **The "Freshness" Search:** Use a function-based sort to show products updated in the **last 7 days**, ordered by the most recent update.
3. **Custom Warehouse Logic:** Your warehouse manager wants a specific order:
* Show 'Appliances' first, then 'Furniture', then 'Electronics'.
* Within each category, sort the `product_name` alphabetically.


4. **The "Hidden Gems" Query:** Find products where the `price` is **NULL** or the `stock_quantity` is **NULL**. Sort them so that the records with the most missing information appear at the top.

---

### **Part 2: New Dataset (The "JainZBites" Restaurant Scenario)**

Since you are working on the **JainZBites** project, let’s practice sorting with a menu-style dataset. This will be very useful for your Java backend development later.

**New Table & Data:**

```sql
CREATE TABLE menu_items (
    item_id INT PRIMARY KEY,
    item_name VARCHAR(100),
    category VARCHAR(50), -- Starter, Main Course, Dessert, Beverage
    price DECIMAL(10,2),
    is_vegan BOOLEAN,
    calories INT,
    popularity_score INT -- 1 to 10
);

INSERT INTO menu_items VALUES
(1, 'Paneer Tikka', 'Starter', 250.00, FALSE, 300, 9),
(2, 'Dal Tadka', 'Main Course', 180.00, TRUE, 250, 8),
(3, 'Veg Biryani', 'Main Course', 320.00, TRUE, 450, 10),
(4, 'Gulab Jamun', 'Dessert', 80.00, FALSE, 350, 7),
(5, 'Masala Chai', 'Beverage', 40.00, FALSE, 100, 9),
(6, 'Hara Bhara Kabab', 'Starter', 210.00, TRUE, 200, 6),
(7, 'Lemonade', 'Beverage', 60.00, TRUE, 80, 5);

```

---

### **Part 3: Challenges for the Restaurant Data**

1. **The "Healthy Choice" Menu:** Display all items sorted by **Calories** in ascending order. However, use a `CASE` statement to ensure all **Vegan** items (is_vegan = TRUE) always appear at the top of the list, regardless of their calories.
2. **The "Menu Layout" Sort:** In a restaurant menu, you usually see Starters first, then Main Courses, then Desserts. Write a query using `FIELD()` to sort the categories in that order, and within each category, sort by **Price** (High to Low).
3. **The "Star Item" Calculation:** Create a calculated column called `value_index` (which is `popularity_score / price`). Sort the menu so the items with the highest "value for money" appear first.
4. **The "Search Bar" Logic:** Imagine a user types "Ba" in your search bar. Write a query to find all items where the name contains "Ba" (case-insensitive) and sort them so that names **starting** with "Ba" appear before names that just **contain** "Ba".

---

**Next Step:**
Would you like me to provide the **solutions** for these challenges so you can check your work, or would you like to try writing them first? (I highly recommend trying them—it's the Mosh way!)
