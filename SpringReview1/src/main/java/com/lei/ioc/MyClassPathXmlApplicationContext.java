package com.lei.ioc;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/*
* 1、IOC读取spring.xml获取bean相关信息、类信息、属性值信息（XML解析）
* 2、根据第一步获取的信息，动态创建对象（反射）
* */
public class MyClassPathXmlApplicationContext implements ApplicationContext {

    private  static Map<String,Object> iocContainer;
    static {
        iocContainer=new HashMap<>();
    }
    public MyClassPathXmlApplicationContext(String path) {
        /*解析XML*/
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read("src/main/resources/spring.xml");//读取xml文件

            Element root = document.getRootElement();//获取头标签

            Iterator<Element> iterator = root.elementIterator();//获取迭代器
            while (iterator.hasNext()) {//循环获取
                Element element = iterator.next();
                /*获取id值、Class值*/
                if (element.getName().equals("bean")) {
                    String id = element.attributeValue("id");//获取id标签的值
                    String className = element.attributeValue("class");//获取class标签的值

                    /*创建对象*/
                    Class clasz = Class.forName(className);//获取类Class对象,即获取目标类运行时类
                    /*获取无参构造函数*/
                    Constructor constructor = clasz.getConstructor();
                    /*调用构造器创建对象*/
                    Object object = constructor.newInstance();

                    Iterator<Element> elementInner = element.elementIterator();//又迭代一层，获取bean里面的一层,即property
                    while (elementInner.hasNext()) {
                        /*获取属性property值*/
                        Element property = elementInner.next();
                        String name = property.attributeValue("name");
                        String value = property.attributeValue("value");

                        /*set方法的名字*/
                        String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

                        /*给对象赋值*/
                        Field field = clasz.getDeclaredField(name);//获取成员变量
                        Class type = field.getType();//获取成员变量的类型

                        //获取set方法
                        Method method = clasz.getMethod(methodName, type);
                        /*获取与成员变量相同类型的属性值*/
                        Object realValue=value;
                        switch (type.getName()){
                            case "java.lang.Integer":
                                realValue=Integer.valueOf(value);
                                break;
                            case "java.lang.String":
                                break;
                        }
                        //利用invoke方法给对象赋值
                        method.invoke(object, realValue);

                        iocContainer.put(id,object);

                    }

                }
            }


        } catch (DocumentException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getApplicationName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public long getStartupDate() {
        return 0;
    }

    @Override
    public ApplicationContext getParent() {
        return null;
    }

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
        return null;
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return null;
    }

    @Override
    public boolean containsLocalBean(String s) {
        return false;
    }

    @Override
    public boolean containsBeanDefinition(String s) {
        return false;
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(ResolvableType resolvableType) {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(ResolvableType resolvableType, boolean b, boolean b1) {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(Class<?> aClass) {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(Class<?> aClass, boolean b, boolean b1) {
        return new String[0];
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> aClass) throws BeansException {
        return null;
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> aClass, boolean b, boolean b1) throws BeansException {
        return null;
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> aClass) {
        return new String[0];
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> aClass) throws BeansException {
        return null;
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String s, Class<A> aClass) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public Object getBean(String s) throws BeansException {

        return iocContainer.get(s);
    }

    @Override
    public <T> T getBean(String s, Class<T> aClass) throws BeansException {
        return null;
    }

    @Override
    public Object getBean(String s, Object... objects) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> aClass) throws BeansException {
        Collection values=iocContainer.values();
        for(Object object:values){
            if(object.getClass().equals(aClass)){
                return (T)object;
            }
        }
        return null;
    }

    @Override
    public <T> T getBean(Class<T> aClass, Object... objects) throws BeansException {
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(ResolvableType resolvableType) {
        return null;
    }

    @Override
    public boolean containsBean(String s) {
        return false;
    }

    @Override
    public boolean isSingleton(String s) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isPrototype(String s) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String s, ResolvableType resolvableType) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String s, Class<?> aClass) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public Class<?> getType(String s) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public Class<?> getType(String s, boolean b) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public String[] getAliases(String s) {
        return new String[0];
    }

    @Override
    public void publishEvent(Object o) {

    }

    @Override
    public String getMessage(String s, Object[] objects, String s1, Locale locale) {
        return null;
    }

    @Override
    public String getMessage(String s, Object[] objects, Locale locale) throws NoSuchMessageException {
        return null;
    }

    @Override
    public String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale) throws NoSuchMessageException {
        return null;
    }

    @Override
    public Environment getEnvironment() {
        return null;
    }

    @Override
    public Resource[] getResources(String s) throws IOException {
        return new Resource[0];
    }

    @Override
    public Resource getResource(String s) {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return null;
    }
}
