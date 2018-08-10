package ac.iie.server.service;

import ac.iie.server.dao.UserCompetitionMapper;
import ac.iie.server.domain.UserCompetition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCompetitionServiceTest {

    @Autowired
    UserCompetitionMapper userCompetitionMapper;
    @Test
    public void joinCompetition() {
        UserCompetition userCompetition=new UserCompetition();
        userCompetition.setType(0);
        userCompetition.setStatus(1);
        userCompetition.setCompetitionId("66fb58519b6c11e8a4b3201a0633876d");
        userCompetition.setUserId("5512ee609b6b11e8a4b3201a0633876d");
        userCompetitionMapper.insert(userCompetition);
    }

    @Test
    public void isJoined() {
    }
}