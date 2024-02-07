package k24.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import k24.bookstore.domain.BookRepository;

@Controller
public class BookController {
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

}
