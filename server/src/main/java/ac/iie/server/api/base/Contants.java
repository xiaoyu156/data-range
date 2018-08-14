package ac.iie.server.api.base;

public interface Contants {

    /**
     * 文件上传类型
     */
    int FILE_LOGO = 1;
    int FILE_DATA = 2;
    int FILE_PROGRAM = 3;
    int FILE_USER_ANSWER_RESULT = 4;
    int FILE_USER_ANSWER_ENGINE = 5;

    /**
     * 比赛状态比赛状态
     */
    // 0:数据校验中
    int COMPETITION_DATA_CHECK = 0;
    String COMPETITION_DATA_CHECK_MSG = "数据集校验中";
    //-1:数据校验失败
    int COMPETITION_DATA_CHECK_FAILED = -1;
    String COMPETITION_PROGRAM_CHECK_FAILED_MSG = "评测程序错误";
    String COMPETITION_DATA_CHECK_FAILED_MSG = "数据集错误";
    String COMPETITION_REDIS_CHECK_FAILED_MSG = "数据集存储错误";
    //1:审核中
    int COMPETITION_CHECKING = 1;
    String COMPETITION_CHECKING_MSG = "比赛审核中";
    //2:审核未通过
    int COMPETITION_CHECKING_FAILED = 2;
    String COMPETITION_CHECKING_FAILED_MSG = "比赛审核未通过";
    //3:报名中
    int COMPETITION_JOINNING = 3;
    String COMPETITION_JOINNING_MSG = "比赛报名中";
    //4:进行中
    int COMPETITION_GOING = 4;
    String COMPETITION_GOING_MSG = "比赛进行中";
    //5:结束
    int COMPETITION_END = 5;
    String COMPETITION_END_MSG = "比赛结束";
    //6:已删除，默认0
    int COMPETITION_DELETE = 6;
    String COMPETITION_DELETE_MSG = "比赛已经删除";

    /**
     * 接口类型
     */
    String POST_INTERFACE = "POST";
    String GET_INTERFACE = "GET";

}
