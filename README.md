# Console-Based Library Management System 📚

## Project Description
A professional, object-oriented console application built in Java to manage library operations. The system handles book inventories, member registrations, borrowing and returns, automated fine calculations, and active reservations. 

## Features
- **CRUD Operations**: Add, view, and search books and members.
- **Transaction Engine**: Borrow/Return books with automated `14-day` due dates.
- **Financial Module**: Automatically calculates overdue fines (₹10/day).
- **Reservation System**: Queue members for currently unavailable books.
- **Data Persistence**: Uses File I/O for `txt` data storage automatically upon any change.
- **Interactive UI**: Features colored ANSI terminal output and paginated tables.
- **Analytics & Export**: Generates library statistics and exports inventory to `CSV`.

## Technologies Used
- **Language:** Java 17
- **Build Tool:** Apache Maven
- **Core Concepts:** OOP (Encapsulation, Inheritance), Collections Framework (ArrayList, Streams), File I/O, Exception Handling.

## Folder Structure
week3-library-system/
│── src/main/java/library/
│   ├── Book.java
│   ├── Member.java
│   ├── Library.java
│   ├── FileHandler.java
│   ├── Main.java
│   ├── Reservation.java
│   └── Statistics.java
│── data/
│   ├── books.txt
│   ├── members.txt
│   └── books.csv
│── README.md
└── pom.xml


## How to Run

**1. Compilation Command:**
```bash
mvn clean compile
2. Execution Command:

Bash
mvn exec:java
Future Enhancements
Integration with MySQL database.

Implementation of an advanced GUI using JavaFX.

Email API integration to alert users of upcoming due dates.

Author: Senior Java Developer
License: MIT


---

### 4. `books.txt` (Initial Sample Data in `data/` folder)
```text
978-0134685991|Effective Java|Joshua Bloch|2018|Programming|true|null|null|null
978-0596009205|Head First Java|Kathy Sierra|2005|Education|false|M001|2023-10-01|2023-10-15
978-0201633610|Design Patterns|Erich Gamma|1994|Software|true|null|null|null
978-1449331818|Learning Python|Mark Lutz|2013|Programming|true|null|null|null
978-0137081073|Clean Coder|Robert Martin|2011|Technology|true|null|null|null
978-0201485677|Refactoring|Martin Fowler|1999|Software|false|M002|2023-10-20|2023-11-03
978-0321125217|Domain-Driven Design|Eric Evans|2003|Architecture|true|null|null|null
978-0132350884|Clean Code|Robert Martin|2008|Programming|true|null|null|null
978-1118531648|JavaScript|Jon Duckett|2014|Web Dev|true|null|null|null
978-1491950296|Fluent Python|Luciano Ramalho|2015|Programming|true|null|null|null
5. members.txt (Initial Sample Data in data/ folder)
Plaintext
M001|Alice Smith|555-0100|alice@example.com|0.0|978-0596009205
M002|Bob Jones|555-0101|bob@example.com|20.0|978-0201485677
M003|Charlie Brown|555-0102|charlie@example.com|0.0|none
M004|Diana Prince|555-0103|diana@example.com|0.0|none
M005|Evan Wright|555-0104|evan@example.com|50.0|none
6. books.csv (Output Example)
Code snippet
ISBN,Title,Author,Year,Category,Status
978-0134685991,Effective Java,Joshua Bloch,2018,Programming,Available
978-0596009205,Head First Java,Kathy Sierra,2005,Education,Borrowed
978-0201633610,Design Patterns,Erich Gamma,1994,Software,Available
...
7. Sample Console Output
Plaintext
==========================================
        LIBRARY MANAGEMENT SYSTEM         
==========================================
1 Add Book
2 View Books (Paginated)
3 Register Member
4 Borrow Book
5 Return Book
6 Reserve Book
7 Statistics
8 Export CSV
9 Exit
Choose an option: 7

==========================================
          LIBRARY STATISTICS LOG          
==========================================
Total Books         : 10
Available Books     : 8
Borrowed Books      : 2
Overdue Books       : 2
Registered Members  : 5
Active Reservations : 0
Total Fine Collected: ₹70.0
=========================================="# week3-librarymanagement" 
