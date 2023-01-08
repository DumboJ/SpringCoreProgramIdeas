package cn.dumboj.dependency.lookup;

import cn.dumboj.annotation.Super;
import cn.dumboj.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Spring Ioc 依赖查找查找demo
 * */
public class DependencyLookUpDemo {
    public static void main(String[] args) {
        //配置xml文件
        //启动Spring上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-lookup-context.xml");
        /**通过id查找*/
        //1-实时查找
        lookUpRealTime(beanFactory);
        //2-延迟查找
        lookUpLazy(beanFactory);

        /**类型查找*/
        //1-单个对象
        lookUpByType(beanFactory);

        //2-查找集合对象
        lookUpCollectionObj(beanFactory);

        /**注解查找*/
        lookUpByAnnotation(beanFactory);
    }
    /**
     * 注解查找
     * 只需在对应类上标注注解
     * 通过ListableBeanFactory.getBeansWithAnnotation(Annotation.class)
     * */
    private static void lookUpByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            Map<String, User> beansWithAnnotation = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("获取所有标注@Super 的对象："+beansWithAnnotation);
        }
    }

    /**
     * 按照类型查找集合对象集合
     * getBeansOfType(目标类型.class)
     * */
    private static void lookUpCollectionObj(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到所有的User对象集合："+beansOfType);
        }
    }

    /**
     * 类型查找 单个对象
     * @see <T> T getBean(Class<T> var1) throws BeansException;
     *
     * 当有多个User对象时，按照类型查找可能查找出多个对象，抛出异常
     * NoUniqueBeanDefinitionException:
     * No qualifying bean of type 'cn.dumboj.domain.User' available: expected single matching bean but found 2: user,superUser
     *
     * resolve:
     *          1:设置声明的同类型多个bean，主类实体
     *          2.使用ListableBeanFacorty处理
     * */
    private static void lookUpByType(BeanFactory beanFactory) {
        User bean = beanFactory.getBean(User.class);
        System.out.println("类型查找："+bean);
    }

    /**
     * id查找 -- 延迟查找
     * 指定bean名称，通过ObjectFactoryCreatingFactoryBean 延时查找获取上下文已有的bean，泛型不再需要类型转换
     * */
    private static void lookUpLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延时加载："+user);
    }

    /**
     * id查找 -- 实时查找
     * 通过BeanFactory
     * */
    private static void lookUpRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找："+user);
    }
}
