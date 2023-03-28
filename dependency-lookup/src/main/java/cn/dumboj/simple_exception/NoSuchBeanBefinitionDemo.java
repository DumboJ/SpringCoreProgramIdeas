package cn.dumboj.simple_exception;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link org.springframework.beans.factory.NoSuchBeanDefinitionException}
 * */
public class NoSuchBeanBefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(NoSuchBeanBefinitionDemo.class);
        context.refresh();
        try {
            Object bean = context.getBean(String.class);
        } catch (NoSuchBeanDefinitionException e) {
            throw new RuntimeException(e);
        }
        context.close();
    }
}
