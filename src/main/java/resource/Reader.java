package resource;


import org.dom4j.Document;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/6/30
 * @since 1.8
 */
public interface Reader {


    void registerBeanDefinitions(Document document);
}
