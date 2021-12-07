package ee.projekt.broneerimissys;

import DTOs.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

//@RestController
//public class Project {
//
//    @Autowired
//    private NamedParameterJdbcTemplate jdbcTemplate;
//

//    @GetMapping("/api/employees")
//    public List<Employee> getEmployeeList(String orderBy) {
//        String sql = "SELECT * FROM employee";
//        HashMap<String, Object> paramMap = new HashMap<>();
//        if (orderBy != null && !orderBy.isEmpty()) {
//            sql += " ORDER BY " + orderBy;
//        }
//        List<Employee> result = jdbcTemplate.query(sql, paramMap, new EmployeeRowMapper());
//        return result;
//    }
//
//    static class EmployeeRowMapper implements RowMapper<Employee> {
//
//        @Override
//        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
//            Employee result = new Employee();
//            result.setId(resultSet.getInt("id"));
//            result.setFirstName(resultSet.getString("first_name"));
//            result.setLastName(resultSet.getString("last_name"));
//            result.setPosition(resultSet.getString("position"));
//            result.setCountry(resultSet.getString("country"));
//            return result;
//        }
//
//    }
//}