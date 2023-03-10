package cn.dumboj.beaninfo.instantiation.init;

import cn.dumboj.beaninfo.instantiation.factory.PersonFactory;
import cn.dumboj.beaninfo.instantiation.factory.PersonFactoryImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * todo
 *  Spring初始化Bean的方式（按存在于同一bean中执行顺序排列）
 * 1.@PostConstruct 注解
 * 2.实现InitializingBean 接口中 afterPropertiesSet()方法
 * 3.自定义初始化方法
 *                 a:<bean init-method=""/>
 *                 b:@Bean(intiMethod = "")
 *                 c:JavaAPI AbstractBeanDefinition#setInitMethodName("")
 *
 *  todo --------------------------------------------------------------------------------
 *   Spring销毁Bean的方式（按存在于同一bean中执行顺序排列）
 *  * 1.@PreDestory 注解
 *  * 2.实现DisposableBean 接口中 destroy()方法
 *  * 3.自定义初始化方法
 *  *                 a:<bean destroy-method=""/>
 *  *                 b:@Bean(destroyMethod = "")
 *  *                 c:JavaAPI AbstractBeanDefinition#setDestroyMethodName("")
 * */
@Configuration
public class InitializationBean {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //注册 Configuration class
        context.register(InitializationBean.class);
        //刷新 Spring 应用上下文
        context.refresh();
        //初始化发生阶段
        PersonFactory bean = context.getBean(PersonFactory.class);
        //关闭 应用上下文
        context.close();
    }
    @Bean(initMethod = "definedSelfMethodInitialization",destroyMethod = "doDestory")
    //延时加载
    @Lazy
    public PersonFactory initPersonFactory() {
        return new PersonFactoryImpl();
    }
}
