package ac.iie.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 接口公共返回类
 * @param:
 * @return:
 * @author: WangXiaoyu
 * @Date: 2018/5/31
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    private String state;
    private String message;
    private Object data;

    public static Response normResponse(String state, String message, Object data) {
        return new Response(state, message, data);
    }


    public static Response operateSucessAndHaveData(Object data) {
        return new Response(ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg(), data);
    }


    public static Response operateSucessNoData() {
        return new Response(ReturnCode.NODATA.getCode(), ReturnCode.SUCCESS.getMsg(), null);
    }


    public static Response databaseError(String msg) {
        return new Response(ReturnCode.DATABASE_ERROE.getCode(), msg, null);
    }


    public static Response paramError(String msg) {
        return new Response(ReturnCode.PARAM_ERROR.getCode(), msg, null);
    }

}
