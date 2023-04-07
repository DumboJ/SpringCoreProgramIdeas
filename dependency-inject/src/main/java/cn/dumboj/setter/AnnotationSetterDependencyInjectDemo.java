package cn.dumboj.setter;

import cn.dumboj.domain.SuperUser;
import cn.dumboj.domain.User;
import cn.dumboj.holder.UserHolder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationSetterDependencyInjectDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

       /* //如果不绑定，注解方式的User不会像xml 中那样初始化 ref的User，报错：UserHolder中的User未初始化找不到
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        //使用ioc-overview 中的xml,否则使用本模块中inject 的xml 声明了两个UserHolder
        reader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");*/

        context.register(AnnotationSetterDependencyInjectDemo.class);
        context.refresh();
        UserHolder bean = context.getBean(UserHolder.class);
        System.out.println(bean.getUser());
        context.close();
    }
    @Bean
    public UserHolder userHolder(User user) {
        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(3L);
        user.setName("dependency inject");
        return user;
    }
}
