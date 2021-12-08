package ee.projekt.broneerimissys;

import DTOs.Doctor;
import DTOs.Booking;
import DTOs.InfoForDocCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("api/project/getBookingsList")
    public List<Booking> getBookingsList() {
        return projectService.getBookingsList();
    }

    @GetMapping("api/project/getInfoForDocCard")
    public List<InfoForDocCard> getInfoForDocCard() {
        return projectService.getInfoForDocCard();
    }
}