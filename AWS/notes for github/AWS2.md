# **Lecture Notes: AWS Infrastructure & Application Deployment**

Today’s lecture bridged the gap between **Physical Infrastructure** (Data Centers/Hardware) and **Logical Deployment** (Virtual Machines and Managed Platforms). Below is the comprehensive guide to everything covered today.

---

## **1. The Core Infrastructure Concepts**

Before launching resources, we must understand the "physics" of the cloud.

* **The 1.75-inch Standard:** Servers are measured in **Rack Units (U)**. 1U = **1.75 inches** high. This allows for massive, standardized "stacking" in data center racks.
* **Data Centers:** Facilities housing **50,000 to 80,000 servers**. They feature "Layered Security" (Biometrics, 24/7 guards, and laser intrusion detection).
* **Availability Zones (AZs):** Distinct locations within a Region. **AWS recommends replicating data across AZs** so that if one data center fails, your app remains online.
* **Latency & CDNs:** To minimize "lag," we use **Edge Locations** (400+) and **Regional Edge Caches** (13) to store content physically closer to the user.

---

## **2. N-Tier Architecture (The Professional Standard)**

Instead of one big server, we break apps into layers for better security and scaling.

| Layer | Component | Job |
| --- | --- | --- |
| **Tier 1** | **Client** | User Interface (Browser/Mobile App). |
| **Tier 2** | **Web Server** | Optimized for **HTTP/HTTPS**. Serves static files (CSS/Images). |
| **Tier 3** | **App Server** | The "Brains." Processes business logic (e.g., Spring Boot, Tomcat). |
| **Tier 4** | **Database** | The "Memory." Stores raw data (e.g., MySQL). |

---

## **3. Step-by-Step Flow: Creating an EC2 Instance**

Launching an **Elastic Compute Cloud (EC2)** instance is like configuring a custom PC in the cloud.

1. **AMI (Amazon Machine Image):** Choose your OS "Blueprint" (e.g., Amazon Linux 2023 or Ubuntu).
2. **Instance Type:** Choose your hardware (CPU/RAM). **t2.micro** is best for the Free Tier.
3. **Key Pair:** Generate and **download your `.pem` key**. (Required for SSH login).
4. **Network Settings:** * Ensure **VPC** is set to default.
* **Auto-assign Public IP:** Enabled (so you can reach it via the web).


5. **Security Group:** Configure the **Virtual Firewall**.
* *Inbound Rule 1:* SSH (Port 22) from "My IP".
* *Inbound Rule 2:* HTTP (Port 80) from "Anywhere".


6. **Storage:** 8GB to 30GB of **EBS (gp3)** storage.
7. **User Data:** (Optional) Paste a script to auto-install Java or MySQL on the first boot.
8. **Launch:** Review and hit the orange button.

---

## **4. Using AWS Elastic Beanstalk (The "Auto-Pilot")**

If EC2 is "Manual," **Elastic Beanstalk** is "Automatic." It is a **PaaS** (Platform as a Service) that builds the N-tier architecture for you.

### **The Deployment Flow:**

1. **Create Application:** Give your project a name (e.g., `SettleSpot-App`).
2. **Select Platform:** Tell AWS your language (e.g., **Java** or **Node.js**).
3. **Upload Code:** Upload your `.war` or `.jar` file.
4. **Configuration:** Beanstalk automatically creates:
* **Load Balancer:** To distribute traffic.
* **Auto Scaling Group:** To add/remove servers automatically.
* **Security Groups:** To protect the servers.


5. **Environment:** Once launched, you get a **URL** (e.g., `myapp.us-east-1.elasticbeanstalk.com`) to access your app.

---

### **Quick Comparison: EC2 vs. Beanstalk**

* **EC2:** You are the mechanic. You change the oil, fix the engine, and drive the car. (Full Control).
* **Beanstalk:** You are the passenger. You tell the driver (AWS) where to go, and they handle the car maintenance. (Ease of Use).

---

[How to Create an EC2 Instance AWS (2026)](https://www.youtube.com/watch?v=5xc8M5WMM6s)
This video provides a practical, updated walkthrough for the exact console steps we discussed in today's lecture.

**Would you like me to create a "Cheat Sheet" of common Linux commands you'll need once you SSH into your new EC2 instance?**
