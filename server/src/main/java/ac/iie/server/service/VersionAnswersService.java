package ac.iie.server.service;

import ac.iie.server.domain.VersionAnswers;

import java.util.List;

public interface VersionAnswersService {
    boolean commitAnswer(VersionAnswers versionAnswers);
    List<VersionAnswers> getUserAnswerByUserCompId(String userCompId );
    int updateScore(VersionAnswers versionAnswers);

}
