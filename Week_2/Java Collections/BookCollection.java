import java.util.LinkedHashSet;

public class BookCollection {
    LinkedHashSet<String> books = new LinkedHashSet<>();

    void addBook(String bookTitle) {
        books.add(bookTitle);
    }

    void removeBook(String bookTitle) {
        books.remove(bookTitle);
    }

    void displayBooks() {
        System.out.println(books);
    }
}

class BookCollectionTest {
    public static void main(String[] args) {
        BookCollection collection = new BookCollection();
        collection.addBook("Book1");
        collection.addBook("Book2");
        collection.addBook("Book3");
        collection.displayBooks();
        collection.removeBook("Book2");
        collection.displayBooks();
    }
}
