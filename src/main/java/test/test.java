package test;

import facade.WebLogService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pojo.bo.WebLogBO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试类
 * Created by liuyanling on 2018/3/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})

public class test {
    @Autowired
    private WebLogService webLogService;
    @Test
    public void webtest(){
        List<WebLogBO> list = webLogService.webcount();
        System.out.println(list);
        Long websum = webLogService.websum();
        System.out.println(websum);
        Long peoplesum = webLogService.peoplesum();
        System.out.println(peoplesum);
    }




}
