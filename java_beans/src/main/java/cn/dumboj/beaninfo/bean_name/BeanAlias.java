package cn.dumboj.beaninfo.bean_name;

import cn.dumboj.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名
 * {@link org.springframework.beans.factory.support.BeanNameGenerator}
 * {@link org.springframework.context.annotation.AnnotationBeanNameGenerator}
 * */
public class BeanAlias {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/alias.xml");
        User user = context.getBean("user", User.class);
        User user_alias = context.getBean("user_alias", User.class);
        System.out.println(user == user_alias);
    }
}
