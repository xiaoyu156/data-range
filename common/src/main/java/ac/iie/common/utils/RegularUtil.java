package ac.iie.common.utils;

/**
 * @Description: 正则表达式工具类
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-28 10:07
 * @version: 1.0.0
 */
public class RegularUtil {
    /**
     * 英文表达式
     */
    public static final String ENGLISH_PATTERN = "[a-zA-Z ]+";
    /**
     * 中文表达式
     */
    public static final String CHINESE_PATTERN = "[\u4e00-\u9fa5 ]+";

    /**
     * @Description: 判断一个字符串是中文还是英文
     * @param:
     * @return:
     * @date: 2018-8-28 10:10
     */
    public static boolean isEnglishOrChinese(String str, String pattern) {
        return str.matches(pattern);
    }

    public static void main(String[] args) {
    }

}
