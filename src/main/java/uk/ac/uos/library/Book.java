package uk.ac.uos.library;

/** A single title in the library catalogue. */
public class Book {

    private final int id;
    private final String isbn;
    private final String title;
    private final String author;
    private final boolean onLoan;

    public Book(int id, String isbn, String title, String author, boolean onLoan) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.onLoan = onLoan;
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isOnLoan() {
        return onLoan;
    }
}
