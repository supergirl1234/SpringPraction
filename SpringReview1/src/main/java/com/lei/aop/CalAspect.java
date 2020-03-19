package com.lei.aop;

import org.aspectj.apache.bcel.classfile.Signature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*切面类*/
@Component   //将其注入到IOC中
@Aspect  //声明是一个切面类
public class CalAspect {
    /*
     * 打印日志的方法，即输出参数的方法
     * */
    /*@Before表示在什么时间点执行，括号里面的内容代表在那些方法上执行*/
    /*JoinPoint是连接点，用来连接切面与目标方法的*/
    @Before("execution(public int com.lei.aop.CalImpl2.*(..))")
    public void beforPrint(JoinPoint joinPoint) {

        String name = joinPoint.getSignature().getName();//获取目标方法的名字
        System.out.println(name + "方法的参数为：" + Arrays.toString(joinPoint.getArgs()));//joinPoint.getArgs()是用来获取目标方法的参数

    }

    /*returning = "result"  就是目标方法中的返回值，result是自己取得名字*/
    @AfterReturning(value = "execution(public int com.lei.aop.CalImpl2.*(..))",returning = "result")
    public void AfterPrint(JoinPoint joinPoint,Object result) {
        String name = joinPoint.getSignature().getName();//获取目标方法的名字
        System.out.println(name + "方法的结果为：" + result);//joinPoint.getArgs()是用来获取目标方法的参数


    }
}
