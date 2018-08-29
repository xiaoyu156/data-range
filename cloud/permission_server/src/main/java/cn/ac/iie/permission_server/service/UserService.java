package cn.ac.iie.permission_server.service;

import cn.ac.iie.permission_server.bean.User;
import cn.ac.iie.permission_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Author tjh
 * @Date 18/8/19 下午1:51
 * @Version 1.0
 **/
@Service
public class UserService extends CommonService<User, String> {
    @Autowired
    private UserRepository userRepository;

    public User getUserByNameAndPass(User user) {
        return userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public User getUserByName(User user) {
        return userRepository.findUserByUsername(user.getUsername());
    }
}
