package uk.ac.uos.library;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/** The rules the library applies before a book can leave the building. */
@Component
public class LoanValidator {

    /** A member may hold at most five books at any one time. */
    static final int MAX_ACTIVE_LOANS = 5;

    private final LibraryDao dao;

    public LoanValidator(LibraryDao dao) {
        this.dao = dao;
    }

    /** Returns one message per broken rule; an empty list means the loan is allowed. */
    public List<String> validate(String bookIdParam, String memberRef) {
        List<String> errors = new ArrayList<>();

        if (memberRef == null || memberRef.trim().isEmpty()) {
            errors.add("Enter a membership reference.");
            return errors;
        }

        int bookId;
        try {
            bookId = Integer.parseInt(bookIdParam);
        } catch (NumberFormatException e) {
            errors.add("Choose a book from the catalogue.");
            return errors;
        }

        Book book = dao.findBook(bookId);
        if (book == null) {
            errors.add("That book is not in the catalogue.");
            return errors;
        }

        if (dao.isOnLoan(bookId)) {
            errors.add("\"" + book.getTitle() + "\" is already out with another member.");
        }

        if (dao.countActiveLoans(memberRef.trim()) > MAX_ACTIVE_LOANS) {
            errors.add("Members may not hold more than " + MAX_ACTIVE_LOANS + " books at a time.");
        }

        return errors;
    }
}
