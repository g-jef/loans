# Library Loans

A small Spring Boot web application for a library. Staff search a catalogue of
books and issue loans to library members.

## The task

Spend about ten minutes reading the code, then talk us through it: what the
application does, how a request moves through it, and anything you would
question or change. There is no need to run it.

## Requirements

- The catalogue can be searched by title or author.
- A book that is already on loan cannot be issued again until it is returned.
- A member may hold at most five books at once.
- Loans may only be issued to registered members of the library.

## Layout

| File | Purpose |
| --- | --- |
| `LibraryLoansApplication.java` | Starts the application |
| `Book.java` | A book in the catalogue |
| `LibraryDao.java` | Database access, using `JdbcTemplate` |
| `LoanValidator.java` | Checks made before a loan is issued |
| `CatalogueController.java` | `GET /catalogue`: search and list books |
| `IssueLoanController.java` | `GET/POST /issue`: the loan form |
| `WEB-INF/views/*.jsp` | The two pages |
| `schema.sql`, `data.sql` | Tables and sample data |
| `application.properties` | Server, database and view configuration |

## Data model

```mermaid
erDiagram
    library_member ||--o{ loan : "borrows"
    book ||--o{ loan : "is lent in"

    library_member {
        VARCHAR(10) member_ref PK
        VARCHAR(60) full_name
    }
    book {
        INT id PK
        VARCHAR(13) isbn
        VARCHAR(120) title
        VARCHAR(60) author
    }
    loan {
        INT id PK
        INT book_id FK
        VARCHAR(10) member_ref FK
        DATE issued_on
        DATE returned_on "null while the book is out"
    }
```
