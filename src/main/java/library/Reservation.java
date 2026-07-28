package library;

import java.time.LocalDate;

public class Reservation {
    private String isbn;
    private String memberId;
    private LocalDate reservationDate;

    public Reservation(String isbn, String memberId) {
        this.isbn = isbn;
        this.memberId = memberId;
        this.reservationDate = LocalDate.now();
    }

    public String getIsbn() { return isbn; }
    public String getMemberId() { return memberId; }
    public LocalDate getReservationDate() { return reservationDate; }
}