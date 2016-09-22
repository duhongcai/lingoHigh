package lingoHigh.security.demo.dao;

import lingoHigh.security.demo.entity.Role;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public interface RoleDao {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId,Long...permissionIds);
    public void uncorrelationPermissions(Long roleId,Long...permissionIds);
}
