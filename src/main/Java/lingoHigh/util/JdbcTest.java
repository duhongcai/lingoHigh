package lingoHigh.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by DuHongcai on 2016/9/7.
 */
public class JdbcTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/spring-resource.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");
       try{
           Connection connection = dataSource.getConnection();
           Statement statement = connection.createStatement();
           String sql = " select * from users ";
           ResultSet rs = statement.executeQuery(sql);
           while(rs.next()){
               System.out.println(rs.getObject("name"));
           }

       }catch(Exception e){
           e.printStackTrace();
       }
    }
}
