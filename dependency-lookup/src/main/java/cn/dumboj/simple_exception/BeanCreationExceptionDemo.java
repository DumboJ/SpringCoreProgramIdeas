package cn.dumboj.simple_exception;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link org.springframework.beans.factory.BeanCreationException}
 * */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册pojo,回调时产生异常
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Pojo.class);
        //注册bean
        context.registerBeanDefinition("pojo", builder.getBeanDefinition());

        context.refresh();
        context.close();
    }

    static class Pojo implements InitializingBean {
        /**
         * cause Exception:
         *          Invocation of init method failed; nested exception is java.lang.Throwable:
         * */
        @PostConstruct
        public void init() throws Throwable {
            throw new Throwable("this is the first error of bean Initializing Exception");
        }
        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("this time is the second initializing Exception");
        }
    }
}
