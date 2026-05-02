# Book Author App

A Spring Boot application built using JSP, JPA, and an embedded H2 database to manage Books and Authors.

## Getting Started

1. Import this project as a Maven project in your preferred IDE (IntelliJ IDEA, Eclipse, VS Code).
2. The project contains a `pom.xml` with dependencies for Spring Boot, Data JPA, H2 Database, Web, and Tomcat Jasper (for JSP support).
3. The application uses an in-memory H2 database, which is populated automatically with 10 sample books and authors at startup via `DataInitializer.java`.
4. Run `BookAppApplication.java` from your IDE to start the Spring Boot Application.
5. Open your web browser and navigate to `http://localhost:8080/`. You will see the list of books.
6. The H2 Console is available at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, Username: `sa`, empty password).

## Submission Note
A file named `submission_report.md` has been generated containing the documentation answering the prompt's assignment requirements (Entity Relationship, Architecture, Implementation Details, and Challenges). You can export it to PDF (e.g., using a VS Code plugin or an online Markdown-to-PDF converter) and attach it for your submission.
