package cn.ac.iie.zuul;

import cn.ac.iie.zuul.bean.Authority;
import cn.ac.iie.zuul.bean.Role;
import cn.ac.iie.zuul.bean.User;
import cn.ac.iie.zuul.repository.AuthorityRepository;
import cn.ac.iie.zuul.repository.RoleRepository;
import cn.ac.iie.zuul.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Init
 * @Author tjh
 * @Date 18/8/17 下午4:34
 * @Version 1.0
 **/
@Component
@AllArgsConstructor
public class Init implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        Authority authority = new Authority();
        authority.setName("test");
        authority.setPermissionUrl("/test/test/**");
        authority.setMethod("get");
//        authorityRepository.save(authority);

        Authority authority2 = new Authority();
        authority2.setName("test");
        authority2.setPermissionUrl("/test/login/**");
        authority2.setMethod("get");
//        authorityRepository.save(authority2);

        Role roleUser = new Role();
        roleUser.setName("role_user");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        roleUser.setAuthorities(authorities);
//        roleRepository.save(roleUser);

        Role all = new Role();
        all.setName("any_one");
        Set<Authority> authorities2 = new HashSet<>();
        authorities2.add(authority2);
        all.setAuthorities(authorities2);
        roleRepository.save(all);

        User user = new User();
        user.setUsername("tjh");
        user.setPassword("123456");
        Set<Role> roles = new HashSet<>();
        roles.add(roleUser);
        user.setRoles(roles);

        userRepository.save(user);

    }
}
