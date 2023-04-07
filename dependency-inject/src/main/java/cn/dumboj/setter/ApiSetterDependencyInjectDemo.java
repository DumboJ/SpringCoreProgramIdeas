package cn.dumboj.setter;

import cn.dumboj.domain.SuperUser;
import cn.dumboj.holder.UserHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * API 方式setter注入
 * {@link BeanDefinitionBuilder.addPropertyReference()}
 * */
public class ApiSetterDependencyInjectDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBeanDefinition("userHolder", createUsrBeanDefinition());

        /**
         * 意在加载lookUp的context ,初始化对应的bean定义，
         * 也可以同 {@link AnnotationSetterDependencyInjectDemo} 中一样手动初始化对应的bean
         * */

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions("classpath:META-INF/dependency-lookup-context.xml");

        context.refresh();
        UserHolder bean = context.getBean(UserHolder.class);
        System.out.println(bean.getUser());
        context.close();
    }
    private static BeanDefinition createUsrBeanDefinition() {
        BeanDefinitionBuilder userBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        //API 的方式实现xml ref = ""
        userBeanDefinitionBuilder.addPropertyReference("user", "superUser");
        return userBeanDefinitionBuilder.getBeanDefinition();
    }
}
