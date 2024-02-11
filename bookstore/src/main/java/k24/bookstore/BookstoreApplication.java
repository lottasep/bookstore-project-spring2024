package k24.bookstore;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k24.bookstore.domain.Book;
import k24.bookstore.domain.BookRepository;
import k24.bookstore.domain.Category;
import k24.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

    @Bean
    public CommandLineRunner testData(BookRepository repository, CategoryRepository categoryRepository) {
        return (args) -> {
            log.info("saving some categories");
            Category sciFiCategory = categoryRepository.save(new Category("Science Fiction"));
            Category computerCategory = categoryRepository.save(new Category("Computers & IT"));
            Category childernsCategory = categoryRepository.save(new Category("Children's Books"));

            log.info("saving some books");
            repository.save(new Book("Dune", "Frank Herbert", 2015, "9780340960196", BigDecimal.valueOf(11.50), sciFiCategory));
            repository.save(new Book("Python Crash Course", "Eric Matthes", 2023, "9781718502703", BigDecimal.valueOf(42.00), computerCategory));
            repository.save(new Book("The Very Hungry Caterpillar", "Eric Carle", 1994, "9780241003008", BigDecimal.valueOf(27.40), childernsCategory));

            log.info("get all books");
            for (Book book: repository.findAll()) {
                log.info(book.toString());
            }
        };
    }
}
