package com.lei.aopProxy;

import com.lei.aop.Cal;
import com.lei.aop.CalImpl2;

public class MyTest {
    public static void main(String[] args) {
        /*目标对象*/
        Cal cal=new CalImpl2();
        MyJdkDynamicAopProxy myJdkDynamicAopProxy=new MyJdkDynamicAopProxy();
        Cal proxy= (Cal) myJdkDynamicAopProxy.bind(cal);//生成代理对象
        System.out.println(proxy.add(1,2));
    }
}
