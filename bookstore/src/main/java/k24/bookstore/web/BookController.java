package k24.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import k24.bookstore.domain.Book;
import k24.bookstore.domain.BookRepository;

@Controller
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository repository;

    @GetMapping("/index")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/booklist")
    public String showBooklist(Model model) {
        model.addAttribute("bookList", repository.findAll());
        return "booklist";
    }

    @GetMapping("/addbook")
    public String addBook(Model model) {
        log.info("Create a new book...");
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String saveBook(@ModelAttribute ("book") Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

}
