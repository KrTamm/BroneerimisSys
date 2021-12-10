package ee.projekt.broneerimissys;

import DTOs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

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

    @GetMapping("api/project/getInfoForDocDate/{kp}")
    public List<InfoForDocCard> getInfoForDocDate(@PathVariable("kp") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate kp) {
        return projectService.getInfoForDocDate(kp);
    }

    @PostMapping("api/project/createNewBron")
    public String createNewBron(@RequestBody NewBron bron) {
        return projectService.createNewBron(bron);
    }

    @PutMapping("api/project/makeBron/{id}")
    public Integer makeBron(@PathVariable("id") Integer id, @RequestBody Booking teeBron) {
        return projectService.makeBron(teeBron, id);
    }

    @PutMapping("/api/project/cancelBron/{bronid}")
    public Integer cancelBron(@PathVariable("bronid") Integer id) {
        return projectService.cancelBron(id);
    }

    @GetMapping("api/project/bronInfo/{id}")
    public BronInfo bronInfo(@PathVariable("id") Integer id) {
        return projectService.bronInfo(id);
    }
}
