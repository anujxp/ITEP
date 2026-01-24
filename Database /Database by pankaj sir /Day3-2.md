

## 1. The Core Philosophy: Why Version?

In the industry, software is a living organism. It evolves. Versioning allows a company to:

* **Track History:** Know exactly what code was running on January 24th if a system crashed.
* **Manage Rollbacks:** If Version 1.2 fails, you must be able to "downgrade" to Version 1.1 instantly.
* **Parallel Development:** One team can work on "Bug Fixes" for the current version while another works on "Big Features" for the next version.

---

## 2. Semantic Versioning (SemVer)

Most professional companies use a **Major.Minor.Patch** format (e.g., `v1.2.4`).

* **Major (1.x.x):** Breaking changes. The API or Database structure has changed so much that old code won't work.
* **Minor (x.2.x):** New **Features** added in a backward-compatible way.
* **Patch (x.x.4):** **Bug fixes** and small **Enhancements**. No new features.

---

## 3. The 3 Pillars: Bugs, Enhancements, and Features

In your job, every piece of work you do will fall into one of these three buckets for the next version release:

| Category | Definition | Example in your Project |
| --- | --- | --- |
| **Bugs** | Fixing something that is "Broken" or not working as intended. | Fixing a NullPointerException in the Sign-up flow. |
| **Enhancements** | Improving an existing feature without changing its purpose. | Making the MySQL search query 50% faster using an Index. |
| **Features** | Adding entirely new functionality that didn't exist before. | Adding a "Payment Gateway" or "Chat Bot" to SettleSpot. |

---

## 4. Integration: Code vs. API/SDK

When a version moves from `1.0` to `1.1`, the **Integration** points must be updated.

* **The Code:** The internal Java logic (the `.jar` or `.exe`).
* **API (The Contract):** If you change how data is sent, you must update the API. If you don't, other apps (like a Mobile App talking to your Java Backend) will break.
* **SDK:** The kit you give to other developers to help them use your new version.

---

## 5. Database Versioning: The "Scripting" Reality

**This is a major interview topic.** Unlike Java, where you can "override" a method, you **cannot override a database table** because it contains live data.

### The Problem

If you replace a `users` table, you delete all your customers.

### The Professional Solution: Migration Scripts

You create "Delta Scripts" or "Migration Scripts." These are SQL files that modify the existing state to the new state.

* **Version 1.0 Script:** `CREATE TABLE students (id INT, name VARCHAR(50));`
* **Version 1.1 Migration Script:** `ALTER TABLE students ADD COLUMN email VARCHAR(100);`

**Job Tip:** In a professional CI/CD pipeline, these scripts are never run manually. Tools like **Liquibase** or **Flyway** automatically detect the version and run the necessary scripts during deployment.

---

## 6. Versioning in Git (Tags)

In Git, you don't just "name" a folder Version 1.1. You use **Tags**.

* When a version is ready for Production, the manager "Tags" the commit:
`git tag -a v1.1 -m "Release version 1.1 with fixed login bug"`
* This creates a permanent "Bookmark" in the history. If you ever need to see exactly what Version 1.1 looked like, you can just `git checkout v1.1`.

---

## 7. The Tiered Deployment (Environment Sync)

Versioning is what keeps your environments in sync:

1. **Dev:** Working on `v1.2-alpha` (unstable).
2. **QA:** Testing `v1.1-rc` (Release Candidate).
3. **Prod:** Running `v1.0` (Stable).

---

### 💼 Interview Scenario: Versioning

**Interviewer:** *"We are moving from v1.0 to v1.1. We need to change a column name in our main 'transactions' table which has 10 million rows. How do you handle this?"*

**Your Job-Ready Answer:**

> "I would write a **Migration Script**. Since we cannot override the table without losing data, I would use an `ALTER TABLE` command. I would first test this script in the **QA environment** to see how long it takes to run on a large dataset. Once verified, I would include it in the **deployment package** so it runs on **Production** during the scheduled maintenance window, ensuring the **API** is updated simultaneously to reflect the new column name."

---

**Would you like me to ... provide a sample "Version 1.1 Migration Script" for your SettleSpot project?** It would show exactly how to add new columns and constraints without losing your existing property data.
