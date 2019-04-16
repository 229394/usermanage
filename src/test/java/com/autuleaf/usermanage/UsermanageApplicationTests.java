package com.autuleaf.usermanage;

import com.autuleaf.usermanage.entity.UserInfo;
import com.autuleaf.usermanage.repository.UserRepository;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsermanageApplicationTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
    }

    /**
     * 测试方法
     */
    @Test
    public void test1(){
        UserInfo u1 = userRepository.findById(1L).get();
        System.out.println(u1);
    }

}
