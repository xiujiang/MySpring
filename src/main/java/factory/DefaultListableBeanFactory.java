package factory;


import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/1
 * @since 1.8
 */
public class DefaultListableBeanFactory {
    private static Map<String,MyBeanDefinition> myBeanDefinitions = new HashMap<String,MyBeanDefinition>();



    public void registerBeanDefinition(String name,MyBeanDefinition myBeanDefinition){
        if(myBeanDefinitions.containsKey(name)){
            return;
        }
        myBeanDefinitions.put(name,myBeanDefinition);
    }
}
