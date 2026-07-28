package library;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DATA_DIR = "data/";
    private static final String BOOKS_FILE = DATA_DIR + "books.txt";
    private static final String MEMBERS_FILE = DATA_DIR + "members.txt";

    public FileHandler() {
        new File(DATA_DIR).mkdirs();
    }

    public void saveBooks(List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book b : books) {
                bw.write(String.join("|", b.getIsbn(), b.getTitle(), b.getAuthor(),
                        String.valueOf(b.getPublicationYear()), b.getCategory(),
                        String.valueOf(b.isAvailable()),
                        b.getBorrowedBy() == null ? "null" : b.getBorrowedBy(),
                        b.getBorrowDate() == null ? "null" : b.getBorrowDate().toString(),
                        b.getDueDate() == null ? "null" : b.getDueDate().toString()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mError saving books: " + e.getMessage() + "\u001B[0m");
        }
    }

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(BOOKS_FILE);
        if (!file.exists()) return books;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                Book b = new Book(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                b.setAvailable(Boolean.parseBoolean(parts[5]));
                if (!"null".equals(parts[6])) b.setBorrowedBy(parts[6]);
                if (!"null".equals(parts[7])) b.setBorrowDate(LocalDate.parse(parts[7]));
                if (!"null".equals(parts[8])) b.setDueDate(LocalDate.parse(parts[8]));
                books.add(b);
            }
        } catch (Exception e) {
            System.out.println("\u001B[31mError loading books: " + e.getMessage() + "\u001B[0m");
        }
        return books;
    }

    public void saveMembers(List<Member> members) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member m : members) {
                String booksStr = String.join(",", m.getBorrowedBooks());
                bw.write(String.join("|", m.getMemberId(), m.getName(), m.getPhone(),
                        m.getEmail(), String.valueOf(m.getTotalFine()), booksStr.isEmpty() ? "none" : booksStr));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mError saving members: " + e.getMessage() + "\u001B[0m");
        }
    }

    public List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        File file = new File(MEMBERS_FILE);
        if (!file.exists()) return members;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                Member m = new Member(parts[0], parts[1], parts[2], parts[3]);
                m.addFine(Double.parseDouble(parts[4]));
                if (!"none".equals(parts[5])) {
                    for (String isbn : parts[5].split(",")) m.borrowBook(isbn);
                }
                members.add(m);
            }
        } catch (Exception e) {
            System.out.println("\u001B[31mError loading members: " + e.getMessage() + "\u001B[0m");
        }
        return members;
    }
}