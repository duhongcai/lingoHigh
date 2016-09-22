package lingoHigh.security.demo.dao;

import org.apache.shiro.authz.Permission;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public interface PermissionDao {
    public lingoHigh.security.demo.entity.Permission createPermission(final lingoHigh.security.demo.entity.Permission permission);

    public void deletePermission(Long permissionId);
}
