package cn.dumboj.container;

import cn.dumboj.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * {@link ApplicationContext } ApplicationContext 作为Ioc容器 demo
 * */
public class ApplicationContextAsIocContainer {
    public static void main(String[] args) {
        //创建BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //配置类注册   将当前类作为配置类使用
        applicationContext.register(ApplicationContextAsIocContainer.class);
        //启动应用上下文
        /**
         * 不启动会触发异常
         *  org.springframework.context.annotation.AnnotationConfigApplicationContext@5e9f23b4 has not been refreshed yet
         * */
        applicationContext.refresh();
        //依赖查找集合对象
        lookUpByType(applicationContext);
    }
    /**
     * 通过Java代码的方式定义一个Bean
     * */
    @Bean
    public User getUser() {
        User user = new User();
        user.setId(2L);
        user.setName("qianjun");
        return user;
    }
    private static void lookUpByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(beansOfType);
        }
    }
}
