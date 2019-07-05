package demo;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import exception.BeansException;
import factory.MyBeanPostProcessor;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/5
 * @since 1.8
 */
public class DefaultBeanPostProcessor implements MyBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("hahah ,我在前面执行了。。"+beanName);
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("hahah，我在后面执行。。。"+beanName);
        return bean;
    }
}
