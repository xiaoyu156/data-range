package ac.iie.common.utils;

import java.util.UUID;

/**
 * @Description: UUID工具类
 * @author: WangXiaoyu
 * @Date: 2018/4/3
 */
public class UUIDFactory {
    /**
     * @Description: 随机生成一个符合32位UUID
     * @param: []
     * @return: java.lang.String
     * @author: WangXiaoyu
     * @Date: 2018/4/3
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * @Description: 随机生成一个符合32位UUID字符串
     * @param: []
     * @return: java.lang.String
     * @author: WangXiaoyu
     * @Date: 2018/4/3
     */
    public static String generate32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * @Description: hashcode 为正的UUID
     * @param: []
     * @return: java.lang.String
     * @author: WangXiaoyu
     * @Date: 2018/4/3
     */
    public static String generatePositiveUUID() {
        while (true) {
            String str = UUID.randomUUID().toString();
            if (str.hashCode() > 0) {
                return str;
            } else {

                continue;
            }
        }
    }

    /**
     * @Description: hashcode为负的UUID
     * @param: []
     * @return: java.lang.String
     * @author: WangXiaoyu
     * @Date: 2018/4/3
     */
    public static String generateNegativeUUID() {
        while (true) {
            String str = UUID.randomUUID().toString();
            if (str.hashCode() < 0) {
                return str;
            } else {
                continue;
            }
        }
    }
}
