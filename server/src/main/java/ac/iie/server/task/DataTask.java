package ac.iie.server.task;

import ac.iie.server.service.IProcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
