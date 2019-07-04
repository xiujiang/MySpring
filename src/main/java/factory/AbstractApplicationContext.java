package factory;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/3
 * @since 1.8
 */
public abstract class AbstractApplicationContext {
    String[] locations = new String[5];

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    //其实ApplicationContext的refresh 就是在注册bean的基础上，提供了更多的功能
    //按照Srping顺序
    public void refresh(){
        //刷新或注册beanFactory
        MyBeanFactory beanFactory = obtainFreshBeanFactory();

        //提前对beanFactory注册一些功能
        prepareBeanFactory(beanFactory);
    }

    private MyBeanFactory obtainFreshBeanFactory(){
        return null;
    }
    public void prepareBeanFactory(MyBeanFactory beanFactory){

    }
}
