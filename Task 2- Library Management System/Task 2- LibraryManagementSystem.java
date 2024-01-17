import java.util.ArrayList;
import java.util.Scanner;

class Book{
    private String title;
    private String author;
    private boolean checkedOut;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.checkedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void checkOut() {
        checkedOut = true;
    }

    public void returnBook() {
        checkedOut = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Status: " + (checkedOut ? "Checked Out" : "Available");
    }
}

class LibraryCatalog {
    private ArrayList<Book> books;

    public LibraryCatalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found: " + book);
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    public void searchByAuthor(String author) {
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println("Book found: " + book);
                return;
            }
        }
        System.out.println("Book by author '" + author + "' not found.");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Books in the library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isCheckedOut()) {
                    book.checkOut();
                    System.out.println("Book checked out: " + book);
                } else {
                    System.out.println("Book '" + title + "' is already checked out.");
                }
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isCheckedOut()) {
                    book.returnBook();
                    System.out.println("Book returned: " + book);
                } else {
                    System.out.println("Book '" + title + "' is not checked out.");
                }
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }
}

public class LibraryManagementSystem {

    public static void main(String[] args) {
        LibraryCatalog library = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Search by Title");
            System.out.println("3. Search by Author");
            System.out.println("4. Display All Books");
            System.out.println("5. Check Out Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchByTitle(searchTitle);
                    break;
                case 3:
                    System.out.print("Enter author to search: ");
                    String searchAuthor = scanner.nextLine();
                    library.searchByAuthor(searchAuthor);
                    break;
                case 4:
                    library.displayAllBooks();
                    break;
                case 5:
                    System.out.print("Enter title to check out: ");
                    String checkoutTitle = scanner.nextLine();
                    library.checkOutBook(checkoutTitle);
                    break;
                case 6:
                    System.out.print("Enter title to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 7:
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }
}