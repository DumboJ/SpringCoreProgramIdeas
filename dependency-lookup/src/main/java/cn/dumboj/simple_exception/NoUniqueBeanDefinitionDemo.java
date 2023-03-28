package cn.dumboj.simple_exception;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.NoUniqueBeanDefinitionException}
 * 依赖查找上下文中存在不止一个单一类型bean
 * */
public class NoUniqueBeanDefinitionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(NoUniqueBeanDefinitionDemo.class);
        context.refresh();
        try {
            Object bean = context.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.out.printf("存在%d个%s类型的bean，原因：%s ",e.getNumberOfBeansFound(),e.getBeanType(),e.getMessage() );
        }
        context.close();
    }

    @Bean
    public String bean1() {
        return "1";
    }
    @Bean
    public String bean2() {
        return "2";
    }
    @Bean
    public String bean3() {
        return "3";
    }
}
