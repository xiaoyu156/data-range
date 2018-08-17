package ac.iie.server.dao;

import ac.iie.server.domain.User;
import ac.iie.server.domain.UserCompetition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
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
}