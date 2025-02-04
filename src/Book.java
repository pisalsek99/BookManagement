public class Book {
    private static int idCounter = 1;
    private int id;
    private String title;
    private Author author;
    private int publishedYear;
    private String status;

    public Book(String title, Author author, int publishedYear) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.status = "Available";
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public String getStatus() {
        return status;
    }

    public void borrowBook() {
        if (status.equals("Available")) {
            status = "Borrowed";
        }
    }

    public void returnBook() {
        status = "Available";
    }
}
