package cn.dumboj.loopup;

import cn.dumboj.beaninfo.bean.Person;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;

/**
 * 通过 {@link ObjectProvider} 依赖查找
 * */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectProviderDemo.class);
        context.refresh();
        //通过ObjectProvider查找
        lookUpByObjectProvider(context);
        //查找不存在时，可以通过ObjectProvider完善创建一个新的对象
        lookUpIfAvilable(context);
        lookUpStreamOps(context);
        context.close();
    }

    private static void lookUpStreamOps(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        //可遍历的集合对象--两个bean
        /*Iterable<String> iterator = beanProvider;
        for (String s : iterator) {
            System.out.println(s);
        }*/

        //Stream 方式
        beanProvider.stream().forEach(System.out::println);
    }

    private static void lookUpIfAvilable(AnnotationConfigApplicationContext context) {
        ObjectProvider<Person> beanProvider = context.getBeanProvider(Person.class);
        //ObjectProvider.getIfAvailable() 不存在可以新创建满足要求的对象
        Person person = beanProvider.getIfAvailable(Person::creatPerson);
        System.out.println("当前Person对象：" + person);
    }

    private static void lookUpByObjectProvider(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        String providerTest = beanProvider.getObject();
        System.out.println(providerTest);
    }
    @Bean
    @Primary
    public String providerTest() {//@Bean不指定bean name ，则以方法名作为bean name
        return "this is the singleton dependency look up with BeanProvider ";
    }
    @Bean
    public String streamDemo() {
        return "message";
    }
}
