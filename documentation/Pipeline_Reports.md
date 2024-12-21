# Pipeline Reports

This document summarizes the initial run of the pipeline, along with screenshots from Jenkins and the associated tools.

---

## 1. Jenkins Dashboard

![Jenkins Dashboard](../images/jenkins_dashboard.png)  
Shows the build job, build history, status, and stage view.

---

## 2. SonarQube Analysis Report

![SonarQube Analysis](../images/sonar_report.png)  
Displays code smells, bugs, and vulnerabilities. Also includes coverage metrics when integrated with JaCoCo.

---

## 3. JaCoCo Code Coverage

![JaCoCo Coverage](../images/jacoco_coverage.png)  
Demonstrates line and branch coverage. The pipeline publishes these results to Jenkins.

---

## 4. Cyclomatic Complexity - Lizard

![Lizard Complexity](../images/lizard_report.png)  
Text-based report showing complexity for each method/class. Lower is generally better.

---

## 5. OWASP Dependency-Check

![OWASP Dependency-Check](../images/owasp_dependency_check.png)  
Indicates any detected CVEs in the project dependencies. Critical issues should break the build.

---

## Conclusion

- The code passed the SonarQube Quality Gate with no major vulnerabilities.
- Code coverage is around XX% (based on the JaCoCo report).
- Cyclomatic complexity is low (simple "HelloWorld" example).
- No critical security vulnerabilities found in dependencies.

