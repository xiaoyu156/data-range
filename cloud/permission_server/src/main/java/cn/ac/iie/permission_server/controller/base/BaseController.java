package cn.ac.iie.permission_server.controller.base;

import cn.ac.iie.permission_server.service.AuthorityService;
import cn.ac.iie.permission_server.service.RoleService;
import cn.ac.iie.permission_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName BaseController
 * @Author tjh
 * @Date 18/8/19 下午1:45
 * @Version 1.0
 **/
public class BaseController {
    @Autowired
    protected UserService userService;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected AuthorityService authorityService;
}
