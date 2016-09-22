package lingoHigh.security.demo.dao.impl;

import lingoHigh.security.demo.JdbcTemplateUtils;
import lingoHigh.security.demo.dao.PermissionDao;
import org.apache.shiro.authz.Permission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class PermissionDaoImpl implements PermissionDao{

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();


    public lingoHigh.security.demo.entity.Permission createPermission(final lingoHigh.security.demo.entity.Permission permission) {
        final String sql = " insert into sys_permissions(permission,desciption,available) values(?,?,?) ";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql);
                psst.setString(1,permission.getPermission());
                psst.setString(2,permission.getDescription());
                psst.setBoolean(3,permission.getAvailable());
                return psst;
            }
        },keyHolder);
        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    public void deletePermission(Long permissionId) {
        String sql = " delete from sys_roles_permissions where permissionId = ? ";
        jdbcTemplate.update(sql,permissionId);

        sql = " DELETE FROM sys_permissions where id = ?";
        jdbcTemplate.update(sql,permissionId);
    }
}
