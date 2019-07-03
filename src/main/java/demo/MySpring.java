package demo;

import demo.entity.Animal;
import demo.entity.Person;
import factory.XmlBeanFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/2
 * @since 1.8
 */
public class MySpring {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory("src/main/resources/application.xml");
        xmlBeanFactory.showBeanDefinitionInfo();

        Animal animal = xmlBeanFactory.getBean("animal");
        Person person = xmlBeanFactory.getBean("t1");
        System.out.println(person);
        System.out.println(animal);

    }



}
