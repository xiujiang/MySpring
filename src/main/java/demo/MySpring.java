package demo;

import demo.entity.Animal;
import demo.entity.Person;
import factory.MyClassPathXmlApplicationContext;
import factory.XmlBeanFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/2
 * @since 1.8
 */
public class MySpring {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
//        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory("src/main/resources/application.xml");
//        xmlBeanFactory.showBeanDefinitionInfo();
        MyClassPathXmlApplicationContext applicationContext = new MyClassPathXmlApplicationContext(true,"src/main/resources/application.xml");
        System.out.println(applicationContext);
//        Animal animal = (Animal) applicationContext.getBean("animal");
        Person person = (Person) applicationContext.getBean("t1");
        System.out.println(person);

    }



}
