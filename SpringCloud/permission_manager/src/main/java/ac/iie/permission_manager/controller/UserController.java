package ac.iie.permission_manager.controller;

import ac.iie.common.response.Response;
import ac.iie.permission_manager.bean.User;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author tjh
 * @Date 18/7/18 下午3:50
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController {

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public Response getAllUsers(@RequestParam int pageNum, @RequestParam int pageSize) {
        Map<String, Object> data = new HashMap<>();
        Page<User> allUsers = userService.getAllUsers(pageNum, pageSize);
        data.put("users", allUsers);
        data.put("pageNum", allUsers.getPageNum());
        data.put("total", allUsers.getTotal());
        data.put("pages", allUsers.getPages());
        data.put("pageSize", allUsers.getPageSize());
        return Response.normResponse("200", "success", data);
    }
}
