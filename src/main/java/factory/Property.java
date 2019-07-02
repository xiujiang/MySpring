package factory;

import lombok.Data;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/1
 * @since 1.8
 */
@Data
public class Property implements ChildBeanDefinition {
    private String name;
    private Object value;
}
