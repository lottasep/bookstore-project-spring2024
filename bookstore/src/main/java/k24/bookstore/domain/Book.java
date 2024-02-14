package k24.bookstore.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @Min(value = 1000)
    @Max(value = 2024)
    private int publicationYear;
    @NotBlank
    private String isbn;
    @NotNull
    private BigDecimal price;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Book() {
    }

    /*
     * public Book(String title, String author, int publicationYear, String isbn,
     * BigDecimal price) {
     * this.title = title;
     * this.author = author;
     * this.publicationYear = publicationYear;
     * this.isbn = isbn;
     * this.price = price;
     * }
     */

    public Book(String title, String author, int publicationYear, String isbn, BigDecimal price, Category category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    	public static List<String> getAllCategories(List<Book> books) {
		List<String> categories = new ArrayList<>();
		for (Book book : books) {
			if (!categories.contains(book.getCategory().getName())) {
				categories.add(book.getCategory().getName());
			}
		}
		return categories;
	}

    /*
     * @Override
     * public String toString() {
     * return "Book [id=" + id + ", title=" + title + ", author=" + author +
     * ", publicationYear=" + publicationYear
     * + ", isbn=" + isbn + ", price=" + price + "]";
     * }
     */

    @Override
    public String toString() {
        if (this.category != null) {
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
                    + ", isbn=" + isbn + ", price=" + price + ", category=" + this.category.getName() + "]";
        } else {
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
                    + ", isbn=" + isbn + ", price=" + price + "]";
        }
    }

}
