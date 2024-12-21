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


apt-get update && apt-get install -y python3-pip pip3 install lizard


- The pipeline runs `lizard src/main/java` and saves output to `lizard_report.txt`.

---

## 5. OWASP Dependency-Check

- **Option A**: Install the [OWASP Dependency-Check Plugin](https://plugins.jenkins.io/dependency-check-jenkins-plugin/) in Jenkins.
- **Option B**: Use Docker image `owasp/dependency-check` in a shell step:


docker run --rm -v $(pwd):/src -w /src owasp/dependency-check --scan . --format ALL --project "HelloWorld"

- **Option C**: Install on the agent machine (`dependency-check.sh`).

---

## 6. Notifications

- Email is set up in the `post` block of the `Jenkinsfile`.
- Update `to: "team@example.com"` with your actual email recipients.
- For Slack or Teams, install the corresponding Jenkins plugins and replace the email steps with your chat tool steps.

---

## 7. Common Issues & Troubleshooting

1. **SonarQube Connection Errors**  
 - Verify the SonarQube server is reachable and credentials are correct.  
2. **Quality Gate Timeout**  
 - If SonarQube analysis takes too long, increase the `timeout(time: 2, unit: 'MINUTES')` in `Jenkinsfile`.  
3. **No Coverage Detected**  
 - Make sure tests are actually running (`mvn clean test`), and the Jacoco exec file is generated in `target/jacoco.exec`.  
4. **Lizard Not Found**  
 - Ensure the `lizard` command is available on the path.  
5. **Dependency-Check Slow**  
 - The first run may be slow due to downloading the NVD database. Consider caching the data directory.

---

## 8. Extending the Pipeline

- Add Docker build stages if you want to containerize the app.
- Add deployment stages for your test/staging/production environments.
- Use a **Multibranch Pipeline** if your repo has multiple branches or PR builds.


