package cn.dumboj.beaninfo.register;

import cn.dumboj.beaninfo.instantiation.factory.PersonFactory;
import cn.dumboj.beaninfo.instantiation.factory.PersonFactoryImpl;
import cn.dumboj.beaninfo.instantiation.init.InitializationBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体Bean注册实例
 *注册Spring Bean的方式 ，2.通过注册外部单体对象注册 bean
 *
 *                      1. 通过BeanDefinition 注册
 * */
public class SingletonBeanRegisterDemo {
    public static void main(String[] args) {
        //创建ApplicationContext上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //创建外部实例化对象 personFactory
        PersonFactory personFactory = new PersonFactoryImpl();

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //注册外部单体实例bean
        beanFactory.registerSingleton("personFactory", personFactory);
        //刷新应用上下文
        context.refresh();

        //通过依赖查找的方式获取PersonFactory
        PersonFactory personFactoryLookUp = context.getBean("personFactory",PersonFactory.class);

        System.out.println("personFactory 是否等于 personFactoryLookUp :" + (personFactory == personFactoryLookUp));
        context.close();
    }
}
