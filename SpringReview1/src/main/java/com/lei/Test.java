package com.lei;

import com.lei.entiy.User;
import com.lei.ioc.MyClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        String[] strings = applicationContext.getBeanDefinitionNames();//获取所有Bean的名字
        User user = (User) applicationContext.getBean("user");//获取某个Bean
        System.out.println(user);
        System.out.println("-------------");
        ApplicationContext applicationContext2 = new MyClassPathXmlApplicationContext("spring.xml");
        User user2= (User) applicationContext2.getBean("user");
        System.out.println(user2);
        ApplicationContext applicationContext3 = new MyClassPathXmlApplicationContext("spring.xml");
        User user3= (User) applicationContext2.getBean(User.class);
        System.out.println(user3);

    }
}
