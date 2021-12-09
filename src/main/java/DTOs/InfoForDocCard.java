package DTOs;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoForDocCard {

    private String docFirstName;
    private String docLastName;
    private String docProfession;
    private String docLicense;
    private String docArea;
    private Integer docId;
    private List<BookingTime> bookingTimes = new ArrayList<>();

    public InfoForDocCard(Doctor doctor, List<Booking> times) {
        this.docId = doctor.getDocId();
        this.docFirstName = doctor.getDocFirstName();
        this.docLastName = doctor.getDocLastName();
        this.docProfession = doctor.getDocProfession();
        this.docLicense = doctor.getDocLicense();
        this.docArea = doctor.getDocArea();
        Map<String, List<String>> timeMap = new HashMap<>();
        for (Booking time : times) {
            String date = time.getBookingDate();
            List<String> timeList = timeMap.get(date);
            if (timeList == null) {
                timeList = new ArrayList<>();
                timeMap.put(time.getBookingDate(), timeList);
            }
            timeList.add(time.getBookingTime());
        }
        for (String key : timeMap.keySet()) {
            bookingTimes.add(new BookingTime(key, timeMap.get(key)));
        }
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getDocFirstName() {
        return docFirstName;
    }

    public void setDocFirstName(String docFirstName) {
        this.docFirstName = docFirstName;
    }

    public String getDocLastName() {
        return docLastName;
    }

    public void setDocLastName(String docLastName) {
        this.docLastName = docLastName;
    }

    public String getDocProfession() {
        return docProfession;
    }

    public void setDocProfession(String docProfession) {
        this.docProfession = docProfession;
    }

    public String getDocLicense() {
        return docLicense;
    }

    public void setDocLicense(String docLicense) {
        this.docLicense = docLicense;
    }

    public String getDocArea() {
        return docArea;
    }

    public void setDocArea(String docArea) {
        this.docArea = docArea;
    }
/*
    public Map getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Map bookingDate) {
        this.bookingDate = bookingDate;
    }
*/

    public List<BookingTime> getBookingTimes() {
        return bookingTimes;
    }

    public void setBookingTimes(List<BookingTime> bookingTimes) {
        this.bookingTimes = bookingTimes;
    }
}
