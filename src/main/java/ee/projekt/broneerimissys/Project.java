package ee.projekt.broneerimissys;

import DTOs.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Project {

    @Autowired
    private ProjectService projectService;
    //localhost:8095/project/createDoc
    @PostMapping("project/createDoc")
    public String createDoc(@RequestBody Doctor doctor) {
        return projectService.createDoc(doctor);
    }


}