package cn.dumboj.loopup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过 {@link ObjectProvider} 依赖查找
 * */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectProviderDemo.class);
        context.refresh();
        //通过ObjectProvider查找
        lookUpByObjectProvider(context);
        context.close();
    }

    private static void lookUpByObjectProvider(AnnotationConfigApplicationContext context) {
        ObjectProvider<String> beanProvider = context.getBeanProvider(String.class);
        String providerTest = beanProvider.getObject();
        System.out.println(providerTest);
    }
    @Bean
    public String providerTest() {//@Bean不指定bean name ，则以方法名作为bean name
        return "this is the singleton dependency look up with BeanProvider ";
    }
}
