package ee.projekt.broneerimissys;

import DTOs.Booking;
import DTOs.Doctor;
import DTOs.InfoForDocCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createDoc(Doctor doctor) {
        String sql = "INSERT INTO doctor (doc_first_name, doc_last_name, doc_profession, doc_area, doc_license)" +
                "VALUES (:docFName, :docLName, :profession, :docArea, :docLicense);";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("docFName", doctor.getDocFirstName());
        paramMap.put("docLName", doctor.getDocLastName());
        paramMap.put("profession", doctor.getDocProfession());
        paramMap.put("docArea", doctor.getDocArea());
        paramMap.put("docLicense", doctor.getDocLicense());
        jdbcTemplate.update(sql, paramMap);
    }

    public List<Doctor> getDoctorsList() {
        String sql = "SELECT * FROM doctor ORDER BY doctor.doc_id, doctor.doc_license ASC;";
        Map<String, Object> paraMap = new HashMap<>();
        return jdbcTemplate.query(sql, paraMap, new BeanPropertyRowMapper<>(Doctor.class));
    }

    public List<Booking> getBookingsList() {
        String sql = "SELECT * FROM booking ORDER BY booking.booking_date ASC;";
        Map<String, Object> paraMap = new HashMap<>();
        return jdbcTemplate.query(sql, paraMap, new BeanPropertyRowMapper<>(Booking.class));
    }

    public List<Booking> getInfoForDocCard(Integer id) {
        String sql = "SELECT * FROM booking WHERE doc_id=:id AND user_email IS null;";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(Booking.class));
    }
}
