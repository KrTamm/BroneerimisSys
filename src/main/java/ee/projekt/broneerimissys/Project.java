package ee.projekt.broneerimissys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Project {

    @Autowired
    private ProjectService projectService;




}