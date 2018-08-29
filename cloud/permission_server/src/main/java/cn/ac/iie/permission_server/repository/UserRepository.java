package cn.ac.iie.permission_server.repository;


import cn.ac.iie.permission_server.bean.User;

public interface UserRepository extends CommonRepository<User, String> {
    User findUserByUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);
}
