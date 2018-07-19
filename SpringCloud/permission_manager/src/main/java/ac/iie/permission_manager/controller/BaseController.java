package ac.iie.permission_manager.controller;

import ac.iie.permission_manager.service.PermissionService;
import ac.iie.permission_manager.service.RoleService;
import ac.iie.permission_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName BaseController
 * @Author tjh
 * @Date 18/7/18 下午3:49
 * @Version 1.0
 **/
public class BaseController {
    @Autowired
    protected UserService userService;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected PermissionService permissionService;
}
