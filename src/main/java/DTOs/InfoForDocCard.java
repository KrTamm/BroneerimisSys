package DTOs;


import java.util.ArrayList;
import java.util.List;

public class InfoForDocCard {

    private String docFirstName;
    private String docLastName;
    private String docProfession;
    private String docLicense;
    private String docArea;
    private String bookingDate;
    private List bookingTime;

    public InfoForDocCard(Doctor doctor, List<Booking> times) {
        this.docFirstName = doctor.getDocFirstName();
        this.docLastName = doctor.getDocLastName();
        this.docProfession = doctor.getDocProfession();
        this.docLicense = doctor.getDocLicense();
        this.docArea = doctor.getDocArea();
        this.bookingTime = new ArrayList();
        for (Booking time : times) {
            this.bookingDate = time.getBookingDate();
            this.bookingTime.add(time.getBookingTime());
        }
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

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(List bookingTime) {
        this.bookingTime = bookingTime;
    }
}
