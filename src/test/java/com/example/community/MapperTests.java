package com.example.community;

import com.example.community.dao.DiscussPostMapper;
import com.example.community.dao.UserMapper;
import com.example.community.entity.DiscussPost;
import com.example.community.entity.User;
import com.example.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private MailClient mailClient;

    @Test
    public void testSelectUser(){
        User user=userMapper.selectById(102);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);

    }

    @Test
    public void testInsertUser(){
        User user=new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        System.out.println(userMapper.insertUser(user));
    }

    @Test
    public void testUpdateUser(){
        System.out.println(userMapper.updateStatus(150,5));
    }

    @Test
    public void testDiscuss(){
        List<DiscussPost> list=discussPostMapper.selectDiscussPosts(0,0,10);
        for(DiscussPost dis:list)
            System.out.println(dis);
        System.out.println(discussPostMapper.selectDiscussPostRows(101));
    }

    @Test
    public void testMail(){
        mailClient.sendMail("ysf981106@163.com","mail test","hello ysf");
        mailClient.sendMail("2583825704@qq.com","mail test","hello ysf");
    }
}
