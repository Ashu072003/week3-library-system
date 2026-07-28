package library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n\u001B[34m==========================================\u001B[0m");
            System.out.println("\u001B[1m        LIBRARY MANAGEMENT SYSTEM         \u001B[0m");
            System.out.println("\u001B[34m==========================================\u001B[0m");
            System.out.println("1 \u001B[32mAdd Book\u001B[0m");
            System.out.println("2 \u001B[32mView Books (Paginated)\u001B[0m");
            System.out.println("3 \u001B[32mRegister Member\u001B[0m");
            System.out.println("4 \u001B[32mBorrow Book\u001B[0m");
            System.out.println("5 \u001B[32mReturn Book\u001B[0m");
            System.out.println("6 \u001B[32mReserve Book\u001B[0m");
            System.out.println("7 \u001B[32mStatistics\u001B[0m");
            System.out.println("8 \u001B[32mExport CSV\u001B[0m");
            System.out.println("9 \u001B[31mExit\u001B[0m");
            System.out.print("\u001B[33mChoose an option: \u001B[0m");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.nextLine().trim();
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine().trim();
                        System.out.print("Enter Year: ");
                        int year = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Category: ");
                        String category = scanner.nextLine().trim();
                        if (isbn.isEmpty() || title.isEmpty()) throw new IllegalArgumentException("Fields cannot be empty.");
                        library.addBook(new Book(isbn, title, author, year, category));
                        break;
                    case 2:
                        library.displayBooksPaginated();
                        break;
                    case 3:
                        System.out.print("Enter Member ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Phone: ");
                        String phone = scanner.nextLine().trim();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine().trim();
                        library.registerMember(new Member(id, name, phone, email));
                        break;
                    case 4:
                        System.out.print("Enter Book ISBN: ");
                        String bIsbn = scanner.nextLine().trim();
                        System.out.print("Enter Member ID: ");
                        String mId = scanner.nextLine().trim();
                        library.borrowBook(bIsbn, mId);
                        break;
                    case 5:
                        System.out.print("Enter Book ISBN: ");
                        String retIsbn = scanner.nextLine().trim();
                        System.out.print("Enter Member ID: ");
                        String retMId = scanner.nextLine().trim();
                        library.returnBook(retIsbn, retMId);
                        break;
                    case 6:
                        System.out.print("Enter Book ISBN to Reserve: ");
                        String resIsbn = scanner.nextLine().trim();
                        System.out.print("Enter Your Member ID: ");
                        String resMId = scanner.nextLine().trim();
                        library.reserveBook(resIsbn, resMId);
                        break;
                    case 7:
                        library.showStatistics();
                        break;
                    case 8:
                        library.exportToCSV();
                        break;
                    case 9:
                        exit = true;
                        System.out.println("Exiting System. Goodbye!");
                        break;
                    default:
                        System.out.println("\u001B[31mInvalid option. Try again.\u001B[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\u001B[31mError: Please enter a valid number.\u001B[0m");
            } catch (Exception e) {
                System.out.println("\u001B[31mUnexpected Error: " + e.getMessage() + "\u001B[0m");
            }
        }
        scanner.close();
    }
}