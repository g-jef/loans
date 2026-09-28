package uk.ac.uos.library;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Shows the "issue a book" form and records the loan when it is submitted. */
@Controller
public class IssueLoanController {

    private final LibraryDao dao;
    private final LoanValidator validator;

    public IssueLoanController(LibraryDao dao, LoanValidator validator) {
        this.dao = dao;
        this.validator = validator;
    }

    @GetMapping("/issue")
    public String showForm(@RequestParam("bookId") int bookId, Model model) {
        model.addAttribute("book", dao.findBook(bookId));
        return "issue";
    }

    @PostMapping("/issue")
    public String issue(@RequestParam("bookId") String bookId,
                        @RequestParam(name = "memberRef", required = false) String memberRef,
                        Model model) {

        List<String> errors = validator.validate(bookId, memberRef);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("memberRef", memberRef);
            model.addAttribute("book", findBook(bookId));
            return "issue";
        }

        dao.insertLoan(Integer.parseInt(bookId), memberRef.trim());
        return "redirect:/catalogue?issued=1";
    }

    /** The form is redisplayed even when the book id was not a number. */
    private Book findBook(String bookIdParam) {
        try {
            return dao.findBook(Integer.parseInt(bookIdParam));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
