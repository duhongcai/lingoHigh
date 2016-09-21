package lingoHigh.security.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by DuHongcai on 2016/9/20.
 */
public class MyRolePermissionResolver  implements RolePermissionResolver{

    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if ("roel1".equals("roleString")){
            return Arrays.asList((Permission) new WildcardPermission("menu:*"));
        }
        return null;
    }
}
