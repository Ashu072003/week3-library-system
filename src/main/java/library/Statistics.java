package library;

import java.util.List;

public class Statistics {
    public static void display(List<Book> books, List<Member> members, List<Reservation> reservations) {
        long available = books.stream().filter(Book::isAvailable).count();
        long overdue = books.stream().filter(Book::isOverdue).count();
        double totalFine = members.stream().mapToDouble(Member::getTotalFine).sum();

        System.out.println("\n==========================================");
        System.out.println("\u001B[36m          LIBRARY STATISTICS LOG          \u001B[0m");
        System.out.println("==========================================");
        System.out.println("Total Books         : " + books.size());
        System.out.println("Available Books     : " + available);
        System.out.println("Borrowed Books      : " + (books.size() - available));
        System.out.println("Overdue Books       : \u001B[31m" + overdue + "\u001B[0m");
        System.out.println("Registered Members  : " + members.size());
        System.out.println("Active Reservations : " + reservations.size());
        System.out.println("Total Fine Collected: \u001B[32m\u20B9" + totalFine + "\u001B[0m");
        System.out.println("==========================================\n");
    }
}