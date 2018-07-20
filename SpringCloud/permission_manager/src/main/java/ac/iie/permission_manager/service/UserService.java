package ac.iie.permission_manager.service;

import ac.iie.permission_manager.bean.User;
import com.github.pagehelper.Page;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName UserService
 * @Author tjh
 * @Date 18/7/18 下午4:55
 * @Version 1.0
 **/
public interface UserService {
    Page<User> getAllUsers(int pageNum, int pageSize);
    User getUser(String id);
    int updateUser(User user);
    int deleteUser(String id);
    int deleteUsers(Collection<String> ids);
    int addUser(User user);
}
