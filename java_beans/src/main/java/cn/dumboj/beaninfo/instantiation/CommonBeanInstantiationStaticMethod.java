package cn.dumboj.beaninfo.instantiation;

import cn.dumboj.beaninfo.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 普通实例化方法
 * 1.构造参数
 * 2.静态方法
 * 3，FactoryBean 实现类 implements FactoryBean
 * 4.工厂方法  在接口中提供 default 实现，通过调用实例对象Bean的静态实现
 */
public class CommonBeanInstantiationStaticMethod {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-instantiation-context.xml");

        /*静态方法获取*/
        Person userByStaticMethod = beanFactory.getBean("personLi",Person.class);
        System.out.println(userByStaticMethod);

        /*Bean工厂方法获取*/
        Person personByInstanceMethod = beanFactory.getBean("factoryInstantiationPerson", Person.class);
        System.out.println(personByInstanceMethod);

        /*FactoryBean 方式实例化Bean*/
        Person factoryBeanPerson = beanFactory.getBean("bean-by-factoryBean", Person.class);
        System.out.println(factoryBeanPerson);
    }
}
