package lingoHigh.security.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class JdbcTemplateUtils {
    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate(){
        if (jdbcTemplate == null) jdbcTemplate = createJdbcTemplate();
        return jdbcTemplate;
    }

    private static JdbcTemplate createJdbcTemplate(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/jdbcStudy");
        ds.setName("dhc");
        ds.setPassword("yyh123");
        return new JdbcTemplate(ds);
    }
}
