package lingoHigh.security.demo.dao;

import lingoHigh.entry.User;

import java.util.Set;

/**
 * Created by DuHongcai on 2016/9/22.
 */
public interface UserDao {
    public lingoHigh.security.demo.entity.User createUser(lingoHigh.security.demo.entity.User user);
    public void updateUser(lingoHigh.security.demo.entity.User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId,Long...roleIds);
    public void uncorrelationRoles(Long userId,Long...roleIds);

    lingoHigh.security.demo.entity.User findOne(Long userId);

    lingoHigh.security.demo.entity.User findByUsername(String username);
    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
