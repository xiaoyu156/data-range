package cn.ac.iie.permission_server.jwt;

import cn.ac.iie.permission_server.bean.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JWTHelper
 * @Author tjh
 * @Date 18/8/17 上午9:20
 * @Version 1.0
 **/
public class JWTHelper {

    // 公用秘钥
    private static final String SECRET = "iie";

    /**
     * 获取token
     * @param user
     * @return
     */
    public static String createToken(User user) {
        // 签发时间
        Date now = new Date();
        // 过期时间，一小时
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date expriesTime = calendar.getTime();
        // 头信息
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "jwt");
        header.put("alg", "HS256");
        String token = Jwts.builder().setHeader(header)
                .claim("user", user)
//                .claim("userId", user.getId())
//                .claim("userName", user.getUsername())
                .setExpiration(expriesTime)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
        return token;
    }

    /**
     * jiexi token
     * @param token
     * @return
     */
    public static Jws<Claims> paresToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token);
    }

    public static User getUserFromToken(String token) {
        Jws<Claims> claimsJws = paresToken(token);
        Claims body = claimsJws.getBody();

        Map<String, Object> map = (Map<String, Object>) body.get("user");
        User user = map2Object(map, User.class);

        return user;
    }

    public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String filedTypeName = field.getType().getName();
                if (filedTypeName.equalsIgnoreCase("java.util.date")) {
                    String datetimestamp = String.valueOf(map.get(field.getName()));
                    if (datetimestamp.equalsIgnoreCase("null")) {
                        field.set(obj, null);
                    } else {
                        field.set(obj, new Date(Long.parseLong(datetimestamp)));
                    }
                } else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
