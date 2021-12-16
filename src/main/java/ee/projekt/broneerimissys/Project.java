package ee.projekt.broneerimissys;

import DTOs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class Project {

    @Autowired
    private ProjectService projectService;

    //localhost:8095/project/createDoc
    @PostMapping("api/protected/project/createDoc")
    public String createDoc(@RequestBody Doctor doctor) {
        return projectService.createDoc(doctor);
    }

    @GetMapping("api/public/project/getDocList")
    public List<Doctor> getDoctorsList() {
        return projectService.getDoctorsList();
    }

    @GetMapping("api/public/project/getBookingsList")
    public List<Booking> getBookingsList() {
        return projectService.getBookingsList();
    }

    @GetMapping("api/public/project/getInfoForDocCard/{profession}")
    public List<InfoForDocCard> getInfoForDocCard(@PathVariable("profession") String profession) {
        return projectService.getInfoForDocCard(profession);
    }

    @GetMapping("api/public/project/getInfoForDocDate/{kp}")
    public List<InfoForDocCard> getInfoForDocDate(@PathVariable("kp") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate kp) {
        return projectService.getInfoForDocDate(kp);
    }

    @PostMapping("api/protected/project/createNewBron/{id}")
    public String createNewBron(@PathVariable("id") Integer id, @RequestBody NewBron bron) {
        return projectService.createNewBron(id, bron);
    }

    @PutMapping("api/public/project/makeBron/{id}")
    public Integer makeBron(@PathVariable("id") Integer id, @RequestBody Booking teeBron) {
        return projectService.makeBron(teeBron, id);
    }

    @PutMapping("/api/protected/project/cancelBron/{bronid}")
    public Integer cancelBron(@PathVariable("bronid") Integer id) {
        return projectService.cancelBron(id);
    }

    @GetMapping("api/public/project/bronInfo/{id}")
    public BronInfo bronInfo(@PathVariable("id") Integer id) {
        return projectService.bronInfo(id);
    }

    @GetMapping("api/public/test/{id}")
    public void send(@PathVariable("id") Integer id, String toEmail, String subject, String body) throws MessagingException {
        projectService.send(id, subject, body, toEmail);
    }

    @PostMapping("api/public/user")
    public void createAdminAccount(@RequestBody UserPass usrpss) {
        projectService.createAdminAccount(usrpss);
    }

    @PostMapping("api/public/login")
    public String login(@RequestBody UserPass usrpss) {
        return projectService.login(usrpss.getKasutajaNimi(), usrpss.getPassword());
    }

    @DeleteMapping("/api/protected/project/deleteTime/{id}")
    public void deleteTime(@PathVariable("id") int a) {
        projectService.deleteTime(a);
    }

    @DeleteMapping("/api/protected/project/deleteDoc/{id}")
    public void deleteDoc(@PathVariable("id") int a) {
        projectService.deleteDoc(a);
    }

    @GetMapping("api/public/professionList")
    public List<ProfessionList> professionList() {
        return projectService.professionList();
    }

    @GetMapping("api/public/heroDetails/{id}")
    public HeroName heroName(@PathVariable("id") Integer id) {
        return projectService.heroName(id);
    }
}
