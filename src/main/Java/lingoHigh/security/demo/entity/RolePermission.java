package lingoHigh.security.demo.entity;

import java.io.Serializable;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class RolePermission implements Serializable {
    private Long roleId;
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
