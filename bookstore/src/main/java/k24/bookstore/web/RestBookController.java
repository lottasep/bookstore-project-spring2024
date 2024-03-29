package k24.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import k24.bookstore.domain.Book;
import k24.bookstore.domain.BookRepository;

@RestController
public class RestBookController {

    private static final Logger log = LoggerFactory.getLogger(RestBookController.class); // LOG

    @Autowired
    private BookRepository repository;

    // Fetch all books #1
    @RequestMapping(value="/allbooks", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        log.info("get and return all books"); // LOG
        return (List<Book>) repository.findAll();
    }

    // Fetch all books #2
    @GetMapping("/books")
	public Iterable<Book> getBooks() {
		log.info("get and return all books"); // LOG
		return repository.findAll();
	}

    @GetMapping("/book/{id}")
	public Optional<Book> getBookById(@PathVariable("id") Long id) {
		log.info("get and return book with id: " + id); // LOG
		return repository.findById(id);
	}

}
