package com.lei;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;


public class XMLTest {

    public static void main(String[] args) {
        /*将XML文件转换为一个对象*/

        SAXReader reader=new SAXReader();
        try {
            Document document=reader.read("src/main/resources/spring.xml");//读取xml文件
           // System.out.println(document);
            Element root=document.getRootElement();//获取头标签
            //System.out.println(root);
            Iterator<Element> iterator=root.elementIterator();//获取迭代器
            while (iterator.hasNext()){//循环获取
                 Element element=iterator.next();
                // System.out.println(element);
                if(element.getName().equals("bean")){
                    String value=element.attributeValue("id");//获取id标签的值
                    System.out.println(value);

                }
            }


        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
