package ac.iie.server.dao;

import ac.iie.server.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        User user = new User();
        user.setAddress("北京市");
        user.setEmail("www");
        user.setIdCard("ssssssssssssssss");
        user.setLastLoginIp("10.10.1");
        user.setPassword("dffffff");
        user.setPhone("erere");
        user.setRealname("海龙");
        user.setStatus(1);
        user.setUsername("sdddddddd");
        userMapper.insert(user);
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void selectUserCompetitions() {
        System.out.println(userMapper.selectUserCompetitions("5512ee609b6b11e8a4b3201a0633876d"));
    }

    @Test
    public void updateByPrimaryKey() {
    }
}