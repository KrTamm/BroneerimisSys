package DTOs;


import java.util.*;

public class InfoForDocCard {

    private String docFirstName;
    private String docLastName;
    private String docProfession;
    private String docLicense;
    private String docArea;
    private Integer docId;
    private String photo;
    private List<BookingDate> bookingTimes = new ArrayList<>();

    public InfoForDocCard(Doctor doctor, List<Booking> times) {
        this.docId = doctor.getDocId();
        this.docFirstName = doctor.getDocFirstName();
        this.docLastName = doctor.getDocLastName();
        this.docProfession = doctor.getDocProfession();
        this.docLicense = doctor.getDocLicense();
        this.docArea = doctor.getDocArea();
        this.photo = doctor.getPhoto();
        Map<String, List<BookingTime>> timeMap = new HashMap<>();
        for (Booking time : times) {
            String date = time.getBookingDate();
            List<BookingTime> timeList = timeMap.get(date);
            if (timeList == null) {
                timeList = new ArrayList<>();
                timeMap.put(time.getBookingDate(), timeList);
            }
            timeList.add(new BookingTime(time.getBookingId(), time.getBookingTime()));
        }
        for (String key : timeMap.keySet()) {
            bookingTimes.add(new BookingDate(key, timeMap.get(key)));
        }
        bookingTimes.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public List<BookingDate> getBookingTimes() {
        return bookingTimes;
    }

    public void setBookingTimes(List<BookingDate> bookingTimes) {
        this.bookingTimes = bookingTimes;
    }
}
