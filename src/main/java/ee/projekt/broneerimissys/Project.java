package ee.projekt.broneerimissys;

import DTOs.Doctor;
import DTOs.Booking;
import DTOs.InfoForDocCard;
import DTOs.NewBron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@RestController
public class Project {

    @Autowired
    private ProjectService projectService;

    //localhost:8095/project/createDoc
    @PostMapping("api/project/createDoc")
    public String createDoc(@RequestBody Doctor doctor) {
        return projectService.createDoc(doctor);
    }

    @GetMapping("api/project/getDocList")
    public List<Doctor> getDoctorsList() {
        return projectService.getDoctorsList();
    }

    @GetMapping("api/project/getBookingsList")
    public List<Booking> getBookingsList() {
        return projectService.getBookingsList();
    }

    @GetMapping("api/project/getInfoForDocCard")
    public List<InfoForDocCard> getInfoForDocCard() {
        return projectService.getInfoForDocCard();
    }

    @GetMapping("api/project/getInfoForDocDate/{kp}")
    public List<InfoForDocCard> getInfoForDocDate(@PathVariable("kp") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate kp) {
        return projectService.getInfoForDocDate(kp);
    }

    @PostMapping("sendEmail")
    public void sendEmail() throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("username", "password");
                    }
                }
        );

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("testitmeil@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse("kluis2206@gmail.com")
        );
        message.setSubject("Subject");
        message.setText("Body of email");
        Transport.send(message);
    }

    @PostMapping("api/project/createNewBron")
    public String createNewBron(@RequestBody NewBron bron) {
        return projectService.createNewBron(bron);
    }

}
