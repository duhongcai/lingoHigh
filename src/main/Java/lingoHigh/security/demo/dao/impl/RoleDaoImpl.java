package lingoHigh.security.demo.dao.impl;

import lingoHigh.security.demo.JdbcTemplateUtils;
import lingoHigh.security.demo.dao.RoleDao;
import lingoHigh.security.demo.entity.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class RoleDaoImpl implements RoleDao {
    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();
    public Role createRole(final Role role) {
        final String sql = " insert into sys_roles(role,description,available) values(?,?,?) ";
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psst = con.prepareStatement(sql);
                psst.setString(1,role.getRole());
                psst.setString(2,role.getDescription());
                psst.setBoolean(3,role.getAvailable());
                return psst;
            }
        });
        return role;
    }

    public void deleteRole(Long roleId) {
        String sql = " delete from sys_users_roles where roleId = ? ";
        jdbcTemplate.update(sql,roleId);

        sql = " DELETE  from sys_roles where id = ?";
        jdbcTemplate.update(sql,roleId);
    }

    public void correlationPermissions(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length ==0){
            return;
        }

        String sql = " insert into sys_roles_permissions(roleId,permissionId) values(?,?) ";
        for (Long permissionId : permissionIds){
            if (!exists(roleId,permissionId)){
                jdbcTemplate.update(sql,roleId,permissionId);
            }
        }
    }

    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        if (permissionIds == null || permissionIds.length ==0){
            return;
        }
        String sql = " delete from sys_roles_permissions where roleId=? and permissionId = ? ";
        for (Long permissionId :permissionIds){
            if (exists(roleId,permissionId)){
                jdbcTemplate.update(sql,roleId,permissionId);
            }
        }
    }

    private boolean exists(Long roleId,Long permissionId){
        String sql = " select count(1) from sys_role_permissions where roleId = ? and permissionId = ? ";
        return jdbcTemplate.queryForObject(sql,Integer.class,roleId,permissionId) !=0;
    }
}
