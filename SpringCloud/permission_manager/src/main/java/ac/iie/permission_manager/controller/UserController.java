package ac.iie.permission_manager.controller;

//~--- JDK imports ------------------------------------------------------------

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//~--- non-JDK imports --------------------------------------------------------

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import com.github.pagehelper.Page;

import ac.iie.common.response.Response;
import ac.iie.common.response.ReturnCode;
import ac.iie.permission_manager.bean.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Class description
 *
 *
 * @version        Enter version here..., 18/07/20
 * @author         Enter your name here...
 */
@RestController
@RequestMapping(value = "user")
@Slf4j
public class UserController extends BaseController {

    /**
     * Method description aaaaa
     *
     *
     * @param user
     *
     * @return
     */
    @RequestMapping(
        path   = "/",
        method = RequestMethod.POST
    )
    public Response addUser(@RequestBody User user) {
        return null;
    }

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    public boolean checkUser(User user) {
        return false;
    }

    /**
     * Method description
     *
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(
        path   = "/{id}",
        method = RequestMethod.DELETE
    )
    public Response deleteUser(@PathVariable("id") String id) {

        // 根据ID查询数据库
        User userFromDB = userService.getUser(id);

        // 如果ID不存在对应数据
        if (userFromDB == null) {
            log.error("数据库中未能找到对应数据：" + id);

            return Response.operateSucessNoData();
        }

        return null;
    }

    /**
     * Method description
     *
     *
     * @param user
     *
     * @return
     */
    @RequestMapping(
        path   = "/",
        method = RequestMethod.PUT
    )
    public Response updateUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();

        // 根据ID查询数据库
        User userFromDB = userService.getUser(user.getId());

        // 如果ID不存在对应数据
        if (userFromDB == null) {
            log.error("数据库中未能找到对应数据：" + user.getId());

            return Response.operateSucessNoData();
        }

        // 对数据进行更新
        try {
            int i = userService.updateUser(user);
        } catch (Exception e) {
            log.error("用户更新失败：\n" + user);
        }

        User newUser = userService.getUser(user.getId());

        result.put("newUser", newUser);

        return Response.operateSucessAndHaveData(result);
    }

    /**
     * Method description
     *
     *
     * @param pageNum
     * @param pageSize
     *
     * @return
     */
    @RequestMapping(
        path   = "/list",
        method = RequestMethod.GET
    )
    public Response getAllUsers(@RequestParam int pageNum, @RequestParam int pageSize) {
        Map<String, Object> data     = new HashMap<>();
        Page<User>          allUsers = userService.getAllUsers(pageNum, pageSize);

        if (allUsers.isEmpty()) {
            log.warn("未能查询到任何数据");

            return Response.operateSucessNoData();
        }

        log.info("获取数据" + data.size());
        data.put("users", allUsers);
        data.put("pageNum", allUsers.getPageNum());
        data.put("total", allUsers.getTotal());
        data.put("pages", allUsers.getPages());
        data.put("pageSize", allUsers.getPageSize());

        return Response.operateSucessAndHaveData(data);
    }

    /**
     * Method description
     *
     *
     * @param id
     *
     * @return
     */
    @RequestMapping(
        path   = "/{id}",
        method = RequestMethod.GET
    )
    public Response getUserById(@PathVariable("id") String id) {
        User user = userService.getUser(id);

        if (user == null) {
            return Response.operateSucessNoData();
        }

        return Response.operateSucessAndHaveData(user);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
