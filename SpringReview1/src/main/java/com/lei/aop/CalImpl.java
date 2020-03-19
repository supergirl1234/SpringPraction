package com.lei.aop;

/*普通的*/
public class CalImpl implements Cal {

    /*下面代码的特点是：重复代码太多，耦合度太多*/
    @Override
    public int add(int num1, int num2) {
        System.out.println("参数为：" + num1 + "、" + num2);
        int result = num1 + num2;
        return result;
    }

    @Override
    public int sub(int num1, int num2) {
        System.out.println("参数为：" + num1 + "、" + num2);
        int result = num1 - num2;
        return result;
    }

    @Override
    public int mul(int num1, int num2) {
        System.out.println("参数为：" + num1 + "、" + num2);
        int result = num1 * num2;
        return result;
    }

    @Override
    public int div(int num1, int num2) {
        System.out.println("参数为：" + num1 + "、" + num2);
        int result = num1 / num2;
        return result;
    }
}
