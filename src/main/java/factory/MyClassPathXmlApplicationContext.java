package factory;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


/**
 * ApplicationContext其实就在BeanFactory的基础上增加了更多的功能
 * 基本的IOC功能还是由beanFactory实现
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/3
 * @since 1.8
 */
public class MyClassPathXmlApplicationContext extends AbstractApplicationContext{

    //可以读取多个配置文件的内容
    MyClassPathXmlApplicationContext(boolean refresh, String... configLocations){
        setLocations(configLocations);
        if(refresh){
            refresh();
        }
    }

}
