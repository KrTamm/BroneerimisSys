package ee.projekt.broneerimissys;


import DTOs.*;
import ee.projekt.broneerimissys.exception.ApplicationException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String kasutajaNimi, String password) {
        String encodedPassword = projectRepository.getPassword(kasutajaNimi);
        if (passwordEncoder.matches(password, encodedPassword)) {
            JwtBuilder builder = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, "secret")
                    .claim("username", kasutajaNimi);
            return builder.compact();
        } else {
            throw new ApplicationException("Parool on vale");
        }
    }

    public void createAdminAccount(UserPass usrpss) {
        String encodedPassword = passwordEncoder.encode(usrpss.getPassword());
        projectRepository.createAdminAccount(usrpss.getKasutajaNimi(), encodedPassword);
    }

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

    public List<InfoForDocCard> getInfoForDocCard(String profession) {
        List<InfoForDocCard> result = new ArrayList<>();
        if (Objects.equals(profession, "Kõik kangelased")) {
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
        } else {
            List<Doctor> docs = projectRepository.getSpecificDoctorsList(profession);
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

    public String createNewBron(Integer id, NewBron bron) {
        projectRepository.createNewBron(id, bron);
        return "Uus broneerimisaeg loodud.";
    }

    public Integer makeBron(Booking teeBron, Integer id) {
        return projectRepository.makeBron(teeBron, id);
    }

    public Integer cancelBron(Integer bronid) {
        return projectRepository.cancelBron(bronid);
    }

    public void deleteTime(Integer id) {
        projectRepository.deleteTime(id);
    }

    public void deleteDoc(Integer id) {
        projectRepository.deleteDoc(id);
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

    public List<ProfessionList> professionList() {
        return projectRepository.professionList();
    }

    public HeroName heroName(Integer id) {
        return projectRepository.heroName(id);
    }
}
