package library;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Reservation> reservations;
    private FileHandler fileHandler;
    private static final int FINE_PER_DAY = 10;
    private static final int BORROW_DAYS = 14;

    public Library() {
        fileHandler = new FileHandler();
        books = fileHandler.loadBooks();
        members = fileHandler.loadMembers();
        reservations = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (findBookByIsbn(book.getIsbn()) != null) {
            System.out.println("\u001B[31mError: Book with ISBN " + book.getIsbn() + " already exists.\u001B[0m");
            return;
        }
        books.add(book);
        saveData();
        System.out.println("\u001B[32mBook added successfully!\u001B[0m");
    }

    public void registerMember(Member member) {
        if (findMemberById(member.getMemberId()) != null) {
            System.out.println("\u001B[31mError: Member ID already exists.\u001B[0m");
            return;
        }
        members.add(member);
        saveData();
        System.out.println("\u001B[32mMember registered successfully!\u001B[0m");
    }

    public void borrowBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (book == null || member == null) {
            System.out.println("\u001B[31mInvalid ISBN or Member ID.\u001B[0m");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("\u001B[33mBook is currently unavailable. You can reserve it.\u001B[0m");
            return;
        }

        book.setAvailable(false);
        book.setBorrowedBy(memberId);
        book.setBorrowDate(LocalDate.now());
        book.setDueDate(LocalDate.now().plusDays(BORROW_DAYS));
        member.borrowBook(isbn);
        
        saveData();
        System.out.println("\u001B[32mBook borrowed successfully! Due back on " + book.getDueDate() + "\u001B[0m");
    }

    public void returnBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (book == null || member == null || book.isAvailable() || !memberId.equals(book.getBorrowedBy())) {
            System.out.println("\u001B[31mInvalid return transaction.\u001B[0m");
            return;
        }

        if (book.isOverdue()) {
            long daysOverdue = ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now());
            double fine = daysOverdue * FINE_PER_DAY;
            member.addFine(fine);
            System.out.println("\u001B[31mBook is overdue by " + daysOverdue + " days. Fine: \u20B9" + fine + "\u001B[0m");
        }

        book.setAvailable(true);
        book.setBorrowedBy(null);
        book.setBorrowDate(null);
        book.setDueDate(null);
        member.returnBook(isbn);

        checkReservations(isbn);
        saveData();
        System.out.println("\u001B[32mBook returned successfully.\u001B[0m");
    }

    public void reserveBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        if (book == null || book.isAvailable()) {
            System.out.println("\u001B[31mCannot reserve. Book either doesn't exist or is currently available.\u001B[0m");
            return;
        }
        reservations.add(new Reservation(isbn, memberId));
        System.out.println("\u001B[32mReservation added. You will be notified when it returns.\u001B[0m");
    }

    private void checkReservations(String isbn) {
        reservations.stream()
            .filter(r -> r.getIsbn().equals(isbn))
            .findFirst()
            .ifPresent(r -> {
                System.out.println("\u001B[36mNOTIFICATION: Reserved book " + isbn + " is now available for Member " + r.getMemberId() + "\u001B[0m");
                reservations.remove(r);
            });
    }

    public void displayBooksPaginated() {
        int pageSize = 5;
        for (int i = 0; i < books.size(); i += pageSize) {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-13s | %-25s | %-20s | %-6s | %-15s | %-20s |\n", "ISBN", "Title", "Author", "Year", "Category", "Status");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            books.stream().skip(i).limit(pageSize).forEach(Book::displayBook);
            if (i + pageSize < books.size()) {
                System.out.print("\u001B[33mPress ENTER for next page or type 'q' to quit: \u001B[0m");
                try {
                    if (System.in.read() == 'q') break;
                } catch (IOException ignored) {}
            }
        }
    }

    public void exportToCSV() {
        try (FileWriter fw = new FileWriter("data/books.csv")) {
            fw.append("ISBN,Title,Author,Year,Category,Status\n");
            for (Book b : books) {
                fw.append(String.join(",", b.getIsbn(), b.getTitle().replace(",", ""), 
                        b.getAuthor(), String.valueOf(b.getPublicationYear()), 
                        b.getCategory(), b.isAvailable() ? "Available" : "Borrowed"))
                  .append("\n");
            }
            System.out.println("\u001B[32mSuccessfully exported to data/books.csv\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31mExport failed: " + e.getMessage() + "\u001B[0m");
        }
    }

    public void showStatistics() { Statistics.display(books, members, reservations); }
    private Book findBookByIsbn(String isbn) { return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null); }
    private Member findMemberById(String id) { return members.stream().filter(m -> m.getMemberId().equals(id)).findFirst().orElse(null); }
    private void saveData() { fileHandler.saveBooks(books); fileHandler.saveMembers(members); }
}