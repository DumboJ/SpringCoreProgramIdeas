package cn.dumboj.order.bean_definition_1_resolve;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解类型的 bean 元信息解析示例
 * */
public class AnnotationBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);
        // 注册当前类作为元信息 非 @Component 类
        reader.register(AnnotationBeanDefinitionReaderDemo.class);

        int count = beanFactory.getBeanDefinitionCount();
        //org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        // org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        // org.springframework.context.annotation.internalCommonAnnotationProcessor
        // org.springframework.context.event.internalEventListenerProcessor
        // org.springframework.context.event.internalEventListenerFactory

        /*普通类注册到Ioc容器后, bean的名称生成规则 由 BeanNamedGenerator 生成
        * 注解的 bean 由 AnnotationBeanNameGenerator 生成
        * */
        // annotationBeanDefinitionReaderDemo
        System.out.println("bean nums : " + count);

        AnnotationBeanDefinitionReaderDemo bean = beanFactory.getBean(AnnotationBeanDefinitionReaderDemo.class);
        System.out.println(bean);
    }
}
