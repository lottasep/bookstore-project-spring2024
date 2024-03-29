package k24.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import k24.bookstore.domain.Book;
import k24.bookstore.domain.BookRepository;
import k24.bookstore.domain.Category;
import k24.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String showIndex() {
        log.info("Showing index page"); // LOG
        return "index";
    }

    @GetMapping("/booklist")
    public String showBooklist(Model model) {
        log.info("Showing book list"); // LOG
        model.addAttribute("bookList", repository.findAll());
       // model.addAttribute("categories", categoryRepository.findAll());
        log.info("Book list loaded"); // LOG
        return "booklist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addbook")
    public String addBook(Model model) {
        log.info("Navigating to add book form"); // LOG
        log.info("Create a new book..."); // LOG
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("savebook")
	public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        log.info("CONTROLLER: Save the book - Check validation of book: " + book);
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "addbook";
        }

		repository.save(book);
		return "redirect:/booklist";
	}

    /*
    
    @PostMapping("/addbook")
    public String saveBook(@ModelAttribute Book book,
            @RequestParam(value = "category", required = false) Long categoryId) {
        log.info("Saving new book: {}", book); // LOG
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            book.setCategory(category);
        } else {
            book.setCategory(null);
        }
        repository.save(book);
        return "redirect:booklist";
    }

    @PostMapping("/updatebook")
    public String updateBook(@ModelAttribute("editBook") Book book,
            @RequestParam(value = "category", required = false) Long categoryId) {
        log.info("Updating book: {}", book); // LOG
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            book.setCategory(category);
        } else {
            book.setCategory(null);
        }
        repository.save(book);
        return "redirect:/booklist";
    }

     */

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        log.info("Attempting to delete book with id: {}", id); // LOG
        log.info("delete book " + id); // LOG
        repository.deleteById(id);
        log.info("Book with id: {} deleted successfully, redirecting to book list", id); // LOG
        return "redirect:/booklist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editBook", repository.findById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editBook";
    }

}
