package com.lei.aopProxy;

import org.springframework.aop.framework.AopProxy;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/*自定义AOP的动态代理*/

/*源码中动态代理JdkDynamicAopProxy就是实现了AopProxy,InvocationHandler,Serializable这三个接口*/

public class MyJdkDynamicAopProxy implements AopProxy,InvocationHandler,Serializable {

    /*目标对象*/
    private  Object target;

    /*
    * 接收目标对象
    * 返回动态代理对象
    * */
    public  Object bind(Object object){
        this.target=object;
        /*调用getProxy*/
        return  this.getProxy(MyJdkDynamicAopProxy.class.getClassLoader());


    }

    /*
    * 业务代码的执行
    * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName()+"方法的参数为："+ Arrays.toString(args));//额外代理的业务
        Object result=method.invoke(target,args);//目标对象的业务
        System.out.println(method.getName()+"方法的参数为："+ result);//额外代理的业务
        return result;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        /*动态创建类，动态创建对象*/
        /*
        * classLoader:类加载器，作用是将动态类加载到JVM中的方法区中
        * target.getClass().getInterfaces():作用是  获取目标类的接口，因为接口定义功能，动态代理要知道目标类的功能
        * this
        * */
        return Proxy.newProxyInstance(classLoader,target.getClass().getInterfaces(),this);
    }
}
