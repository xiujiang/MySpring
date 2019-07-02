package demo;

import demo.entity.Animal;
import factory.XmlBeanFactory;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/2
 * @since 1.8
 */
public class MySpring {

    public static void main(String[] args) {
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory("src/main/resources/application.xml");
        xmlBeanFactory.showBeanDefinitionInfo();

        Animal animal = xmlBeanFactory.getBean("a1");
        System.out.println(animal);

    }



}
