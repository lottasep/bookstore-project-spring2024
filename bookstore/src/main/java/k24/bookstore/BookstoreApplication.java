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

@SpringBootApplication
public class BookstoreApplication {
    private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

    @Bean
    public CommandLineRunner testData(BookRepository repository) {
        return (args) -> {
            log.info("saving some books");
            repository.save(new Book("Linna", "Franz Kafka", 2014, "9789529902262", BigDecimal.valueOf(23.90)));
            repository.save(new Book("Python Crash Course", "Eric Matthes", 2023, "9781718502703", BigDecimal.valueOf(42.00)));

            log.info("get all books");
            for (Book book: repository.findAll()) {
                log.info(book.toString());
            }
        };
    }
}
