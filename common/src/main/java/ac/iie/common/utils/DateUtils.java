package ac.iie.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 时间转换类
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-24 10:56
 * @version: 1.0.0
 */
public class DateUtils {
    public static final String DATE_FORMATE_NORMAL = "yyyy-MM-dd HH:mm:ss";

    /**
     * @Description: 日期转换
     * @param:
     * @return:
     * @date: 2018-8-24 11:01
     */
    public static LocalDateTime formateDateTime(String strDate, String pattern) throws Exception {
        if (StringUtils.isEmpty(strDate) || StringUtils.isEmpty(strDate)) {
            throw new Exception("输入strDate不可以为空");
        }
        if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(pattern)) {
            throw new Exception("输入pattern日期格式不可以为空");
        }
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(strDate, dateTimeFormat);
    }
}
