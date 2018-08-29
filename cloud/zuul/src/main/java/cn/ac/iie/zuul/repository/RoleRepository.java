package cn.ac.iie.zuul.repository;

import cn.ac.iie.zuul.bean.Role;

/**
 * @ClassName RoleRepository
 * @Author tjh
 * @Date 18/8/17 上午10:28
 * @Version 1.0
 **/
public interface RoleRepository extends CommonRepository<Role, String> {
    Role findAllByName(String name);
}
