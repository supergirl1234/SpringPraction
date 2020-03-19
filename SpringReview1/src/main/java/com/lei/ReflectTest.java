package com.lei;

/*IOC实现的前置知识：反射+XML解析*/

import com.lei.entiy.User;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) {
        /*
        * 反射的源头：Class：专门用来描述其他类的类
        * 一个Class对象就对应一个类
        * */
        try {
            Class claz= Class.forName("com.lei.entiy.User");
            System.out.println(claz);
            Class clazz=User.class;
            System.out.println(clazz);
            User user=new User();
            Class clazzz=user.getClass();
            System.out.println(clazzz);

            /*获取类中的方法、属性*/
            Method[] methods=claz.getMethods();//获取类中的所有方法
            for(Method item:methods){
                System.out.println(item);
            }
            Field[]  fields=claz.getFields();//获取所有属性
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
