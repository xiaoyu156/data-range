package ac.iie.common.response;

/**
 * @Description: 状态
 * @author: WangXiaoyu
 * @Date: 2018/6/5
 */
public enum ReturnCode {
    SUCCESS("200", "操作成功！"), NODATA("201", "操作成功,但是没有获取到数据！"), DATABASE_ERROE("500"), PARAM_ERROR("501");
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ReturnCode(String code) {
        this.code = code;
    }

}
