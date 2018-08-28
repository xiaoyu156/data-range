package ac.iie.server.dao;

import ac.iie.server.domain.User;
import ac.iie.server.domain.UserCompetition;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserCompetitionMapperTest {

    @Autowired
    UserCompetitionMapper userCompetitionMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByUidAndComId() {
    }

    @Test
    public void selectByUid() {
        UserCompetition userCompetition=new UserCompetition();
        userCompetition.setUserId("5512ee609b6b11e8a4b3201a0633876d");
        System.out.println(userCompetitionMapper.selectByUid(userCompetition));
    }

    @Test
    public void selectByCompIdAndType() {
        UserCompetition userCompetition=new UserCompetition();
        userCompetition.setCompetitionId("8ce0d107aa8d11e899b8201a0633876d");
        userCompetition.setStatus(1);
        System.out.println(userCompetitionMapper.selectByCompIdAndType(userCompetition));
    }
}