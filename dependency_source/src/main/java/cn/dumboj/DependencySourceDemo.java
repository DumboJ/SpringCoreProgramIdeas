package cn.dumboj;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import javax.annotation.PostConstruct;

/**
 * 依赖注入＆依赖查找的来源问题解析
 *
 * 1、BeanDefinition -->支持依赖查找和依赖注入  ->具有生命周期管理，查找到后会生成bean，然后进行生命周期管理
 *                      {@link org.springframework.beans.factory.support.BeanDefinitionRegistry}注册
 * 2、singleton bean 单例bean --> 支持依赖查找和依赖注入->没有生命周期的管理，查找到后直接返回
 *                      {@link DefaultSingletonBeanRegistry} 注册
 * 3、resolveDependency 非容器管理的bean对象-->仅支持依赖注入
 *                      {@link ConfigurableListableBeanFactory}
 * 4、@Value  外部化配置的注入
 * */
public class DependencySourceDemo {
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    //查看是否注入  注入在 postProcessProperties 时候完成，早于@PostConstruct 在初始化前完成所以能注入成功
    public void init() {
        System.out.println("beanFactory == applicationContext " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory() " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("beanFactory == applicationContext.getBeanFactory() " + (resourceLoader == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory() " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    //依赖查找 BeanFactory /ApplicationContext
    // ResourceLoader /ApplicationEventPublisher
    // 都属于resolveDependency 的bean只能用于依赖注入，不能用于依赖查找
    //也没有生命周期的管理
    public void lookup() {
       getBean(BeanFactory.class);
       getBean(ApplicationContext.class);
       getBean(ResourceLoader.class);
       getBean(ApplicationEventPublisher.class);
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DependencySourceDemo.class);
        context.refresh();
        context.close();
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + "无法在BeanFactory中查找");
        }
        return null;
    }
}