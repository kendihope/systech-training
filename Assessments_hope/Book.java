public class Book {

    // ===== Private Fields =====
    private String title;
    private String author;
    private String isbn;
    private double price;
    private boolean isAvailable;

    // ===== Constructor (uses setters for validation) =====
    public Book(String title, String author, String isbn, double price) {
        setTitle(title);
        setAuthor(author);
        setIsbn(isbn);
        setPrice(price);
        this.isAvailable = true; // default when created
    }

    // ===== Getters =====
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // ===== Setters with Validation =====

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
        this.author = author;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || !isbn.matches("\\d{13}")) {
            throw new IllegalArgumentException("ISBN must be exactly 13 digits.");
        }
        this.isbn = isbn;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    // ===== Borrow & Return Methods =====

    public void borrowBook() {
        if (!isAvailable) {
            System.out.println("Book is already borrowed.");
        } else {
            isAvailable = false;
            System.out.println("Book borrowed successfully.");
        }
    }

    public void returnBook() {
        if (isAvailable) {
            System.out.println("Book is already available.");
        } else {
            isAvailable = true;
            System.out.println("Book returned successfully.");
        }
    }

    // ===== toString() Method =====

    @Override
    public String toString() {
        return "Book {" +
                "Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", ISBN='" + isbn + '\'' +
                ", Price=" + price +
                ", Available=" + isAvailable +
                '}';
    }
}
