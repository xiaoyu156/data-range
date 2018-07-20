package ac.iie.server.dao;

import ac.iie.server.domain.CompetitionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitionTypeMapperTest {

    @Autowired
    CompetitionTypeMapper competitionTypeMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        CompetitionType competitionType = new CompetitionType();
        competitionType.setName("算法大赛");
        competitionTypeMapper.insert(competitionType);
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
}