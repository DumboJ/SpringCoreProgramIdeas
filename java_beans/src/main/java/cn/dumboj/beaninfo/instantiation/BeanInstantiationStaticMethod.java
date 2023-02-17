package cn.dumboj.beaninfo.instantiation;

import cn.dumboj.beaninfo.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化方法
 */
public class BeanInstantiationStaticMethod {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-instantiation-context.xml");

        /*静态方法获取*/
        Person userByStaticMethod = beanFactory.getBean("personLi",Person.class);
        System.out.println(userByStaticMethod);

        /*Bean工厂方法获取*/
        Person personByInstanceMethod = beanFactory.getBean("factoryInstantiationPerson", Person.class);
        System.out.println(personByInstanceMethod);
    }
}
