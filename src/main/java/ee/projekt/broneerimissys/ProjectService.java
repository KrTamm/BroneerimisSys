package ee.projekt.broneerimissys;


import DTOs.Booking;
import DTOs.Doctor;
import DTOs.InfoForDocCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            List<Booking> times = projectRepository.getInfoForDocCard(doc.getDocId());
            InfoForDocCard docData = new InfoForDocCard(doc, times);
            result.add(docData);
        }
        return result;
    }

    public List<InfoForDocCard> getInfoForDocDate(LocalDate kp) {
        List<InfoForDocCard> result = new ArrayList<>();
        List<Doctor> docs = projectRepository.getDoctorsList();
        for (Doctor doc : docs) {
            List<Booking> kpDate = projectRepository.getInfoForDocDate(kp, doc.getDocId());
            InfoForDocCard docData = new InfoForDocCard(doc, kpDate);
            result.add(docData);
        }
        return result;
    }
}
