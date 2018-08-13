package ac.iie.server.api.base;

public interface Contants {

    /**
     * 文件上传类型
     */
    int FILE_LOGO = 1;
    int FILE_DATA = 2;
    int FILE_PROGRAM = 3;
    int FILE_USER_ANSWER = 4;

    /**
     * 比赛状态比赛状态
     */
    // 0:数据校验中
    int COMPETITION_DATA_CHECK = 0;
    //-1:数据校验失败
    int COMPETITION_DATA_CHECK_FAILED = -1;
    //1:审核中
    int COMPETITION_CHECKING = 1;
    //2:审核未通过
    int COMPETITION_CHECKING_FAILED = 2;
    //3:报名中
    int COMPETITION_JOINNING = 3;
    //4:进行中
    int COMPETITION_GOING = 4;
    //5:结束
    int COMPETITION_END = 5;
    //6:已删除，默认0
    int COMPETITION_DELETE = 6;

    /**
     * 接口类型
     */
    String POST_INTERFACE = "POST";
    String GET_INTERFACE = "GET";

}
