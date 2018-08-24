package ac.iie.server.api.base;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 接口记录切面
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-13 17:11
 * @version: 1.0.0
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    /**
     * @Description: 切入点定义
     * @param:
     * @return:
     * @date: 2018-8-13 17:13
     */
    @Pointcut(value = "execution(public * ac.iie.server.api.*.*(..))")
    public void accessPoint() {

    }

    /**
     * @Description: 在调用上面 @Pointcut标注的方法前执行以下方法
     * @param:
     * @return:
     * @date: 2018-8-13 17:09
     */
    @Before("accessPoint()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("-----------------------------doBefore------------------------");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            log.info("url ={}", request.getRequestURI());
            log.info("method={}", request.getMethod());
            log.info("ip={}", request.getRemoteAddr());
            log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + '.' + joinPoint.getSignature().getName());
            log.info("args={}", joinPoint.getArgs());
        } else {
            log.error("日志拦截器获取属性失败，bad Request!");
        }
    }

    /**
     * @Description: 无论Controller中调用方法以何种方式结束，都会执行
     * @param:
     * @return:
     * @date: 2018-8-13 17:09
     */
    @After("accessPoint()")
    public void doAfter() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response=attributes.getResponse();
        response.setHeader("Access-Control-Allow-Origin","*");
        log.info("-----------------------------doAfter------------------------");
    }

    /**
     * @Description: 在调用上面 @Pointcut标注的方法后执行。用于获取返回值
     * @param:
     * @return:
     * @date: 2018-8-13 17:10
     */
    @AfterReturning(returning = "obj", pointcut = "accessPoint()")
    public void doAfterReturning(Object obj) {
        if (obj == null) {
            log.info("没有返回值");
        } else {
            log.info("response:" + obj.toString());
        }
    }
}
