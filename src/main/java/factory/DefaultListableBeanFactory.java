package factory;


import constants.BeanScope;
import exception.BeanDefinitionException;
import utils.ObjectUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/1
 * @since 1.8
 */
public class DefaultListableBeanFactory {

    private static Map<String,MyBeanDefinition> myBeanDefinitions = new HashMap<String,MyBeanDefinition>();
    //别名
    private static Map<String,String> myAliesMap = new HashMap<>();

    private static Map<String,Object> mySingletonObjects = new ConcurrentHashMap<>(256);

    public void registerBeanDefinition(String name,MyBeanDefinition myBeanDefinition){
        if(myBeanDefinitions.containsKey(name)){
            return;
        }
        myBeanDefinitions.put(name,myBeanDefinition);
    }
    public void registerAlies(String name, List<String> alies){
        if(ObjectUtils.isEmpty(name) || ObjectUtils.isEmpty(alies)){
            throw new BeanDefinitionException("别名注册失败");
        }
       alies.forEach(o->{
           myAliesMap.put(o,name);
       });
    }
    public void showBeanDefinitionInfo(){
        myBeanDefinitions.forEach((k,v)->{
            System.out.println("key:"+k+" value: "+v);
        });
        myAliesMap.forEach((k,v)->{
            System.out.println("alies: "+k+" beanName:"+v);
        });
    }

    public <T> T getBean(String name){
        return doGetBean(name,null,null);
    }
    public <T> T getBean(String name,Class<T> requiredType){
        return doGetBean(name,requiredType,null);
    }
    public <T> T getBean(String name,Object ...args){
        return doGetBean(name,null,args);
    }

    public <T> T doGetBean(String name,Class<T> requiredType,Object ...args){
        String beanName = transferName(name);
        Object obj = getSingleton(beanName);
        //没有在缓存中获取到
        GeneralBeanDefinition beanDefinition = (GeneralBeanDefinition) myBeanDefinitions.get(beanName);
        if(obj == null){
            if(beanDefinition==null){
                return null;
            }
            //单例的处理,这里对scope 就简单处理，Spring其实对单例的处理有很多
            if(BeanScope.SINGLETON.equals(beanDefinition.scope)){
                obj = createBean(beanName,beanDefinition);
                mySingletonObjects.put(beanName,obj);
            }else if(BeanScope.PROTOTYPE.equals(beanDefinition.scope)){
                obj = createBean(beanName,beanDefinition);
            }
        }

        if(obj==null){
            return null;
        }
        //注入属性
        populateBean(beanDefinition,beanName);

        //这里先暂时不考虑转换covert
//        if(requiredType != null && requiredType.isInstance(obj)){
        if(requiredType !=null){
            return (T)obj;
        }
        return (T)obj;
    }


    //这个name 是beanName,或者是alies
    public String transferName(String name){
        return myAliesMap.get(name)!=null?myAliesMap.get(name):name;
    }
    public Object getSingleton(String beanName){
        return mySingletonObjects.get(beanName);
    }

    //这里属性都在definition里了，所以直接赋值就好
    public void populateBean(GeneralBeanDefinition definition,String beanName){
        List<ChildBeanDefinition> childList = definition.childs;
        if(ObjectUtils.isEmpty(childList)){
            return;
        }
        Object obj = mySingletonObjects.get(beanName);
        Class clazz = obj.getClass();
        //setter注入
        if(childList.get(0) instanceof Property){
            //填充属性，用反射填充
            Field[] fields =clazz.getDeclaredFields();
            childList.forEach(o->{
                for (Field field : fields) {
                    field.setAccessible(true);
                    if(field.getName().equals(((Property)o).getName())){
                        try {
                            field.set(obj,((Property) o).getValue());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }else if(childList.get(0) instanceof ConstructorInfo){
            //构造器注入
            Constructor[] constructors = clazz.getDeclaredConstructors();
            //找一个合适的构造器
            int argsCount = childList.size();
            Object[] objects = childList.stream().sorted().map(o->{return ((ConstructorInfo)o).getValue();}).toArray();
            for (Constructor constructor : constructors) {
                if(constructor.getParameterCount()!=argsCount){
                    continue;
                }
                try {
                    constructor.newInstance(objects);
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }
        }

    }

    //生成bean对象
    public Object createBean(String beanName,GeneralBeanDefinition definition){
        try {
            Class clazz = Class.forName(definition.beanClass);
            Constructor c = clazz.getDeclaredConstructor();
            return c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
