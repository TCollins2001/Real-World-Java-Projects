import java.util.*;

class Book {

    String bookTitle;

    Book(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
        return bookTitle;
    }
}

public class BookTracker {

    static void listBooks(List<Book> bookList) {
        System.out.println();
        System.out.println("Our Book List: ");
        System.out.println("---------------");
        for (Book book : bookList) {
            System.out.println(book.bookTitle);
        }
    }



    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Lord of the Rings"));
        bookList.add(new Book("The Da Vinci Code"));
        bookList.add(new Book("To Kill a Mockingbird"));
        bookList.add(new Book("Harry Potter and the Sorcerer's Stone"));
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("1. View Book List");
        System.out.println("2. Exit");

        System.out.println();

        System.out.print("Enter Number Choice: ");
        String userChoice = scanner.nextLine().trim();

        switch (userChoice) {
            case "1":
                listBooks(bookList);
                break;
            case "2":
                System.out.println();
                System.out.println("Exited :)");
                scanner.close();
                return;
            default:
                System.out.println("Invalid Entry. Try Again.");
        }
    }
}