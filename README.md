# Library Loans

A small Spring Boot web application for a library. Staff search a catalogue of
books and issue loans to library members.

## The task

Spend ten minutes reading the code. There is no need to run it - Screenshots of
the front end are below. 

After the ten minutes we will ask you to talk through your understanding
of the code, so consider what each file does. There will also be some
specific questions asked about the code.

Let us know if you spot any issues or bugs in the code too.

## Requirements

- The catalogue can be searched by title or author.
- A book that is already on loan cannot be issued again until it is returned.
- A member may hold at most five books at once.
- Loans may only be issued to registered members of the library.

## Screens

The catalogue lists every book and whether it is available:

![Catalogue](docs/screenshots/01-catalogue.png)

Searching by title or author narrows the list:

![Search results](docs/screenshots/02-search-results.png)

Choosing "Issue" opens a form for the member's reference:

![Issue form](docs/screenshots/03-issue-form.png)

![Issue form with a reference entered](docs/screenshots/04-issue-form-filled.png)

When the loan is issued, the catalogue is shown again with the book on loan:

![Loan issued](docs/screenshots/05-loan-issued.png)

A loan that breaks a rule is refused, and the form is shown again with the
reason:

![Book already on loan](docs/screenshots/06-book-already-on-loan.png)

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
