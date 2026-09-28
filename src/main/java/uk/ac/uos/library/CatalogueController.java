package uk.ac.uos.library;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Searches the catalogue and shows the results. */
@Controller
public class CatalogueController {

    private final LibraryDao dao;

    public CatalogueController(LibraryDao dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/catalogue";
    }

    @GetMapping("/catalogue")
    public String catalogue(@RequestParam(name = "q", defaultValue = "") String term, Model model) {
        model.addAttribute("books", dao.searchBooks(term));
        model.addAttribute("term", term);
        return "catalogue";
    }
}
