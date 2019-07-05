package factory;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import constants.BeanScope;
import sun.reflect.generics.scope.Scope;
import utils.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/3
 * @since 1.8
 */
public abstract class AbstractApplicationContext implements MyBeanFactory{

    String[] locations = new String[5];
    DefaultListableBeanFactory beanFactory = null;

    private final List<MyBeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

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
        try{
            postProcessBeanFactory(beanFactory);
            invokeBeanFactoryPostProcessors(beanFactory);
            registerBeanPostProcessors(beanFactory);
            //消息通知，监听，这些暂时没有
            //最后初始化剩下的非lazy的SingleBean
            finishBeanFactoryInitialization(beanFactory);
        }catch (Exception e){
            //创建失败后，会销毁之前的bean
            destroyBeans();
            e.printStackTrace();
        }
    }

    /**
     * 这一步，其实就是刷新BeanFactory,注册BeanDefinition
     * @return
     */
    private MyBeanFactory obtainFreshBeanFactory(){
        MyBeanFactory beanFactory = getNewBeanFactory();
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
        loadBeanDefinitions(locations);
        return beanFactory;
    }

    MyBeanFactory getNewBeanFactory(){
        if(!ObjectUtils.isEmpty(this.beanFactory)){
            this.beanFactory.clear();
        }
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(String[] locations);

    public void prepareBeanFactory(MyBeanFactory beanFactory){

    }

    /**
     * 留给子类来实现
     * @param myBeanFactory
     */
    void postProcessBeanFactory(MyBeanFactory myBeanFactory){

    }

    void invokeBeanFactoryPostProcessors(MyBeanFactory beanFactory){

        for (MyBeanFactoryPostProcessor postProcessor : getBeanFactoryPostProcessors()) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }
    }
    void registerBeanPostProcessors(MyBeanFactory myBeanFactory){
        //其实注册这个BeanPostProcssor 就是从所有的bean或者BeanDefinition 里面，找到它的子类
        //然后getBean注册
        List<String> beanNames = this.beanFactory.getBeanNamesByTyps(MyBeanPostProcessor.class);
        //这里Spring 对BeanPostProcessor 做了区分，优先级的，内部的
        //在这里，DefaultBeanPostProcessor就已经注册了
        beanNames.forEach(o->{
            this.beanFactory.addBeanPostProcessor(this.beanFactory.getBean(o,MyBeanPostProcessor.class));
        });
    }
    void finishBeanFactoryInitialization(MyBeanFactory myBeanFactory){
        //Spring 会先初始化conversionService 这个类
        Set<String> beanNames = this.beanFactory.getBeanNams();
        //Spring在实例化的时候，也会对那些FactoryBean实例化
        beanNames.forEach(o->{
            GeneralBeanDefinition beanDefinition = (GeneralBeanDefinition) DefaultListableBeanFactory.myBeanDefinitions.get(o);
            if(!beanDefinition.lazy && BeanScope.SINGLETON.equals(beanDefinition.scope)){
                this.beanFactory.getBean(o);
            }
        });
        //Srping 中对 SmartInitializingSingleton 的初始化调用，在这里
    }

    public List<MyBeanFactoryPostProcessor> getBeanFactoryPostProcessors() {
        return this.beanFactoryPostProcessors;
    }

    void destroyBeans(){
        this.beanFactory.clear();
    }
}
