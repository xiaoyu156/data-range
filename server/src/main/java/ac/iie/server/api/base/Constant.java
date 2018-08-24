package ac.iie.server.api.base;

/**
 * @Description: java类作用描述
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-17 10:22
 * @version: 1.0.0
 */
public interface Constant {

    /**
     * 比赛模式0:普通比赛模式，1：
     */
    int COMMON_PATTERN = 0;
    int TARGET_PATTERN = 1;

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
    Integer COMPETITION_DATA_CHECK_FAILED = 1;
    String COMPETITION_PROGRAM_CHECK_FAILED_MSG = "评测程序错误";
    String COMPETITION_DATA_CHECK_FAILED_MSG = "数据集错误";
    String COMPETITION_REDIS_CHECK_FAILED_MSG = "数据集存储错误";
    //1:审核中
    int COMPETITION_CHECKING = 2;
    String COMPETITION_CHECKING_MSG = "比赛审核中";
    //2:审核未通过
    int COMPETITION_CHECKING_FAILED = 3;
    String COMPETITION_CHECKING_FAILED_MSG = "比赛审核未通过";
    //3:报名中
    int COMPETITION_JOINNING = 4;
    String COMPETITION_JOINNING_MSG = "比赛报名中";
    //4:进行中
    int COMPETITION_GOING = 5;
    String COMPETITION_GOING_MSG = "比赛进行中";
    //5:结束
    int COMPETITION_END = 6;
    String COMPETITION_END_MSG = "比赛结束";
    //6:已删除，默认0
    int COMPETITION_DELETE = 7;
    String COMPETITION_DELETE_MSG = "比赛已经删除";

    /**
     * 用户答案版本状态,及描述,创建检测任务失败，检测中，检测完成
     */
    int ANSWER_CREATE_DETE_FAILED = 1;
    String ANSWER_CREATE_DETE_FAILED_MSG = "创建检测任务失败";
    int ANSWER_DATECTING = 2;
    String ANSWER_DETECTING_MSG = "检测中";
    int ANSWER_DATECTED = 3;
    String ANSWER_DATECTED_MSG = "检测完成";

    /**
     * 接口类型
     */
    String POST_INTERFACE = "POST";
    String GET_INTERFACE = "GET";


    /**
     * 创建测评程序
     */
    String CLOUD_CREATE_EVA = "createEvaluation";
    /**
     * 查询测评程序状态
     */
    String CLOUD_QUERY_EVA = "queryEvaluation";
    /**
     * 创建检测程序（靶场模式下）
     */
    String CLOUD_CREATE_DETE = "createDetection";
    /**
     * 检测程序状态查询
     */
    String CLOUD_QUERY_DETE = "queryDetection";
    /**
     * 提交测评任务(检测程序调用)
     */
    String CLOUD_COMMIT_EVA = "/evaluating/request";
    /**
     * 竞赛模式下上传结果答案
     */
    String CLOUD_UPLOAD_RESULT = "/result_file/upload";

}
