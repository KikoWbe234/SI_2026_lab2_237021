import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class SI2026Lab2Test {

    @Test
    void testSearchEveryStatement_EmptyTitle() {
        Library library = new Library();
        // Тест за линијата каде што се фрла IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> library.searchBookByTitle(""));
    }

    @Test
    void testSearchEveryStatement_Success() {
        Library library = new Library();
        library.addBook(new Book("1984", "Orwell", "Dystopian"));
        List<Book> result = library.searchBookByTitle("1984");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testSearchEveryStatement_NoResults() {
        Library library = new Library();
        library.addBook(new Book("1984", "Orwell", "Dystopian"));
        assertNull(library.searchBookByTitle("The Hobbit"));
    }


    @Test
    void testBorrowEveryBranch_Success() {
        Library library = new Library();
        library.addBook(new Book("The Hobbit", "Tolkien", "Fantasy"));
        library.borrowBook("The Hobbit", "Tolkien");

        assertNull(library.searchBookByTitle("The Hobbit"));
    }

    @Test
    void testBorrowEveryBranch_AlreadyBorrowed() {
        Library library = new Library();
        Book b = new Book("1984", "Orwell", "Dystopian");
        b.setBorrowed(true);
        library.addBook(b);
        assertThrows(RuntimeException.class, () -> library.borrowBook("1984", "Orwell"));
    }

    @Test
    void testBorrowEveryBranch_NotFound() {
        Library library = new Library();
        library.addBook(new Book("1984", "Orwell", "Dystopian"));
        assertThrows(RuntimeException.class, () -> library.borrowBook("Clean Code", "Martin"));
    }

    @Test
    void borrowBookMultipleConditionTest() {
        Library library = new Library();

        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("", "Author"));

        assertThrows(IllegalArgumentException.class, () -> library.borrowBook("Title", ""));

        assertThrows(RuntimeException.class, () -> library.borrowBook("Title", "Author"));
    }

    @Test
    void searchBookMultipleConditionTest() {
        Library library = new Library();

        Book b1 = new Book("1984", "Orwell", "Dystopian");
        library.addBook(b1);
        assertNotNull(library.searchBookByTitle("1984"));

        Library library2 = new Library();
        Book b2 = new Book("The Hobbit", "Tolkien", "Fantasy");
        b2.setBorrowed(true);
        library2.addBook(b2);
        assertNull(library2.searchBookByTitle("The Hobbit"));
        
        Library library3 = new Library();
        library3.addBook(new Book("Clean Code", "Martin", "Tech"));
        assertNull(library3.searchBookByTitle("Other Title"));
    }
}