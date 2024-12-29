package cn.dumboj;

import cn.dumboj.bean.ScopeObj;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;
import java.util.Set;

/**
 * 讨论 Spring Bean的作用域 singleton 和 prototype
 * */
public class BeanScopeDemo implements DisposableBean {
    @Bean
    @Scope//默认单例
    private static ScopeObj singletonBean() {
        return createUser();
    }
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//每次请求都创建新对象
    private static ScopeObj prototypeBean() {
        return createUser();
    }

    //singleton 依赖注入
    @Autowired
    @Qualifier("singletonBean")
    private ScopeObj singleton;

    @Autowired
    @Qualifier("singletonBean")
    private ScopeObj singleton1;

    //prototype 依赖注入
    @Autowired
    @Qualifier("prototypeBean")
    private ScopeObj prototype;

    @Autowired
    @Qualifier("prototypeBean")
    private ScopeObj prototype1;

    @Autowired
    @Qualifier("prototypeBean")
    private ScopeObj prototype2;

    //集合类型依赖注入
    @Autowired
    private Map<String, ScopeObj> scopes;

    //注入 ResolveDependency 的 bean --> BeanFactory
    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanScopeDemo.class);

        /**
         * {@link org.springframework.beans.factory.config.BeanPostProcessor}
         *  依赖注入前的初始化过程可以处理 prototype bean 初始化后销毁 但一般不这么做，初始化后 bean创建一般供外界使用
         * */
        /*context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    System.out.printf("%s ,beanName : %s 在初始化后回调 .... %n ",
                            bean.getClass().getName(),
                            beanName);
                    return bean;
                }
            });
        });*/
        context.refresh();

        //依赖查找时的现象
        dependencyLookup(context);

        //依赖注入时的现象
        dependencyInjection(context);
        context.close();
    }



    /**
     * 依赖查找多次,观察 singleton 和 prototype 对象id是否相同
     * 结论： 1. singleton 依赖查找时，每次都返回同一个对象，因为是单例对象
     *       2. prototype 依赖查找时，每次都返回新的对象，因为是多例对象
     * */
    private static void dependencyLookup(AnnotationConfigApplicationContext context) {
        for (int i = 0; i < 3; i++) {
            //singleton 依赖查找
            ScopeObj singleton = context.getBean("singletonBean", ScopeObj.class);
            System.out.println("singletonBean id:" + singleton.getId());

            //prototype 依赖查找
            ScopeObj prototypeBean = context.getBean("prototypeBean", ScopeObj.class);
            System.out.println("prototypeBean id:" + prototypeBean.getId());
        }
    }


    /**
     *   结论3. 依赖查找集合对象时，prototype会返回跟原先注入不一致的新id的bean
     * */
    private static void dependencyInjection(AnnotationConfigApplicationContext context) {
        BeanScopeDemo beanScopeDemo = context.getBean(BeanScopeDemo.class);
        System.out.println("singleton:" + beanScopeDemo.singleton);
        System.out.println("singleton1:" + beanScopeDemo.singleton1);

        System.out.println("prototype:" + beanScopeDemo.prototype);
        System.out.println("prototype1:" + beanScopeDemo.prototype1);
        System.out.println("prototype2:" + beanScopeDemo.prototype2);

        System.out.println("users: " + beanScopeDemo.scopes);
    }



    /**
     * 创建bean 并 返回，id区分是否单例对象
     * */
    private static ScopeObj createUser() {
        ScopeObj scopeObj = new ScopeObj();
        scopeObj.setId(System.nanoTime());
        return scopeObj;
    }
    /**
     * scope 是 singleton 的 bean 生命周期的销毁由spring ioc 管理（观察现象注释destroy()方法中代码 ）
     * prototype 的 bean 生命周期的销毁需要显式销毁
     *
     * 可以通过实现
     * {@link org.springframework.beans.factory.DisposableBean}
     * 显式在应用上下文中销毁
     * */
    @Override
    public void destroy() throws Exception {
       this.prototype.destroy();
       this.prototype1.destroy();
       this.prototype2.destroy();

       //集合类型的依赖注入，遍历如果是 prototype 才调用 ，singleton的对象由ioc管理生命周期，如果显示调用可能会报错
        Map<String, ScopeObj> scopes = this.scopes;
        scopes.forEach((key, value) -> {
            if (beanFactory.getBeanDefinition(key).getScope() == ConfigurableBeanFactory.SCOPE_PROTOTYPE) {
                value.destroy();
            }
        });
    }
}