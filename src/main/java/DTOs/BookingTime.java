package DTOs;

import java.util.List;

public class BookingTime {
    private String date;
    private List<String> timeList;

    public BookingTime(String date, List<String> timeList) {
        this.date = date;
        this.timeList = timeList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }
}
