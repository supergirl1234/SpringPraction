package com.lei.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aopSpring.xml");
        /*String[] strings = applicationContext.getBeanDefinitionNames();//获取所有Bean的名字
        for(String item:strings){
            System.out.println(item);
        }*/
        Cal cal=applicationContext.getBean(Cal.class);
        System.out.println(cal.add(1,1));//就会发现，会输出日志
    }
}
