# Book and Author Management System

## 1. Entity Relationship Design
The system contains two main entities: `Book` and `Author`.
- **Author**: Represents a book author. Attributes include `id` and `name`.
- **Book**: Represents a book written by an author. Attributes include `id`, `title`, and `isbn`.

**Relationship**: There is a one-to-many relationship between `Author` and `Book`. One author can write multiple books, but in this implementation, each book has exactly one primary author. 
This is modeled using JPA annotations:
- `@OneToMany(mappedBy = "author")` on the `Author` side.
- `@ManyToOne` and `@JoinColumn(name = "author_id")` on the `Book` side.

## 2. Implementation Details

### Populate Database
We used Spring Boot's `CommandLineRunner` via a `DataInitializer` bean to populate the database on startup. We generated 10 authors and 10 books and saved them using the `saveAll` method from our repository interfaces. H2 database is used in-memory for immediate availability.

### Create Operation
- **View**: A JSP form (`addBook.jsp`) allows the user to input the title, ISBN, and select an author from a dynamically populated dropdown menu.
- **Controller**: Handled via `@PostMapping("/add")` in `BookController`. It captures form data and uses `BookService` to save the entity. Exception handling intercepts `DataIntegrityViolationException` to provide meaningful errors to the user.

### Read Operation
- **View**: The `list.jsp` page contains an HTML table to display all records.
- **Controller**: `BookController`'s `@GetMapping("/")` handles the display, fetching all records via `BookService.getAllBooks()`.
- **Custom Query**: We used a custom JPQL query inside `BookRepository` to prevent the N+1 select problem:
```java
@Query("SELECT b FROM Book b JOIN FETCH b.author")
List<Book> findAllBooksWithAuthors();
```

### Update Operation
- **View**: Handled by `updateBook.jsp`, reusing a form populated with the existing data.
- **Controller**: `@GetMapping("/edit/{id}")` pre-populates the data. `@PostMapping("/edit/{id}")` handles the persistence of the changes using `BookService.updateBook(id, book)`.

## 3. Testing
Tests are implemented using `JUnit 5` and `Mockito`.
- `BookServiceTest`: Mocks `BookRepository` to verify business logic, testing `saveBook`, `getAllBooks`, and `updateBook` in isolation.
- `BookRepositoryTest`: A `@DataJpaTest` to test custom repository query methods with an actual H2 database context.

## 4. Challenges Faced and Solutions
1. **Handling N+1 Select Problem**: 
   - *Challenge*: Initially, fetching all books caused a separate SQL query to run for every single author associated with the books, hurting performance.
   - *Solution*: A custom `@Query` was implemented using `JOIN FETCH` to retrieve the books and authors in a single SQL inner join operation.
2. **Form Data Binding with JPA Entities**:
   - *Challenge*: Tying a selected author from the HTML `<select>` tag to a complex JPA `Author` entity in the controller.
   - *Solution*: We bound the select's `name` attribute to `author.id` in JSP, allowing Spring to map the provided ID and attach the author association successfully.

## 5. Github URL
*(Insert your Github Repository URL here)*

*(Note: Please export this markdown file to PDF using an extension like Markdown PDF in VS Code, or an online converter, before submission.)*
