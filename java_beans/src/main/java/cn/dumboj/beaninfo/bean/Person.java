package cn.dumboj.beaninfo.bean;
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

    public static Person creatPerson() {
        Person person = new Person();
        person.setAge(22);
        person.setName("李行亮");
        return person;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
