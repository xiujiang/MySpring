package demo.entity;

import lombok.Data;

@Data
public class Person {
    private Integer id;
    private String name;
    private String age;

    public void init(){
        System.out.println("我在执行初始化方法 哈哈哈 。。。");
    }
}
