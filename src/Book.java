import java.util.ArrayList;

/**
 * represents a book.
 */
public class Book {
    /**
     * Book title.
     */
    final private String title;

    /**
     * Book author.
     */
    final private String author;

    /**
     * Book release year.
     */
    final private int releaseYear;

    /**
     * Book edition.
     */
    final private int edition;

    /**
     * Book availability.
     */
    private boolean status;

    /**
     * Reserve queue.
     */
    private ArrayList<String> reserveQueue;

    /**
     * Creates an instance of Book with default and specified values.
     * @param title - Book title.
     * @param author - Book author.
     * @param releaseYear - Book release year.
     * @param edition - Book edition.
     */
    Book(String title, String author, int releaseYear, int edition) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
        this.edition = edition;
        this.status = true;
        this.reserveQueue = new ArrayList<>();
    }

    /**
     * Lend the book.
     * @return - Whether book can be loaned or not.
     */
    public boolean loan() {
        if (this.status) {
            this.status = false;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return the book. Lends the book if there is a reserve queue.
     * @return - Whether book has been returned or not.
     */
    public boolean returnBook() {
        if (!this.status) {
            if (this.reserveQueue.isEmpty()) {
                this.status = true;
            } else {
                this.reserveQueue.removeFirst();
                System.out.println("Book lended to reserver.");
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns book title.
     * @return - Book title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns book availability.
     * @return - Book availability.
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Makes a book reservation if it's unavailable.
     * FIFO queue.
     * @return - Whether reservation has been made or not.
     */
    public boolean reserve(String name) {
        if (!this.status) {
            this.reserveQueue.add(name);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Book information.
     * @return - Book information.
     */
    public String toString() {
        return String.format("Title: %s\nAuthor: %s\nRelease Year: %d\nEdition: %d\nAvailable: "
                        + ((this.status) ? "Yes" : "No"),
                this.title, this.author, this.releaseYear, this.edition);
    }
}
