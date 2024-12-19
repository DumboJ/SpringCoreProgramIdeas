package cn.dumboj.beaninfo.register;

import cn.dumboj.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Map;
/**
 * bean定义注册
 * {@link BeanDefinitionRegistry}
 * */
public class API_Register {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //1.命名方式注册BeanDefinition
        registerUserBeanDefinition(context,"dufu");
        //2.非命名方式注册BeanDefinition
        registerBeanDefinition(context);

        //刷新应用上下文
        context.refresh();

        Map<String, User> beansOfType = context.getBeansOfType(User.class);

        //所有User类型的Bean{dufu=User{id=1, name='dufu'}, cn.dumboj.domain.User#0=User{id=1, name='dufu'}}
        System.out.println("所有User类型的Bean"+beansOfType);

        //关闭上下文
        context.close();
    }
    //命名方式注册bean
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        //构建BeanDefinition
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", 1L).addPropertyValue("name", "dufu");

        //注册bean
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        } else {
            //以Api BeanDefinitionReaderUtils.registerWithGeneratedName(AbstractBeanDefinition definition, BeanDefinitionRegistry registry)
            /**如果上下文有重复类型的Bean 将会通过DefaultBeanNameGeneric  生成
             * If a class name or parent name is not unique, "#1
             * 类似：cn.dumboj.domain.User#0=User{id=1, name='dufu'}
             * */
            BeanDefinitionReaderUtils.registerWithGeneratedName(builder.getBeanDefinition(), registry);
        }
    }
    //非命名的方式注册BeanDefinition
    public static void registerBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry,null);
    }
}
