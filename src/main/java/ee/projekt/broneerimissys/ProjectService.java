package ee.projekt.broneerimissys;


import DTOs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.Authenticator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public String createDoc(Doctor doctor) {
        projectRepository.createDoc(doctor);
        return "New doctor, with license number " + doctor.getDocLicense() + " has been added to the database.";
    }

    public List<Doctor> getDoctorsList() {
        return projectRepository.getDoctorsList();
    }

    public List<Booking> getBookingsList() {
        return projectRepository.getBookingsList();
    }


    public List<InfoForDocCard> getInfoForDocCard() {
        List<InfoForDocCard> result = new ArrayList<>();
        List<Doctor> docs = projectRepository.getDoctorsList();
        for (Doctor doc : docs) {
            LocalDate from = LocalDate.now();
            LocalDate to = from.plusDays(7);
            List<Booking> times = projectRepository.getInfoForDocCard(doc.getDocId(), from, to);
            InfoForDocCard docData = new InfoForDocCard(doc, times);
            if (!docData.getBookingTimes().isEmpty()) {
                result.add(docData);
            }
        }
        return result;
    }


    public List<InfoForDocCard> getInfoForDocDate(LocalDate kp) {
        List<InfoForDocCard> result = new ArrayList<>();
        List<Doctor> docs = projectRepository.getDoctorsList();
        for (Doctor doc : docs) {
            List<Booking> kpDate = projectRepository.getInfoForDocDate(kp, doc.getDocId());
            InfoForDocCard docData = new InfoForDocCard(doc, kpDate);
            if (!docData.getBookingTimes().isEmpty()) {
                result.add(docData);
            }
        }
        return result;
    }

    public String createNewBron(NewBron bron) {
        projectRepository.createNewBron(bron);
        return "Uus broneerimisaeg loodud.";
    }

    public Integer makeBron(Booking teeBron, Integer id) {
        return projectRepository.makeBron(teeBron, id);
    }

    public Integer cancelBron(Integer bronid) {
        return projectRepository.cancelBron(bronid);
    }

    public BronInfo bronInfo(Integer id) {
        BronInfo result = projectRepository.bronInfo(id);
        return result;
    }

    public void send(Integer id, String toEmail, String subject, String body) throws MessagingException {
        Properties prop = new Properties();
        String meil = projectRepository.send(id);
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "*");


        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("testitmeil@gmail.com", "Enter012345");
                    }
                });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("testitmeil@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(meil)
        );
        message.setSubject("Broneerimise kinnitus");
        message.setText("Broneering tehtud!");

        Transport.send(message);

    }

}
