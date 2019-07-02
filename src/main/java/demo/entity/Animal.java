package demo.entity;

import lombok.Data;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/1
 * @since 1.8
 */
@Data
public class Animal {
    private Integer id;
    private String name;
    private String weight;
    public Animal(Integer id,String name,String weight){

    }
    public Animal(String name,String weight){

    }
    public Animal(){

    }
}
