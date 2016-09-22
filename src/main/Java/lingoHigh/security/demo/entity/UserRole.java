package lingoHigh.security.demo.entity;

import java.io.Serializable;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public class UserRole implements Serializable {
    private Long userId;
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
