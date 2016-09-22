package lingoHigh.security.demo.dao.impl;

import lingoHigh.security.demo.JdbcTemplateUtils;
import lingoHigh.security.demo.dao.UserDao;
import lingoHigh.security.demo.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();

    public User createUser(final User user){
        final String sql = " insert into sys_users(username,password,salt,locked) values(?,?,?,?)  ";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psst = con.prepareStatement(sql);
                psst.setString(1,user.getUsername());
                psst.setString(2,user.getPassword());
                psst.setString(3,user.getSalt());
                psst.setBoolean(4,user.getLocked());
                return psst;
            }
        },keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public void updateUser(User user) {
        String sql = " update sys_users set username =?,password = ?,salt=?,locked = ? where id = ? ";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getSalt(),user.getLocked(),user.getId());
    }

    public void deleteUser(Long userId) {
        String sql = " delete from sys_users where id = ? ";
        jdbcTemplate.update(sql,userId);
    }

    public void correlationRoles(Long userId, Long... roleIds) {
        if (roleIds == null || roleIds.length ==0){
            return;
        }
        String sql = " insert into sys_users_roles(userId,roleId) values(?,?) ";
        for (Long roleId : roleIds){
            if (!exists(userId,roleId)){
                jdbcTemplate.update(sql,userId,roleId);
            }
        }
    }

    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if (roleIds ==null || roleIds.length ==0){
            return;
        }
        String sql = " delete from sys_users_roles where userId = ? and roleId = ? ";
        for (Long roleId : roleIds){
            if (exists(userId,roleId)){
                jdbcTemplate.update(sql,userId,roleId);
            }
        }
    }

    private boolean exists(Long userId,Long roleId){
        String sql = " select count(1) from sys_users_roles where userId= ? and roleId = ? ";
        return jdbcTemplate.queryForObject(sql,Integer.class,userId,roleId) != 0;
    }

    public User findOne(Long userId) {
        String sql = " select id,username,password,salt,locked from sys_users where userId = ? ";
        List<User> userList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),userId);
        if (userList.size() == 0 ){
            return null;
        }
        return userList.get(0);
    }

    public User findByUsername(String username) {
        String sql = " select id,username,password,salt,locked from sys_users where username = ? ";
        List<User> userList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),username);
        if (userList.size() == 0){
            return null;
        }
        return userList.get(0);
    }

    public Set<String> findRoles(String username) {
        String sql = " select role from sys_users u,sys_roles r,sys_users_roles ur where username = ? and u.id = ur.userId and  r.id = ur.roleId ";
        return new HashSet<String>(jdbcTemplate.queryForList(sql,String.class,username));
    }

    public Set<String> findPermissions(String username) {
        String sql = " select permission from sys_users u,sys_permissions p,sys_users_roles ur,sys_roles_permissions rp where u.username = ? and " +
                " r.id = ur.roleId and r.is = rp.roleId and p.id = rp.permissionId ";
        return new HashSet<String>(jdbcTemplate.queryForList(sql,String.class,username));
    }
}
