package cn.ac.iie.permission_server.service;

import cn.ac.iie.permission_server.bean.Role;
import cn.ac.iie.permission_server.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName RoleService
 * @Author tjh
 * @Date 18/8/19 下午1:51
 * @Version 1.0
 **/
@Service
public class RoleService extends CommonService<Role, Long> {
    @Autowired
    private RoleRepository roleRepository;
    public Role getRoleByName(String roleName) {
        return roleRepository.findAllByName(roleName);
    }
}
