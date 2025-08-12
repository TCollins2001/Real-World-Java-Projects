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

        static void borrowBooks(List<Book> bookList, Scanner scanner) {

            while (true) {
                listBooks(bookList);
                System.out.println();
                System.out.print("Enter Book Title: ");
                String userBorrowed = scanner.nextLine().trim();

                System.out.println();

                if (userBorrowed.isEmpty()) {
                    System.out.println("Empty Entry. Try Again.");
                    return;
                }

                boolean bookFound = false;

                Iterator<Book> iterator = bookList.iterator();

                while (iterator.hasNext()) {
                    Book book = iterator.next();
                        if (book.bookTitle.equalsIgnoreCase(userBorrowed)) {
                            bookFound = true;
                            System.out.println("You Borrowed " + book.bookTitle + "!");
                            iterator.remove();
                            break;
                        }
                    }

                if (!bookFound) {
                    break;
                }
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
        System.out.println("2. Borrow Book");
        System.out.println("3. Exit");

        System.out.println();

        System.out.print("Enter Number Choice: ");
        String userChoice = scanner.nextLine().trim();

        switch (userChoice) {
            case "1":
                listBooks(bookList);
                break;
            case "2":
                borrowBooks(bookList, scanner);
                break;
            case "3":
                System.out.println();
                System.out.println("Exited :)");
                scanner.close();
                return;
            default:
                System.out.println("Invalid Entry. Try Again.");
        }
    }
}