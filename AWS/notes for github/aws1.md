Here are your detailed lecture notes covering everything we’ve discussed—from the physical infrastructure and global network to the specific steps for launching your first virtual machine.

---

# **Lecture Notes: AWS Global Infrastructure & EC2**

## **1. The Physical Backbone**

* **Data Centers:** These are high-security, unmarked buildings housing **50,000 to 80,000 physical servers**.
* **Security:** Multi-layered defense including biometric scanners, iris recognition, and 24/7 armed guards.
* **Redundancy:** Every data center has redundant power, cooling, and network connectivity to prevent any single point of failure.


* **The 1.75-inch Standard:** Servers are measured in **Rack Units (U)**. 1U is exactly **1.75 inches** tall. This standard allows AWS to stack thousands of servers efficiently in high-density racks.
* **Availability Zones (AZs):** A logical cluster of one or more data centers within a region.
* AWS recommends **replicating data across multiple AZs** for resiliency. If one AZ goes down (due to a power grid failure, etc.), the others remain online.


* **Regions:** Geographic areas containing multiple AZs (e.g., `us-east-1`, `ap-south-1`).
* **Selection Criteria:** Compliance (Data Governance), Proximity (Latency), Cost, and Service Availability.



---

## **2. Content Delivery & Latency**

* **Latency:** The delay in data travel. We minimize this by putting content closer to the user.
* **AWS CloudFront (CDN):** A service that delivers data, videos, and applications globally with high speed.
* **Origin:** The "Original" source of your data (like an S3 bucket or EC2 server).
* **Edge Server:** A server located at an **Edge Location** (700+ globally) that caches copies of your data.
* **Regional Edge Cache (13+):** A mid-tier cache that holds larger amounts of content to save the Origin from being overloaded.



---

## **3. Steps to Create a Virtual Machine (EC2 Instance)**

An **Instance** is a "virtual slice" of a physical server. Here is the checklist for launching one in the AWS Console:

### **Step 1: Choose a Region**

* Before clicking anything, ensure you are in the correct **Region** (top right corner of the console), such as **Mumbai** or **N. Virginia**, depending on where your users are.

### **Step 2: Launch Instance**

* Go to the **EC2 Dashboard** and click the orange **"Launch Instance"** button.

### **Step 3: Name and Tags**

* Give your instance a name following a professional convention (e.g., `hostelfinder-dev-server`).
* **Tags:** Add a Key: `Project` and Value: `HostelFinder` for better billing tracking.

### **Step 4: Choose an AMI (Amazon Machine Image)**

* Select your Operating System template.
* **Recommended:** *Amazon Linux 2023* (Free Tier eligible).

### **Step 5: Select Instance Type**

* Choose the hardware specs (CPU/RAM).
* **Recommended:** *t2.micro* or *t3.micro* (Stay within the **Free Tier**).

### **Step 6: Key Pair (Security)**

* Create a new **Key Pair** if you don't have one.
* **Action:** Download the `.pem` file and keep it safe. You cannot download it again, and you need it to log in!

### **Step 7: Configure Network (Security Groups)**

* **Security Group:** This is your virtual firewall.
* **Allow SSH (Port 22):** To connect to your server.
* **Allow HTTP (Port 80):** If you are hosting a website.

### **Step 8: Configure Storage (EBS)**

* Choose your "Virtual Hard Drive" size.
* **Note:** You get up to **30 GB** of SSD storage for free in the first 12 months.

### **Step 9: Review and Launch**

* Verify all settings on the summary page and click **Launch Instance**.

---

## **4. Post-Lecture Checklist (Cost Control)**

> **Important:** To avoid unexpected charges after your lab:

* **Stop Instance:** Like turning off a computer. You stop paying for the CPU, but pay a tiny fee for the storage.
* **Terminate Instance:** Deletes the server and storage entirely. **This is the best way to ensure your bill stays at $0.**

---

**Would you like me to walk you through how to connect to your instance using the terminal once it's launched?**
