import java.util.ArrayList;

public class Book {

    private String title;
    private String author;

    public Book(String t, String a) {
        title = t;
        author = a;
    }

    public void printBookInfo() {
        System.out.print(title + ", written by " + author);

    }
}

class PictureBook extends Book {
    String illustrator;

    public PictureBook(String title, String author, String illustrator) {
        super(title, author);
        this.illustrator = illustrator;
    }

    public void printBookInfo() {
        super.printBookInfo();
        System.out.print("and illustrated by " + illustrator);
    }
}

class BookListing {
    private float price;
    private Book book;

    public BookListing(Book book, float price) {
        this.price = price;
        this.book = book;
    }

    public void printDescription() {
        book.printBookInfo();
        System.out.println(", $" + price);
    }
}

class FRQ9 {
    public static void main(String[] args) {
        Book book1 = new Book("Frankenstein", "Mary Shelley");
        PictureBook book2 = new PictureBook("The Wonderful Wizard of Oz", "L. Frank Baum", "W.W. Denslow");

        ArrayList<Book> myLibrary = new ArrayList<Book>();
        myLibrary.add(book1);
        myLibrary.add(book2);
    }
}
