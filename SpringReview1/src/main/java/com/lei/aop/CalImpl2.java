package com.lei.aop;

import org.springframework.stereotype.Component;

/*使用AOP来解耦*/
@Component
public class CalImpl2 implements Cal {
    @Override
    public int add(int num1, int num2) {

        int result = num1 + num2;
        return result;
    }

    @Override
    public int sub(int num1, int num2) {

        int result = num1 - num2;
        return result;

    }

    @Override
    public int mul(int num1, int num2) {

        int result = num1 * num2;
        return result;
    }

    @Override
    public int div(int num1, int num2) {

        int result = num1 / num2;
        return result;
    }
}
