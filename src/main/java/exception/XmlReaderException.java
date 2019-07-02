package exception;


/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/6/30
 * @since 1.8
 */
public class XmlReaderException extends RuntimeException {

    String code;
    XmlReaderException(String code,String message){
        super(message);
        this.code = code;
    }
    public XmlReaderException(String message){
        super(message);
    }

    public String getCode() {
        return code;
    }
}
