package cn.dumboj.dependency.injection;

import cn.dumboj.domain.User;
import cn.dumboj.repository.UserRepository;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * Spring 依赖注入 案例
 * */
public class DependencyInjectDemo {
    public static void main(String[] args) {
        //配置xml
        //启动上下文
        BeanFactory beanfactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-inject-context.xml");

        /**依赖来源1：自定义bean*/

        /**注入的bean有两种方式：
         * 1. 名称id名称注入
         *      <util:list>
         *          <ref name=""></ref>
         *      </util:list>
         * 2.Auto-wiring方式注入 可以指定注入类型
         *      <bean id="" class="" autowire="byType"/>
         * */
        UserRepository userRepository =  beanfactory.getBean("userRepository",UserRepository.class);
        System.out.println(userRepository.getUsers());

        /**依赖来源2：依赖注入（内建依赖）*/

        //自定义bean中的beanFactory是否与上下文的beanFactory是同一对象
        // todo false
        System.out.println(beanfactory == userRepository.getBeanFactory());

        //org.springframework.beans.factory.support.DefaultListableBeanFactory@22f71333:
        // defining beans [user,superUser,objectFactory,userRepository];
        // root of factory hierarchy
        System.out.println(userRepository.getBeanFactory());

        //todo 内建依赖，并不能通过依赖查找的方式获取到  No qualifying bean of type 'org.springframework.beans.factory.BeanFactory' available
        try {
            //错误
            System.out.println(beanfactory.getBean(BeanFactory.class));
        } catch (BeansException e) {
            System.out.println(e.getMessage());
        }

        /**注入ObjectFactory<user>*/
        ObjectFactory<User> objectFactory = userRepository.getUserObjectFactory();
        // SuperUser{address='北京市'}User{id=1, name='dumboj'}  因为SuperUser设置了 primary属性为true
        System.out.println(objectFactory.getObject());

        /**注入ObjectFactory<ApplicationContext>*/
        ObjectFactory<ApplicationContext> apxObjectFactory = userRepository.getApxObjectFactory();
        ApplicationContext applicationContext = apxObjectFactory.getObject();
        //todo true  当内建非bean对象是ApplicationContext时，为啥与BeanFactory对象是同一对象  说明依赖注入的来源？？
        System.out.println(beanfactory == applicationContext);


        /**依赖来源3：容器内建Bean*/
        Environment environment = beanfactory.getBean(Environment.class);
        System.out.println("获取容器内建Bean Environment类型的Bean：" + environment);
    }
    
}
