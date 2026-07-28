package library;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private String phone;
    private String email;
    private List<String> borrowedBooks;
    private double totalFine;

    public Member(String memberId, String name, String phone, String email) {
        this.memberId = memberId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
        this.totalFine = 0.0;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public List<String> getBorrowedBooks() { return borrowedBooks; }
    public double getTotalFine() { return totalFine; }

    public void addFine(double amount) { this.totalFine += amount; }
    public void payFine() { this.totalFine = 0; }

    public void borrowBook(String isbn) {
        borrowedBooks.add(isbn);
    }

    public void returnBook(String isbn) {
        borrowedBooks.remove(isbn);
    }

    public void displayMember() {
        System.out.printf("| %-10s | %-20s | %-12s | %-25s | Fines: ₹%-6.2f | Books: %d |\n",
                memberId, name, phone, email, totalFine, borrowedBooks.size());
    }
}