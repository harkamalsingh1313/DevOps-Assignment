# Pipeline Documentation

## Overview
This document explains how to set up, configure, and run the Jenkins pipeline defined in `Jenkinsfile`. It also covers troubleshooting steps.

---

## 1. Jenkins Setup

1. **Installation**:  
   - Install Jenkins on a VM or use a cloud-based Jenkins.  
2. **Plugins**:  
   - Git Plugin (to pull source code from Git repos)  
   - Maven Integration Plugin  
   - SonarQube Scanner for Jenkins  
   - JaCoCo Plugin for Jenkins  
   - OWASP Dependency-Check Plugin (optional; or run via command line / Docker)  
   - Email Extension Plugin (for sending build notifications)

3. **Global Tool Configuration**:  
   - Configure a **JDK** (e.g., Java 8 or Java 11).  
   - Configure **Maven**.  
   - Configure **SonarQube** credentials under **Manage Jenkins -> Configure System**.

---

## 2. SonarQube Integration

- In **Manage Jenkins -> Configure System**, find **SonarQube servers**.
- Add a new SonarQube installation named `MySonarQubeServer` (or whatever you prefer).
- Provide the server URL, authentication token, etc.

---

## 3. JaCoCo Coverage

- The `jacoco-maven-plugin` is included in `pom.xml`.
- The Jenkins JaCoCo plugin will publish coverage results from `target/jacoco.exec`.

---

## 4. Cyclomatic Complexity (Lizard)

- Install Lizard in your Jenkins agent:
