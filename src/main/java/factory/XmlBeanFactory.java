package factory;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import org.dom4j.Document;
import resource.MyXmlBeanDefinitionReader;
import resource.MyXmlResource;
import resource.Reader;
import resource.Resource;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/2
 * @since 1.8
 */
public class XmlBeanFactory extends DefaultListableBeanFactory {
    Reader reader = new MyXmlBeanDefinitionReader(this);
    Resource resource;
    public XmlBeanFactory(Resource resource){
        this.resource = resource;
        this.loadBeanDefinitions();
    }
    public XmlBeanFactory(String path){
        this.resource = new MyXmlResource(path);
        this.loadBeanDefinitions();
    }

    public void loadBeanDefinitions(){
        Document documents = resource.readInfo();
        reader.registerBeanDefinitions(documents);
    }
}
