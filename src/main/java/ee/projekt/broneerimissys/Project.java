package ee.projekt.broneerimissys;

import DTOs.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}