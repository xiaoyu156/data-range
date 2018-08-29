package cn.ac.iie.permission_server.controller;

import cn.ac.iie.permission_server.bean.Role;
import cn.ac.iie.permission_server.bean.User;
import cn.ac.iie.permission_server.controller.base.BaseController;
import cn.ac.iie.permission_server.jwt.JWTHelper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @ClassName UserController
 * @Author tjh
 * @Date 18/8/19 下午1:45
 * @Version 1.0
 **/
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        JSONObject result = new JSONObject();
        String msg = "";
        // 根据请求信息查询数据库
        User userFromDB = userService.getUserByNameAndPass(user);
        if(userFromDB == null) {
            msg = "用户名或密码不正确";
        } else {
            // 用户名密码能够找到对应实体，为该用户生成token
            String token = JWTHelper.createToken(userFromDB);
            result.put("user",userFromDB);
            result.put("token", token);
        }

        result.put("msg", msg);
        return result.toJSONString();
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PutMapping("/regist")
    public String regist(@RequestBody User user) {
        JSONObject result = new JSONObject();
        String msg = "";
        // 根据用户名查询数据库
        User userByName = userService.getUserByName(user);

        if(userByName == null) {
            // 不存在同名用户，添加用户信息
            User newUser = userService.save(user);
            // 为用户添加默认角色
            Role role = roleService.getRoleByName("any_one");
            if(role != null) {
                newUser.getRoles().add(role);
            }
            result.put("user", newUser);
        } else {
            msg = "用户名已存在";
        }

        result.put("msg", msg);

        return result.toJSONString();
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/")
    public String addUser(@RequestBody User user) {
        JSONObject result = new JSONObject();
        String msg = "";

        User userFromDB = userService.getUserByName(user);
        if(userFromDB != null) {
            userService.save(user);
            result.put("user", user);
        } else {
            msg = "用户名已存在";
        }

        result.put("msg", msg);

        return result.toJSONString();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        JSONObject result = new JSONObject();
        String msg = "";

        User userFromDB = userService.get(id);

        if(userFromDB != null) {
            userService.delete(id);
        } else {
            msg = "id："+id+" 在数据库中没有对应实体";
        }

        result.put("msg", msg);

        return result.toJSONString();
    }

    @PutMapping("/")
    public String updateUser(@RequestBody User user) {
        JSONObject result = new JSONObject();
        String msg = "";

        User userFromDB = userService.get(user.getId());
        if(userFromDB != null) {
            User updateUser = userService.update(user);
            result.put("user", updateUser);
        } else {
            msg = "id："+user.getId()+" 在数据库中没有对应实体";
        }

        result.put("msg", msg);

        return result.toJSONString();
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") String id) {
        JSONObject result = new JSONObject();
        String msg = "";

        User userFromDB = userService.get(id);

        if(userFromDB != null) {
            result.put("user", userFromDB);
        } else {
            msg = "id："+id+" 在数据库中没有对应实体";
        }

        result.put("msg", msg);

        return result.toJSONString();
    }

    @GetMapping("/list")
    public String getUserList() {
        JSONObject result = new JSONObject();
        String msg = "";

        List<User> all = userService.getAll();

        result.put("users", all);

        return result.toJSONString();
    }

    @PostMapping("/{id}/roles")
    public String addRoleToUser(@PathVariable("id") String id, @RequestBody String params) {
        return null;


    }
}
