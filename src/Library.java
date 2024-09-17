import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a library.
 */
public class Library {
    /**
     * Collection of books.
     */
    private ArrayList<Book> books;

    /**
     * Creates an instance of Library.
     */
    public Library() {
        books = new ArrayList<>();
    }

    /**
     * Provides an interface to the user with menu selection.
     */
    public void menu() {
        int menuSelection;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Library");
        System.out.println("What would you like to do?");
        while(true) {
            System.out.println("1. Add a book to the library");
            System.out.println("2. Search a book by title");
            System.out.println("3. List all available books");
            System.out.println("4. Return a book");
            System.out.println("5. Reserve a book");
            System.out.println("6. Exit");

            if (input.hasNextInt() && input.nextInt() > 0 && input.nextInt() < 7) {
                menuSelection = input.nextInt();
                input.nextLine();

                switch (menuSelection) {
                    case 1:
                        this.addBook();
                        break;
                    case 2:
                        this.searchBook();
                        break;
                    case 3:
                        this.listAvailableBooks();
                        break;
                    case 4:
                        this.returnBook();
                        break;
                    case 5:
                        System.out.println("Enter the book title to reserve: ");
                        this.reserveBook(input.nextLine());
                        break;
                    case 6:
                        System.out.println("Leaving library.");
                        System.exit(0);
                }

            } else {
                System.out.println("Please enter a valid option");
            }
        }
    }

    /**
     * Adds a book to the library.
     */
    private void addBook() {
        String title, author;
        int releaseYear, edition;

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Add a book to the library");
        System.out.println("Title: ");
        title = input.nextLine();
        System.out.println("Author: ");
        author = input.nextLine();
        System.out.println("Release Year: ");
        releaseYear = input.nextInt();
        input.nextLine();
        System.out.println("Edition: ");
        edition = input.nextInt();

        books.add(new Book(title, author, releaseYear, edition));
        System.out.println("Book added to the library.");
    }

    /**
     * Searches library for a book.
     */
    private void searchBook() {
        String bookTitle;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the book title: ");
        bookTitle = input.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                System.out.println(book);
                System.out.println("Do you want to lend the book? Y/n");
                if (input.nextLine().equalsIgnoreCase("Y")) {
                    if (book.loan()) {
                        System.out.println("Loan confirmation: " + book.getTitle());
                    } else {
                        System.out.println("Book is unavailable.");
                        System.out.println("Make reservation? Y/n");
                        if (input.nextLine().equalsIgnoreCase("Y")) {
                            this.reserveBook(book.getTitle());
                        }
                    }
                }
                return;
            }
        }
        System.out.println("Book not found");
    }

    /**
     * Lists available books.
     */
    private void listAvailableBooks() {
        int counter = 0;

        System.out.println("Available books: ");
        for (Book book : books) {
            if (book.getStatus()) {
                counter++;
                System.out.println(counter + book.getTitle());
            }
        }

        if (counter == 0) {
            System.out.println("No books available!");
        }
    }

    /**
     * Returns a book to the library.
     */
    private void returnBook() {
        String bookTitle;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the book title to return: ");
        bookTitle = input.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                if (book.returnBook()) {
                    System.out.println("Book returned: " + book.getTitle());
                } else {
                    System.out.println("Could not return the book.");
                }
                return;
            }
        }
        System.out.println("Book not found");
    }

    /**
     * Reserve a book.
     * @param bookTitle - Title of book.
     */
    private void reserveBook(String bookTitle) {
        Scanner input = new Scanner(System.in);

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                System.out.println("Enter your name: ");
                if (book.makeReservation(input.nextLine())) {
                    System.out.println("Reservation was successful.");
                } else {
                    System.out.println("Book is available. Lend it? Y/n");
                    if (input.nextLine().equalsIgnoreCase("Y")) {
                        if (book.loan()) {
                            System.out.println("Loan confirmation: " + book.getTitle());
                        } else {
                            System.out.println("Book is unavailable.");
                        }
                    } else {
                        System.out.println("Returning to menu.");
                    }

                }
            }
        }
    }
}
