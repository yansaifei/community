package com.example.community;

import com.example.community.dao.CommentMapper;
import com.example.community.dao.DiscussPostMapper;
import com.example.community.dao.LoginTicketMaper;
import com.example.community.dao.UserMapper;
import com.example.community.entity.DiscussPost;
import com.example.community.entity.LoginTicket;
import com.example.community.entity.User;
import com.example.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMaper loginTicketMapper;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private CommentMapper commentMapper;

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

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "sunday");

        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("ysf981106@163.com", "HTML", content);
    }


    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc", 1);
        loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);
    }


    @Test
    public void testComment() {
        System.out.println(commentMapper.selectCountByEntity(1,275));
    }
}
