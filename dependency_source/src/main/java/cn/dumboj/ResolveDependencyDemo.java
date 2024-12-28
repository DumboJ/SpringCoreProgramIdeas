package cn.dumboj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory}
 *
 * 可以在Spring 上下文刷新前通过
 * context.addBeanFactoryPostProcessor(DefaultListableBeanFactory beanFactory)
 * 来注入
 * e.g:
 * {@link org.springframework.beans.factory.config.ConfigurableListableBeanFactory
 *  * #registerResolvableDependency(Class, Object)}
 * */
public class ResolveDependencyDemo {
//    @Autowired(required = false)//不限定依赖注入的bean必须时不会触发refresh()的异常
    @Autowired
    private String value;
    @PostConstruct
    public void getValue() {
        System.out.println(value);
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ResolveDependencyDemo.class);
        context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerResolvableDependency(String.class,"Hello World!");
        });
        context.refresh();
        //在此处刷新后上下文中已经存在了String.class的bean，refresh()会触发异常
       /* AutowireCapableBeanFactory autowireCapableBeanFactory = context.getAutowireCapableBeanFactory();
        if (autowireCapableBeanFactory instanceof DefaultListableBeanFactory) {
            DefaultListableBeanFactory cast = DefaultListableBeanFactory.class.cast(autowireCapableBeanFactory);
            cast.registerResolvableDependency(String.class,"Hello World!");
        }*/
        context.close();
    }
}
