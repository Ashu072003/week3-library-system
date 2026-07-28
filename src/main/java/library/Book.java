package library;

import java.time.LocalDate;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private String category;
    private boolean isAvailable;
    private String borrowedBy;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public Book(String isbn, String title, String author, int publicationYear, String category) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.category = category;
        this.isAvailable = true;
    }

    // Getters and Setters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return isAvailable; }
    public String getBorrowedBy() { return borrowedBy; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }

    public void setAvailable(boolean available) { isAvailable = available; }
    public void setBorrowedBy(String borrowedBy) { this.borrowedBy = borrowedBy; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public boolean isOverdue() {
        return !isAvailable && dueDate != null && LocalDate.now().isAfter(dueDate);
    }

    public void displayBook() {
        String status = isAvailable ? "\u001B[32mAvailable\u001B[0m" : "\u001B[31mBorrowed by " + borrowedBy + "\u001B[0m";
        System.out.printf("| %-13s | %-25s | %-20s | %-6d | %-15s | %-20s |\n",
                isbn, title, author, publicationYear, category, status);
    }
}