package mainpackage.company;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CompanyConfig {
    private static int IANUM;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public static int getIANUM() {
        return IANUM;
    }
    
    public static void updateIANUMInDatabase(JdbcTemplate jdbcTemplate) {
            String updateQuery = "UPDATE COMPANYCONFIG SET IANUM = ?";
            jdbcTemplate.update(updateQuery, ++IANUM);
    }
    
    @PostConstruct
    public void initializeIANUMFromDatabase() {
    	String selectQuery = "SELECT IANUM FROM COMPANYCONFIG";
        int initialIANUM = jdbcTemplate.queryForObject(selectQuery, Integer.class);
        IANUM = initialIANUM;
    }
}
