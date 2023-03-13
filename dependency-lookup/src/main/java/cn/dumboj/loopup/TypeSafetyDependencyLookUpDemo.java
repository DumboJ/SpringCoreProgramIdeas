package cn.dumboj.loopup;

import cn.dumboj.beaninfo.bean.Person;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全的依赖查找
 * singleton type look up delegate implementation
 *  context type                                            |  method |                 safety
 * {@link org.springframework.beans.factory.BeanFactory }    #getBan()                  unsafe
 * {@link org.springframework.beans.factory.ObjectFactory}   #getObject()               unsafe
 * {@link org.springframework.beans.factory.ObjectProvider}  #getIfAvailable()          safe
 *
 * collection type look up delegate implementation
 *
 * {@link org.springframework.beans.factory.ListableBeanFactory} #getBeansOfType()      safe
 * {@link org.springframework.beans.factory.ObjectProvider} #getIfAvailable()           safe
 *
 * Hierarchical type look up delegate implementation
 * 层次性依赖查找的安全性取决于其扩展的单一或集合类型的 BeanFactory 接口
 * 比如：ListableBeanFactory 扩展 BeanFactory ,BeanFactory 查找单一类型不安全，则对应的层次查找也不安全
 *                                           ListableBeanFactory 查找集合类型beansOfType是安全的，则对应的层次类型查找安全
 *
 *
 * {@link ConfigurableListableBeanFactory}
 * {@link org.springframework.beans.factory.support.DefaultListableBeanFactory}
 *
 * */
public class TypeSafetyDependencyLookUpDemo {
   private  static Logger logger = LoggerFactory.getLogger(TypeSafetyDependencyLookUpDemo.class);
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TypeSafetyDependencyLookUpDemo.class);
        context.refresh();

        //BeanFactory#getBean()
        wayOfBeanFactory(context);

        //ObjectFactory#getObject()
        wayOfObjectFactory(context);

        //ObjectProvider#getIfAvailable()
        wayOfObjectProvider(context);

        //ListableBeanFactory#getBeanOfType()
        wayOfCollListableBeanFactory(context);

        //ObjectProvider#Stream
        wayOfCollObjectProvider(context);
        context.close();
    }
    /**
     * {@link ObjectProvider}
     * */
    private static void wayOfCollObjectProvider(AnnotationConfigApplicationContext context) {
        ObjectProvider<Person> beanProvider = context.getBeanProvider(Person.class);
        printBeanException("ObjectProvider Stream look up Collection",()->beanProvider.forEach(System.out::println));
    }

    /**
     * {@link org.springframework.beans.factory.ListableBeanFactory}
     * */
    private static void wayOfCollListableBeanFactory(ListableBeanFactory context) {
        printBeanException("ListableBeanFactory",()->context.getBeansOfType(Person.class));
    }

    /**
     * {@link org.springframework.beans.factory.ObjectProvider}
     * */
    private static void wayOfObjectProvider(AnnotationConfigApplicationContext context) {
        ObjectProvider<Person> beanProvider = context.getBeanProvider(Person.class);
        printBeanException("ObjectProvider",beanProvider::getIfAvailable);
    }

    /**
     * {@link org.springframework.beans.factory.ObjectFactory}
     * */
    private static void wayOfObjectFactory(AnnotationConfigApplicationContext context) {
        //ObjectProvider implements ObjectFactory
        ObjectProvider<Person> objectFactory = context.getBeanProvider(Person.class);
        // method reference invoke
        printBeanException("ObjectFactory", objectFactory::getObject);
    }

    /**
     * {@link org.springframework.beans.factory.BeanFactory}
     * */
    private static void wayOfBeanFactory(AnnotationConfigApplicationContext context) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //lambda invoke
        printBeanException("BeanFactory ",()-> beanFactory.getBean(Person.class));
    }

    public static void printBeanException(String source ,Runnable runnable) {
        logger.error("the source is :"+source);
        try {
            runnable.run();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
