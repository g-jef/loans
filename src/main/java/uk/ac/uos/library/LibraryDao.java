package uk.ac.uos.library;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/** All of the database access for the catalogue and for issuing loans. */
@Repository
public class LibraryDao {

    /** Every book query selects the same columns, so they share one mapper. */
    private static final String SELECT_BOOKS =
            "SELECT b.id, b.isbn, b.title, b.author, "
          + "(SELECT COUNT(*) FROM loan l WHERE l.book_id = b.id AND l.returned_on IS NULL) AS live_loans "
          + "FROM book b ";

    private static final RowMapper<Book> BOOK_MAPPER = (rs, rowNum) -> new Book(
            rs.getInt("id"), rs.getString("isbn"), rs.getString("title"),
            rs.getString("author"), rs.getInt("live_loans") > 0);

    private final JdbcTemplate jdbc;

    public LibraryDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /** Books whose title or author contains the search term, in title order. */
    public List<Book> searchBooks(String term) {
        String sql = SELECT_BOOKS
                + "WHERE LOWER(b.title) LIKE '%" + term.toLowerCase() + "%' "
                + "   OR LOWER(b.author) LIKE '%" + term.toLowerCase() + "%' "
                + "ORDER BY b.title";
        return jdbc.query(sql, BOOK_MAPPER);
    }

    /** The book with this id, or null if there is no such book. */
    public Book findBook(int bookId) {
        List<Book> books = jdbc.query(SELECT_BOOKS + "WHERE b.id = ?", BOOK_MAPPER, bookId);
        return books.isEmpty() ? null : books.get(0);
    }

    /** True when the book is out with a member and has not been brought back. */
    public boolean isOnLoan(int bookId) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM loan WHERE book_id = ? AND returned_on IS NULL",
                Integer.class, bookId);
        return count != null && count > 0;
    }

    /** How many books this member currently has out. */
    public int countActiveLoans(String memberRef) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM loan WHERE member_ref = ? AND returned_on IS NULL",
                Integer.class, memberRef);
        return count == null ? 0 : count;
    }

    /** Records a new loan, issued today. */
    public void insertLoan(int bookId, String memberRef) {
        jdbc.update("INSERT INTO loan (book_id, member_ref, issued_on) VALUES (?, ?, CURRENT_DATE)",
                bookId, memberRef);
    }
}
