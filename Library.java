public class LibraryManagementSystem {

    // ================= BASE CLASS =================
    static class LibraryItem {

        private int itemId;
        private String title;
        private String author;
        protected boolean isAvailable;

        public LibraryItem(int itemId, String title, String author) {
            this.itemId = itemId;
            this.title = title;
            this.author = author;
            this.isAvailable = true;
        }

        public void checkout() {
            if (!isAvailable) {
                System.out.println("Item already checked out.");
            } else {
                isAvailable = false;
                System.out.println("Item checked out successfully.");
            }
        }

        public void returnItem() {
            if (isAvailable) {
                System.out.println("Item is already available.");
            } else {
                isAvailable = true;
                System.out.println("Item returned successfully.");
            }
        }

        public void displayDetails() {
            System.out.println("Item ID: " + itemId);
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("Available: " + isAvailable);
        }
    }

    // ================= BOOK CLASS =================
    static class Book extends LibraryItem {

        private String isbn;
        private String genre;

        public Book(int itemId, String title, String author, String isbn, String genre) {
            super(itemId, title, author);
            this.isbn = isbn;
            this.genre = genre;
        }

        @Override
        public void displayDetails() {
            super.displayDetails();
            System.out.println("ISBN: " + isbn);
            System.out.println("Genre: " + genre);
            System.out.println("------------------------");
        }
    }

    // ================= DVD CLASS =================
    static class DVD extends LibraryItem {

        private int duration; // in minutes
        private String rating;

        public DVD(int itemId, String title, String author, int duration, String rating) {
            super(itemId, title, author);
            this.duration = duration;
            this.rating = rating;
        }

        @Override
        public void displayDetails() {
            super.displayDetails();
            System.out.println("Duration: " + duration + " minutes");
            System.out.println("Rating: " + rating);
            System.out.println("------------------------");
        }
    }

    // ================= MAGAZINE CLASS =================
    static class Magazine extends LibraryItem {

        private int issueNumber;
        private String publicationDate;

        public Magazine(int itemId, String title, String author, int issueNumber, String publicationDate) {
            super(itemId, title, author);
            this.issueNumber = issueNumber;
            this.publicationDate = publicationDate;
        }

        @Override
        public void displayDetails() {
            super.displayDetails();
            System.out.println("Issue Number: " + issueNumber);
            System.out.println("Publication Date: " + publicationDate);
            System.out.println("------------------------");
        }
    }

    // ================= MAIN (POLYMORPHISM DEMO) =================
    public static void main(String[] args) {

        // Polymorphic array
        LibraryItem[] items = new LibraryItem[3];

        items[0] = new Book(1, "Java Programming", "James Gosling", "1234567890123", "Technology");
        items[1] = new DVD(2, "Inception", "Christopher Nolan", 148, "PG-13");
        items[2] = new Magazine(3, "Tech Monthly", "Various", 45, "Jan 2026");

        // Display all items
        for (LibraryItem item : items) {
            item.displayDetails();
        }

        // Checkout & return demo
        items[0].checkout();
        items[0].checkout(); // already checked out
        items[0].returnItem();
    }
}
