package ac.iie.permission_manager.service.impl;

import ac.iie.permission_manager.bean.User;
import ac.iie.permission_manager.service.UserService;
import ac.iie.permission_manager.service.base.BaseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author tjh
 * @Date 18/7/18 下午4:56
 * @Version 1.0
 **/
@Service(value = "userService")
public class UserServiceImpl extends BaseService implements UserService{
    @Override
    public Page<User> getAllUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAll();
    }

    @Override
    public User getUser(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
