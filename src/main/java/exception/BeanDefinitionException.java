package exception;


/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/1
 * @since 1.8
 */
public class BeanDefinitionException extends RuntimeException{

    String code;
    BeanDefinitionException(String code,String message){
        super(message);
        this.code = code;
    }
    public BeanDefinitionException(String message){
        super(message);
    }

    public String getCode() {
        return code;
    }
}
