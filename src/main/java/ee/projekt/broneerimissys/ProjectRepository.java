package ee.projekt.broneerimissys;

import DTOs.Doctor;
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
        paramMap.put("docFName", doctor.getDoc_first_name());
        paramMap.put("docLName", doctor.getDoc_last_name());
        paramMap.put("profession", doctor.getDoc_profession());
        paramMap.put("docArea", doctor.getDoc_area());
        paramMap.put("docLicense", doctor.getDoc_license());
        jdbcTemplate.update(sql, paramMap);
    }
    public List<Doctor> getDoctorsList() {
        String sql = "SELECT * FROM doctor ORDER BY doctor.doc_id, doctor.doc_license ASC;";
        Map<String, Object> paraMap = new HashMap<>();
        return jdbcTemplate.query(sql, paraMap, new BeanPropertyRowMapper<>(Doctor.class));
    }

}
