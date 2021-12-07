package ee.projekt.broneerimissys;


import DTOs.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public List<Doctor> getDoctorsList() {
        return projectRepository.getDoctorsList();
    }


}
