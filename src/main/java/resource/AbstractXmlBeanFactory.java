package resource;



import org.dom4j.Document;

import java.util.concurrent.AbstractExecutorService;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/6/30
 * @since 1.8
 */
public class AbstractXmlBeanFactory{
    Reader reader = new MyXmlBeanDefinitionReader();
    Resource resource;
    AbstractXmlBeanFactory(Resource resource){
        this.resource = resource;
        this.loadBeanDefinitions();
    }

    public void loadBeanDefinitions(){
        Document documents = resource.readInfo();
        reader.registerBeanDefinitions(documents);
    }


}
