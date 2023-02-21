package cn.dumboj.beaninfo.instantiation;

import cn.dumboj.beaninfo.bean.Person;
import cn.dumboj.beaninfo.instantiation.factory.PersonFactory;
import cn.dumboj.beaninfo.instantiation.factory.PersonFactoryImpl;
import cn.dumboj.beaninfo.register.API_Register;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Bean 特殊实例化方法
 *  1.ServiceLoader
 *  2.AutoWireCapableBeanFactory
 *  3.BeanDefinitionRegister 注册模块有实现 {@link API_Register }
 */
public class SpecialBeanInstantiationStaticMethod {
    public static void main(String[] args) {

        /**1原理（java/jvm ioc）.普通演示ServiceLoader获取/META-INF/services下的文件命名（接口）中定义路径（实现类）实例对象*/
        demoServiceLoadInstantiation();

        /**
         * {@link org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean}
         * 2（ServiceLoaderFactoryBean实例化Bean演示）.此方式需获取指定应用上个下文bean，通过定义的ServiceLoader定义的属性  <property name="serviceType"></property> 实例化
         * */

        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/special-bean-instantiation-context.xml");
        ServiceLoader<PersonFactory> serviceLoader = beanFactory.getBean("personByServiceLoaderFactoryBean", ServiceLoader.class);
        displayBean("ServiceLoader配置xml实例化Bean:",serviceLoader);

        /**
         * {@link org.springframework.beans.factory.config.AutowireCapableBeanFactory }
         * 3.(AutowireCapableBeanFactory实例化bean演示) ，此方式的AutowireCapableBeanFactory 需要通过ApplicationContext上下文获取
         * */
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/special-bean-instantiation-context.xml");
        AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
        PersonFactoryImpl bean = autowireCapableBeanFactory.createBean(PersonFactoryImpl.class);
        Person person = bean.factoryCreatePerson();
        System.out.println("AutoWireCapableBeanFactory.createBean并实例化："+person);

        //通过AutoWireCapableBeanFactory也可以通过ServiceLoader获取已经定义的实例化对象
        ServiceLoader<PersonFactory> autoWireCapableBeanFacotyLoader = context.getBean("personByServiceLoaderFactoryBean", ServiceLoader.class);
        displayBean("AutoWireCapableBeanFactory.getBean",autoWireCapableBeanFacotyLoader);
    }

    private static void demoServiceLoadInstantiation() {
        ServiceLoader<PersonFactory> serviceLoader = ServiceLoader.load(PersonFactory.class, Thread.currentThread().getClass().getClassLoader());
        displayBean("普通ServiceLoader原理实例化：",serviceLoader);
    }

    public static void displayBean(String desc,ServiceLoader<PersonFactory> serviceLoader) {

        Iterator<PersonFactory> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            PersonFactory personFactory = iterator.next();
            System.out.println(desc+personFactory.factoryCreatePerson());
        }
    }
}
