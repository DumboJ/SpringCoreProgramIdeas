package cn.dumboj.dependency.lookup;

import cn.dumboj.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring Ioc 以来查找demo
 * */
public class DependencyLookUpDemo {
    public static void main(String[] args) {
        //配置xml文件
        //启动Spring上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        User user = (User) beanFactory.getBean("user");
    }
}
