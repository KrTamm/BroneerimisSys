package ee.projekt.broneerimissys;


import DTOs.Booking;
import DTOs.Doctor;
import DTOs.InfoForDocCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        for (int i = 0; i < docs.size(); i++) {
            List<Booking> times = projectRepository.getInfoForDocCard(docs.get(i).getDocId());
            InfoForDocCard docData = new InfoForDocCard(docs.get(i), times);
            result.add(docData);
        }
        return result;
    }


}
