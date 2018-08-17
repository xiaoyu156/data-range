package ac.iie.server.service;

import ac.iie.server.domain.Competition;
import ac.iie.server.domain.UserCompetition;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserCompetitionService {
    boolean joinCompetition(UserCompetition userCompetition);

    boolean isJoined(UserCompetition userCompetition);

    List<UserCompetition> selectByUid(UserCompetition userCompetition);
}
