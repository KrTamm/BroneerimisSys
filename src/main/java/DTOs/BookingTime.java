package DTOs;

public class BookingTime {

    private Integer bookingId;
    private String bookingTime;

    public BookingTime(Integer bookingId, String bookingTime) {
        this.bookingId = bookingId;
        this.bookingTime = bookingTime;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }
}
