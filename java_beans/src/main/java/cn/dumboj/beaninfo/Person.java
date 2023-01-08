package cn.dumboj.beaninfo;
/**
 * Person描述 POJO
 * 贫血模型的POJO 只有get/set方法
 * */
public class Person {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
