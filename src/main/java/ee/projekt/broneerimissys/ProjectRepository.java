package ee.projekt.broneerimissys;

import DTOs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getPassword(String kasutajaNimi) {
        String sql = "SELECT password FROM kasutajad WHERE kasutaja_nimi = :kasutajaNimi";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("kasutajaNimi", kasutajaNimi);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public void createUserAccount(String kasutajaNimi, String password) {
        String sql = "INSERT INTO kasutajad (kasutaja_nimi, password) VALUES (:kasutajaNimi, :password)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("kasutajaNimi", kasutajaNimi);
        paramMap.put("password", password);
        jdbcTemplate.update(sql, paramMap);
    }

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
        String sql = "SELECT * FROM booking;";
        Map<String, Object> paraMap = new HashMap<>();
        return jdbcTemplate.query(sql, paraMap, new BeanPropertyRowMapper<>(Booking.class));
    }

    public List<Booking> getInfoForDocCard(Integer id, LocalDate from, LocalDate to) {
        String sql = "SELECT * FROM booking WHERE doc_id =:id AND user_email IS null AND booking_date >= :from AND booking_date <= :to ORDER BY booking_date, booking_time;";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("from", from);
        paramMap.put("to", to);
        return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(Booking.class));
    }

    public List<Booking> getInfoForDocDate(LocalDate kp, Integer id) {
        String sql = "SELECT * FROM booking WHERE doc_id =:id AND user_email IS null AND booking_date = :kp;";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("kp", kp);
        return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(Booking.class));
    }

    public void createNewBron(NewBron bron) {
        String sql = "INSERT INTO booking (doc_id, user_email, booking_date, booking_time)" +
                "VALUES (:id, null, :kuup, :kellaaeg);";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", bron.getDocId());
        paramMap.put("kuup", bron.getBookingDate());
        paramMap.put("kellaaeg", bron.getBookingTime());
        jdbcTemplate.update(sql, paramMap);
    }


    public Integer makeBron(Booking teeBron, Integer id) {
        String sql = "UPDATE booking SET user_email = :meil WHERE booking_id = :id;";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("meil", teeBron.getUserEmail());
        paramMap.put("id", id);
        return jdbcTemplate.update(sql, paramMap);
    }

    public Integer cancelBron(Integer bronid) {
        String sql = "UPDATE booking SET user_email = null WHERE booking_id = :bronid;";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("bronid", bronid);
        return jdbcTemplate.update(sql, paramMap);
    }

    public BronInfo bronInfo(Integer id) {
        String sql = "SELECT doc_first_name, doc_last_name, doc_profession, doc_area, booking_date, booking_time FROM doctor a JOIN booking b on a.doc_id = b.doc_id WHERE booking_id = :id;";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        List<BronInfo> result = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<>(BronInfo.class));
        return result.get(0);
    }

    public String send(Integer id) {
        String sql = "SELECT user_email FROM booking WHERE booking_id = :id;";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        String result = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        return result;
    }
}
