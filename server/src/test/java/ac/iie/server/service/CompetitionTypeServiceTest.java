package ac.iie.server.service;

import ac.iie.server.domain.CompetitionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompetitionTypeServiceTest {
    @Autowired
    CompetitionTypeService competitionTypeService;

    @Test
    public void insert() {
        CompetitionType competitionType = new CompetitionType();
        competitionType.setName("算法大赛");
        competitionTypeService.insert(competitionType);
    }
}