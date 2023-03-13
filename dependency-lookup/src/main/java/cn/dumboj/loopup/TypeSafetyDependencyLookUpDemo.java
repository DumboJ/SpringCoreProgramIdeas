package cn.dumboj.loopup;

import cn.dumboj.beaninfo.bean.Person;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全的依赖查找
 *  context type                                            |  method |      safety
 * {@link org.springframework.beans.factory.BeanFactory }    #getBan()          unsafe
 * {@link org.springframework.beans.factory.ObjectFactory}   #getObject()       unsafe
 * {@link org.springframework.beans.factory.ObjectProvider}  #getIfAvailable    safe
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

        context.close();
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
