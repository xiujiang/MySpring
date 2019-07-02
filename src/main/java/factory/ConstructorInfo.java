package factory;


import lombok.Data;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/1
 * @since 1.8
 */
@Data
public class ConstructorInfo implements ChildBeanDefinition {

    private Object value;
    private Integer index;
}
