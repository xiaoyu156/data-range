package ac.iie.permission_manager.service.base;

import ac.iie.permission_manager.dao.PermissionMapper;
import ac.iie.permission_manager.dao.RoleMapper;
import ac.iie.permission_manager.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName BaseService
 * @Author tjh
 * @Date 18/7/18 下午5:01
 * @Version 1.0
 **/
public class BaseService {
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected RoleMapper roleMapper;
    @Autowired
    protected PermissionMapper permissionMapper;
}
