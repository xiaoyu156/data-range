package ac.iie.server.dao;

import ac.iie.server.domain.Competition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitionMapperTest {

    @Autowired
    CompetitionMapper competitionMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        for (int i = 0; i < 10; i++) {
            Competition competition = new Competition();
            competition.setStatus(1);
            competition.setType(0);
            competition.setLogoUrl("sssssss");
            competition.setIsIncludeMedia(true);
            competition.setDescription("测试");
            competition.setTypeId("sdfd");
            competition.setDataDesc("数据集描述");
            competition.setKeyDataMap("sssss");
            competition.setScoreItems("items");
            competition.setProgramUrl("sssssssss");
            competition.setTitle("sssss");
            competition.setContent("dfdf");
            competition.setDataUrl("dddddd");
            competitionMapper.insert(competition);
        }
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {
        System.out.println(competitionMapper.selectAll());
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void updateCompetitionTypeByTypeId() {
    }

    @Test
    public void getCompetitonByStatus() {

    }
}