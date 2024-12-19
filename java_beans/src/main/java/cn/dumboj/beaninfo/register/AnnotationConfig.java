package cn.dumboj.beaninfo.register;

import cn.dumboj.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 通过注解注册Bean 元信息
 *  3种方式
 *
 *  Spring容器中的Bean不重复注册
 *
 *  {@link @Component}
 *  {@link @Import}
 *  {@link @Bean}
 * */

//3。通过@Import 方式注册
@Import(AnnotationConfig.Config.class)
public class AnnotationConfig {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册配置类
        context.register(AnnotationConfig.class);
        context.refresh();
        Map<String, User> beanUsers = context.getBeansOfType(User.class);
        Map<String, AnnotationConfig> configBeans = context.getBeansOfType(AnnotationConfig.class);
        System.out.println("所有User类型的Bean"+beanUsers);
        System.out.println("所有User类型的Bean"+configBeans);
//        显式关闭Spring 上下文
        context.close();
    }
    //2。通过@Component方式注册
    @Component
    public static class Config{
        //1.@Bean 方式注册
        @Bean
        public User getUser() {
            User user = new User();
            user.setId(11L);
            user.setName("QIAN");
            return user;
        }
    }

}
