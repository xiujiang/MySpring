package factory;

import java.util.List;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/1
 * @since 1.8
 */
public class GeneralBeanDefinition extends AbstractBeanDefinition{

    public GeneralBeanDefinition() {
    }

    public GeneralBeanDefinition(String scope, boolean lazy, String initMethodName, String destroyMethodName, String beanClass, String targetClass, List<ChildBeanDefinition> childs) {
        super(scope, lazy, initMethodName, destroyMethodName, beanClass, targetClass, childs);
    }
}
