package factory;


import constants.BeanScope;

import java.util.List;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/1
 * @since 1.8
 */
public class AbstractBeanDefinition implements MyBeanDefinition{

    //bean范围
    String scope = BeanScope.SINGLETON;
    //懒加载
    boolean lazy=false;
    //初始化方法
    String initMethodName = null;
    //销毁方法
    String destroyMethodName = null;
    //构造类
    String beanClass=null;
    //目标类
    String targetClass=null;
    //子元素
    List<ChildBeanDefinition> childs = null;

    AbstractBeanDefinition(){
        super();
    }

    public AbstractBeanDefinition(String scope, boolean lazy, String initMethodName, String destroyMethodName, String beanClass, String targetClass, List<ChildBeanDefinition> childs) {
        this.scope = scope;
        this.lazy = lazy;
        this.initMethodName = initMethodName;
        this.destroyMethodName = destroyMethodName;
        this.beanClass = beanClass;
        this.targetClass = targetClass;
        this.childs = childs;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    @Override
    public void setChilds(List<ChildBeanDefinition> childs) {
        this.childs = childs;
    }

    @Override
    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    @Override
    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    @Override
    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }
}
