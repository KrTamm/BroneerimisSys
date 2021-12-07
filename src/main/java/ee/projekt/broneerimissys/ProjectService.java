package ee.projekt.broneerimissys;


import DTOs.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public String createDoc(Doctor doctor) {
        projectRepository.createDoc(doctor);
        return "New doctor, with license number " + doctor.getDoc_license() + " has been added to the database.";
    }

    public List<Doctor> getDoctorsList() {
        return projectRepository.getDoctorsList();
    }


}
