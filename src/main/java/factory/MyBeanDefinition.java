package factory;


import java.util.List;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/6/30
 * @since 1.8
 */
public interface MyBeanDefinition {


    void setScope(String scope);
    void setLazy(boolean lazy);
    void setChilds(List<ChildBeanDefinition> childs);
    void setInitMethodName(String initMethodName);
    void setDestroyMethodName(String destroyMethodName);
    void setBeanClass(String beanClass);
    void setTargetClass(String targetClass);


}
