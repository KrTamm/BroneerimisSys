package DTOs;

import java.util.List;

public class BookingDate {
    private String date;
    private List<BookingTime> timeList;

    public BookingDate(String date, List<BookingTime> timeList) {
        this.date = date;
        this.timeList = timeList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<BookingTime> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<BookingTime> timeList) {
        this.timeList = timeList;
    }
}
