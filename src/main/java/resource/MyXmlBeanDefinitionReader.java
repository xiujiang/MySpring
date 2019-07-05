package resource;


import constants.InjectRules;
import constants.XmlRules;
import exception.BeanDefinitionException;
import exception.XmlReaderException;
import factory.*;
import org.dom4j.Document;
import org.dom4j.Element;
import utils.ObjectUtils;

import java.util.*;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/6/30
 * @since 1.8
 */
public class MyXmlBeanDefinitionReader implements Reader{

    DefaultListableBeanFactory defaultListableBeanFactory;

    public MyXmlBeanDefinitionReader(DefaultListableBeanFactory defaultListableBeanFactory){
        this.defaultListableBeanFactory = defaultListableBeanFactory;
    }

    @Override
    public void registerBeanDefinitions(Document document) {
        checkDocument(document);
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        elements.forEach(o->{
            this.doRegisterBeanDefinition(o);
        });

    }

    public Map<String,String> getAttributeValue(Element o){
        Map<String,String> attributeMap = new HashMap<>();
        attributeMap.put("id",o.attributeValue(XmlRules.BEAN_ID));
        attributeMap.put("alies",o.attributeValue(XmlRules.BEAN_ALIES));
        attributeMap.put("beanClass",o.attributeValue(XmlRules.BEAN_CLASS));
        attributeMap.put("lazy",o.attributeValue(XmlRules.BEAN_LAZY));
        attributeMap.put("initMethod",o.attributeValue(XmlRules.BEAN_INIT_METHOD));
        attributeMap.put("destroyMethod",o.attributeValue(XmlRules.EEAN_DESTROY_METHOD));
        attributeMap.put("targetClass",o.attributeValue(XmlRules.BEAN_TARGET_CLASS));
        attributeMap.put("scope",o.attributeValue(XmlRules.BEAN_SCOPE));
        return attributeMap;

    }


    void doRegisterBeanDefinition(Element o){
        //如果是bean标签，则解析bean
        if(XmlRules.BEAN.equals(o.getName())){
            Map<String,String> attributeMap = getAttributeValue(o);
            GeneralBeanDefinition generalBeanDefinition = new GeneralBeanDefinition(
                    attributeMap.get("scope"),new Boolean(attributeMap.get("lazy")),attributeMap.get("initMethod"),
                    attributeMap.get("destroyMethod"),attributeMap.get("beanClass"),attributeMap.get("targetClass"),null
            );
            List<Element> childElements = o.elements();
            if(!ObjectUtils.isEmpty(childElements)){
                List<ChildBeanDefinition> childBeanDefinitions = new ArrayList<>();
                childElements.forEach(c->{
                    //构造器注入
                    if(XmlRules.CON_ARG.equals(c.getName())){
                        ConstructorInfo childBeanDefinition = new ConstructorInfo();
                        childBeanDefinition.setIndex(Integer.parseInt(c.attributeValue(XmlRules.CON_INDEX)));
                        childBeanDefinition.setValue(c.attributeValue(XmlRules.CON_VALUE));
                        childBeanDefinitions.add(childBeanDefinition);
                    }else if(XmlRules.PROPERTY.equals(c.getName())){
                        //setter注入
                        Property property = new Property();
                        property.setValue(c.attributeValue(XmlRules.PROPERTY_VALUE));
                        property.setName(c.attributeValue(XmlRules.PROPERTY_NAME));
                        childBeanDefinitions.add(property);
                    }
                });
                generalBeanDefinition.setChilds(childBeanDefinitions);
            }
            defaultListableBeanFactory.registerBeanDefinition(attributeMap.get("id"),generalBeanDefinition);
            if(!ObjectUtils.isEmpty(attributeMap.get("alies"))){
                String[] args = attributeMap.get("alies").split(",");
                List t = Arrays.asList(args);
                defaultListableBeanFactory.registerAlies(attributeMap.get("id"),t);
            }
        }
    }

    public void checkDocument(Document document){
        if(ObjectUtils.isEmpty(document)){
            throw  new BeanDefinitionException("document错误");
        }
    }
}
