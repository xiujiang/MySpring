package resource;


import exception.XmlReaderException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import utils.ObjectUtils;

import java.io.File;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/6/30
 * @since 1.8
 */
public class MyXmlResource implements Resource {

    String xmlPath;

    public MyXmlResource(String path){
        this.xmlPath = path;
    }

    @Override
    public Document readInfo(){
        return this.readInfo(xmlPath);
    }

    public Document readInfo(String path) {
        checkPath(path);
        Document document = doReadInfo(path);
        return document;
    }

    public void checkPath(String path){
        //check info
        if(ObjectUtils.isEmpty(path)){
            throw new XmlReaderException("文件路径错误");
        }
    }

    //做具体读取操作
    Document doReadInfo(String path){
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = null;
        String pathName = path;
        try {
            document = reader.read(new File(pathName));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

}
