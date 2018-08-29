package ac.iie.server.task;

import ac.iie.server.service.IProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description: 定时触发的后台处理流程
 * @Author: wangxiaoyua
 * @CreateDate: 2018-8-29 9:06
 * @version: 1.0.0
 */
@Component
public class DataTask {

    @Autowired
    IProcService procService;

//    @Scheduled(fixedRate = 60 * 1000)
//    public void createComTask() {
//        procService.dealCreateCompetitionProc();
//    }

//    @Scheduled(fixedRate = 60 * 1000)
//    public void updateEvaStatusTask() {
//        procService.dealProgramStatus();
//    }
//
//    @Scheduled(fixedRate = 60 * 1000)
//    public void updateDetectionStatusTask() {
//        procService.dealDetecStatus();
//    }
}
