# DevOps Engineer Assignment

This repository demonstrates a complete Jenkins CI/CD pipeline that integrates:
- **SonarQube** for code quality analysis
- **JaCoCo** for code coverage
- **Lizard** for cyclomatic complexity
- **OWASP Dependency-Check** for security vulnerability scans

## Project Overview

We have a simple Java "Hello World" application with JUnit tests. The `Jenkinsfile` defines stages to:
1. Check out code from Git
2. Build and run unit tests using Maven
3. Analyze code quality (SonarQube)
4. Generate code coverage reports (JaCoCo)
5. Calculate cyclomatic complexity (Lizard)
6. Perform dependency vulnerability scanning (OWASP Dependency-Check)
7. Send notifications upon success or failure

For instructions on how to set up Jenkins, see [Pipeline_Documentation.md](documentation/Pipeline_Documentation.md).  
For screenshots and reports, see [Pipeline_Reports.md](documentation/Pipeline_Reports.md).

## Quick Start

1. **Clone** this repository.
2. **Configure Jenkins** with the required plugins (Git, Maven Integration, SonarQube, JaCoCo, Email, etc.).
3. **Create a Jenkins job** (Pipeline job) pointing to this repo.
4. **Adjust** any environment variables or paths (e.g., SonarQube server name).
5. **Run** the build to see the pipeline in action.

## Contributing & Troubleshooting

Feel free to open issues or pull requests. Refer to [Pipeline_Documentation.md](documentation/Pipeline_Documentation.md) for troubleshooting common problems.

