package cn.ac.iie.zuul.filter;

import cn.ac.iie.zuul.bean.Authority;
import cn.ac.iie.zuul.bean.Role;
import cn.ac.iie.zuul.bean.User;
import cn.ac.iie.zuul.repository.AuthorityRepository;
import cn.ac.iie.zuul.repository.RoleRepository;
import cn.ac.iie.zuul.repository.UserRepository;
import cn.ac.iie.zuul.jwt.JWTHelper;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StreamUtils;

import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName TokenFilter
 * @Author tjh
 * @Date 18/8/17 上午9:05
 * @Version 1.0
 **/
@Slf4j
public class TokenFilter extends ZuulFilter {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        AntPathMatcher matcher = new AntPathMatcher();
        boolean flag = false;
        int code = 401;
        String msg = "";

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String requestURL = request.getRequestURI().toString();
        System.out.println(requestURL);
        String method = request.getMethod();
        String token = request.getHeader("access_token");

        boolean shoudFilter = true;
        Role notFilter = roleRepository.findAllByName("any_one");
        if(notFilter == null) {
            log.info("未定义不需过滤接口，即将对url进行过滤");
            shoudFilter = true;
        } else {
            for (Authority authority : notFilter.getAuthorities()) {
                // 判断url是否在不需要过滤的列表中
                if(matcher.match(authority.getPermissionUrl(), requestURL)) {
                    log.info("url"+requestURL+"不需要任何权限");
                    shoudFilter = false;
                    flag = true;
                    code = 200;

                    break;
                }
            }
        }

        // 如果需要过滤
        if(shoudFilter) {
            // 根据token获取用户信息
            if (StringUtils.isNotBlank(token)) {
                User userFromToken = JWTHelper.getUserFromToken(token);
                User userFromDB = userRepository.getOne(userFromToken.getId());

                // 判断用户信息是否具有相应权限
                Set<Role> roles = userFromDB.getRoles();
                for (Role role : roles) {
                    Set<Authority> authorities = role.getAuthorities();
                    for (Authority authority : authorities) {
                        if(matcher.match(authority.getPermissionUrl(), requestURL)
                                && (method.equalsIgnoreCase(authority.getMethod()) || "all".equalsIgnoreCase(authority.getMethod()))) {
                            System.out.println("有相应权限，判断参数");
                            // 放行
                            flag = true;
                            code = 200;
                            // 判断参数
                            String params = authority.getParams();
                            JSONObject paramsFromDB = JSONObject.parseObject(params);
                            if(StringUtils.isNotBlank(params)) {
                                JSONObject requestParams = null;
                                if("get".equalsIgnoreCase(method)) {
                                    Map<String, String[]> map = request.getParameterMap();
                                    requestParams = JSONObject.parseObject(JSONObject.toJSONString(map));
                                } else if ("put".equalsIgnoreCase(method) || "post".equalsIgnoreCase(method)) {
                                    InputStream in = (InputStream) ctx.get("requestEntity");
                                    if (in == null) {
                                        try {
                                            in = ctx.getRequest().getInputStream();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    // 获取请求参数
                                    String body = null;
                                    try {
                                        body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println(body);

                                    requestParams = JSONObject.parseObject(body);
                                }

                                for (Map.Entry<String, Object> entry :
                                        paramsFromDB.entrySet()) {
                                    if (!(requestParams.containsKey(entry.getKey()) && requestParams.getString(entry.getKey()).equals((String) entry.getValue()))) {
                                        flag = false;
                                        code = 405;
                                        msg = "字段"+entry.getKey()+"值不能为"+entry.getValue();

                                    }
                                }
                            } else {
                                System.out.println("不需要校验参数");
                            }
                        } else {
                            System.out.println("没有匹配");
                            msg = "对"+requestURL+"没有"+method+"权限";
                        }

                        // 如果已有满足条件的则跳出循环
                        if(flag) {
                            break;
                        }
                    }
                }
            } else {
                msg = "访问"+requestURL+"需要有相关权限，请携带token";
            }
        }

        ctx.setSendZuulResponse(flag); //是否其进行路由
        ctx.setResponseStatusCode(code);

        HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        if(!flag) {
            ctx.setResponseBody(msg);
        }
        ctx.set("isSuccess", flag);

        return null;
    }
}
