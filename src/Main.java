import org.nocrala.tools.texttablefmt.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Main {
    static Book[] bookArray = new Book[3];
    static int bookCount = 0;
    public static void main(String[] args) {
        bookArray[bookCount++] = new Book("Java Programming", new Author("James Gosling", 1975, -1), 1995);
        bookArray[bookCount++] = new Book("Effective Java", new Author("Joshua Bloch", 1995, -1), 2001);
        bookArray[bookCount++] = new Book("Clean Code", new Author("Robert C. Martin", 1970, -1), 2008);
        Scanner sc = new Scanner(System.in);
        String name = "", address = "";
        String redColor = "\u001B[31m";
        String resetColor = "\u001B[0m";
        String blueColor = "\u001B[34m";

        System.out.println("========= SET UP LIBRARY =========");
        while (true) {
            if (name.isEmpty()) {
                System.out.print("=> Enter Library's Name: ");
                name = sc.nextLine().trim();
                if (!name.matches("^[a-zA-Z\\s]+$")) {
                    System.out.println(redColor + "Invalid name! Please enter only letters and spaces." + resetColor);
                    name = "";
                    continue;
                }
                name = name.toUpperCase();
            }
            if (address.isEmpty()) {
                System.out.print("=> Enter Library's Address: ");
                address = sc.nextLine().trim();
                if (!address.matches("^[a-zA-Z\\s]+$")) {
                    System.out.println(redColor + "Invalid address! Please enter only letters and spaces." + resetColor);
                    address = "";
                    continue;
                }
                address = address.toUpperCase();
            }
            break;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date date = new Date();

        System.out.println(blueColor + "\n\"" + name + "\" Library is already created in \"" + address + "\" address");
        System.out.println("Successfully created on " + formatter.format(date) + "\n" + resetColor);


        displayMenu(name, address, sc);
    }

    public static void displayMenu(String name, String address, Scanner sc) {
        String blueColor = "\u001B[34m";
        String resetColor = "\u001B[0m";
        while (true) {
            System.out.println("========= " + name + " LIBRARY, " + address + " =========");
            System.out.println("1- Add Book ");
            System.out.println("2- Show All Books ");
            System.out.println("3- Show Available Books ");
            System.out.println("4- Borrow Book ");
            System.out.println("5- Return Book ");
            System.out.println("6- Exit");
            System.out.println("-----------------------------------------");
            System.out.print("Enter your choice: ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("\u001B[34mInvalid input! Please enter a number between 1 and 6.\u001B[0m");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("========= ADD BOOK INFO =========");
                    String authorName;
                    String title;
                    int yearActive;

                    // Title Validation
                    while (true) {
                        System.out.print("=> Enter Book's Name: ");
                        title = sc.nextLine().trim();

                        if (title.matches("^[a-zA-Z\\s]+$")) {
                            break;
                        }
                        System.out.println("\u001B[31mInvalid book name! Please enter only letters and spaces.\u001B[0m");
                    }

                    // Author Name Validation
                    while (true) {
                        System.out.print("=> Enter Book Author Name: ");
                        authorName = sc.nextLine().trim();

                        if (authorName.matches("^[a-zA-Z\\s]+$")) {
                            break;
                        }
                        System.out.println("\u001B[31mInvalid author name! Please enter only letters and spaces.\u001B[0m");
                    }


                    while (true) {
                        System.out.print("=> Enter Author Year Active: ");
                        if (sc.hasNextInt()) {
                            yearActive = sc.nextInt();
                            sc.nextLine();

                            if (yearActive <= 2025) {
                                break;
                            } else {
                                System.out.println("\u001B[31mInvalid input! Year Active must not be greater than 2025.\u001B[0m");
                            }
                        } else {
                            System.out.println("\u001B[31mInvalid input! Please enter a valid year.\u001B[0m");
                            sc.nextLine();
                        }
                    }

                    boolean isAlive;
                    while (true) {
                        System.out.print("=> Is the author still alive? (yes/no or y/n): ");
                        String response = sc.nextLine().trim().toLowerCase();

                        if (response.equals("y") || response.equals("yes")) {
                            isAlive = true;
                            break;
                        } else if (response.equals("n") || response.equals("no")) {
                            isAlive = false;
                            break;
                        } else {
                            System.out.println("\u001B[31mInvalid input! Please enter 'yes' or 'no' or 'y' or 'n'.\u001B[0m");
                        }
                    }

                    int yearDie = -1;
                    if (!isAlive) {
                        while (true) {
                            System.out.print("=> Enter Author Year Die: ");
                            if (sc.hasNextInt()) {
                                yearDie = sc.nextInt();
                                sc.nextLine();

                                if (yearDie > yearActive) {
                                    break;
                                } else {
                                    System.out.println("\u001B[31mInvalid input! Year Die must be greater than Year Active.\u001B[0m");
                                }
                            } else {
                                System.out.println("\u001B[31mInvalid input! Please enter a valid year.\u001B[0m");
                                sc.nextLine();
                            }
                        }
                    }

                    int publishedYear;
                    while (true) {
                        System.out.print("=> Enter Published Year: ");
                        if (sc.hasNextInt()) {
                            publishedYear = sc.nextInt();
                            sc.nextLine();

                            if (publishedYear <= 2025) {
                                if (publishedYear >= (yearActive + 18)) {
                                    break;
                                } else {
                                    System.out.println("\u001B[31mInvalid input! Published Year must be at least 18 years after the Author's Year Active.\u001B[0m");
                                }
                            } else {
                                System.out.println("\u001B[31mInvalid input! Published Year must not be greater than 2025.\u001B[0m");
                            }
                        } else {
                            System.out.println("\u001B[31mInvalid input! Please enter a valid year.\u001B[0m");
                            sc.nextLine();
                        }
                    }

                    if (bookCount == bookArray.length) {
                        resizeArray();
                    }

                    Author author = new Author(authorName, yearActive, yearDie);
                    bookArray[bookCount++] = new Book(title, author, publishedYear);

                    System.out.println(blueColor + "Book added successfully!" + resetColor);
                    break;

                case 2:
                    System.out.println("========= ALL BOOKS =========");

                    if (bookCount == 0) {
                        System.out.println("No books available in the library.");
                    } else {
                        Table table = new Table(7);
                        table.addCell(String.format("%-10s", "ID"));
                        table.addCell(String.format("%-20s", "Title"));
                        table.addCell(String.format("%-20s", "Author"));
                        table.addCell(String.format("%-20s", "Year Active"));
                        table.addCell(String.format("%-20s", "Year Die"));
                        table.addCell(String.format("%-20s", "Published"));
                        table.addCell(String.format("%-20s", "Status"));

                        for (int i = 0; i < bookCount; i++) {
                            Book book = bookArray[i];
                            String statusColor = book.getStatus().equalsIgnoreCase("Available") ? "\u001B[34m" : "\u001B[31m";
                            String statusReset = "\u001B[0m";
                            table.addCell(String.format("%-10s", book.getId()));
                            table.addCell(String.format("%-20s", book.getTitle()));
                            table.addCell(String.format("%-20s", book.getAuthor().getName()));
                            table.addCell(String.format("%-20s", book.getAuthor().getYearActive()));
                            table.addCell(String.format("%-20s", book.getAuthor().getYearDie()));
                            table.addCell(String.format("%-20s", book.getPublishedYear()));
                            table.addCell(String.format("%-20s", statusColor + book.getStatus() + statusReset));
                        }

                        System.out.println(table.render());
                    }
                    break;


                case 3:
                    System.out.println("\n========= AVAILABLE BOOKS =========");

                    if (bookCount == 0) {
                        System.out.println("\u001B[31mNo books available in the library.\u001B[0m");
                        break;
                    }
                    Table table = new Table(5);

                    table.addCell(String.format("%-10s","ID"));
                    table.addCell(String.format("%-20s","Title"));
                    table.addCell(String.format("%-20s","Author"));
                    table.addCell(String.format("%-20s","Public Date"));
                    table.addCell(String.format("%-20s","Status"));

                    boolean found = false;

                    for (int i = 0; i < bookCount; i++) {
                        if (bookArray[i].getStatus().equalsIgnoreCase("Available")) {
                            String statusColor = "\u001B[34m";
                            String statusReset = "\u001B[0m";

                            table.addCell(String.valueOf(bookArray[i].getId()));
                            table.addCell(bookArray[i].getTitle());
                            table.addCell(bookArray[i].getAuthor().getName());
                            table.addCell(String.valueOf(bookArray[i].getPublishedYear()));
                            table.addCell(statusColor + bookArray[i].getStatus() + statusReset);

                            found = true;
                        }
                    }

                    if (found) {
                        System.out.println(table.render());
                    } else {
                        System.out.println("\u001B[31mNo available books at the moment.\u001B[0m");
                    }
                    System.out.println();
                    break;

                case 4:
                    System.out.println("\n========= BORROW BOOK INFO =========");
                    System.out.print("=> Enter Book ID to Borrow: ");
                    int idBorrowed = sc.nextInt();
                    sc.nextLine();

                    boolean bookFound = false;

                    for (int i = 0; i < bookCount; i++) {
                        if (bookArray[i].getId() == idBorrowed) {
                            bookFound = true;

                            if (bookArray[i].getStatus().equalsIgnoreCase("Available")) {
                                bookArray[i].borrowBook();
                                System.out.println("\nBook ID : " + bookArray[i].getId());
                                System.out.println("Book Title : " + bookArray[i].getTitle());
                                System.out.println("Book Author : " + bookArray[i].getAuthor().getName() +
                                        " (" + bookArray[i].getAuthor().getYearActive() + "-" + bookArray[i].getAuthor().getYearDie() + ")");
                                System.out.println("Published Year : " + bookArray[i].getPublishedYear() +
                                        " is borrowed successfully...\n");
                            } else {
                                System.out.println("\u001B[31mBook ID : " + idBorrowed + " is already borrowed!\u001B[0m\n");
                            }
                            break;
                        }
                    }
                    if (!bookFound) {
                        System.out.println("\u001B[31mBook ID : " + idBorrowed + " not Exist...\u001B[0m\n");
                    }
                    break;

                case 5:

                    System.out.println("\n========= RETURN BOOK INFO =========");
                    System.out.print("=> Enter Book ID to Return: ");
                    int idReturn = sc.nextInt();
                    sc.nextLine();
                    boolean bookFoundReturn = false;

                    for (int i = 0; i < bookCount; i++) {
                        if (bookArray[i].getId() == idReturn) {
                            bookFoundReturn = true;

                            if (bookArray[i].getStatus().equalsIgnoreCase("Borrowed")) {
                                bookArray[i].returnBook();
                                System.out.println("\nBook ID : " + bookArray[i].getId());
                                System.out.println("Book Title : " + bookArray[i].getTitle());
                                System.out.println("Book Author : " + bookArray[i].getAuthor().getName() +
                                        " (" + bookArray[i].getAuthor().getYearActive() + "-" + bookArray[i].getAuthor().getYearDie() + ")");
                                System.out.println("Published Year : " + bookArray[i].getPublishedYear() +
                                        " is returned successfully...\n");
                            } else {
                                System.out.println("\u001B[31mBook ID : " + idReturn + " failed to return...\u001B[0m\n");
                            }
                            break;
                        }
                    }
                    if (!bookFoundReturn) {
                        System.out.println("\u001B[31mBook ID : " + idReturn + " not Exist...\u001B[0m\n");
                    }
                    break;
                case 6:
                    System.out.println("\n========= SET PAGE  =========");
                    break;
                case 7:
                    System.out.println("\n========= DELETE BOOK BY ID =========");
                    int idDelete = -1;
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("=> Enter Book ID to Delete: ");
                        if (sc.hasNextInt()) {
                            idDelete = sc.nextInt();
                            sc.nextLine();

                            if (idDelete > 0 && idDelete <= bookCount) {
                                validInput = true;
                            } else {
                                System.out.println("\u001B[31mInvalid Book ID! Please enter a valid existing book ID.\u001B[0m");
                            }
                        } else {
                            System.out.println("\u001B[31mInvalid input! Please enter a number.\u001B[0m");
                            sc.next();
                        }
                    }

                    boolean isBookFound = false;
                    int deleteIndex = -1;

                    for (int i = 0; i < bookCount; i++) {
                        if (bookArray[i].getId() == idDelete) {
                            bookFound = true;
                            deleteIndex = i;

                            if (!bookArray[i].getStatus().equalsIgnoreCase("Available")) {
                                System.out.println("\u001B[31mCannot delete! Book is currently borrowed.\u001B[0m");
                                break;
                            }

                            System.out.println("\nBook ID : " + bookArray[i].getId());
                            System.out.println("Book Title : " + bookArray[i].getTitle());
                            System.out.println("Book Author : " + bookArray[i].getAuthor().getName() +
                                    " (" + bookArray[i].getAuthor().getYearActive() + "-" + bookArray[i].getAuthor().getYearDie() + ")");
                            System.out.println("Published Year : " + bookArray[i].getPublishedYear());

                            System.out.print("\nAre you sure you want to delete this book? (yes/no): ");
                            String confirm = sc.nextLine().trim().toLowerCase();

                            if (confirm.equals("yes") || confirm.equals("y")) {
                                for (int j = deleteIndex; j < bookCount - 1; j++) {
                                    bookArray[j] = bookArray[j + 1];
                                }
                                bookArray[bookCount - 1] = null;
                                bookCount--;

                                System.out.println("\n\u001B[32mBook ID : " + idDelete + " deleted successfully!\u001B[0m\n");
                            } else {
                                System.out.println("\n\u001B[31mBook deletion canceled.\u001B[0m\n");
                            }
                            break;
                        }
                    }
                    break;


                case 8:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 8.");
            }
            System.out.println();
        }
    }

    // resize array
    public static void resizeArray() {
        int newSize = bookArray.length * 2;
        Book[] newArray = new Book[newSize];

        for (int i = 0; i < bookArray.length; i++) {
            newArray[i] = bookArray[i];
        }
        bookArray = newArray;
    }
}