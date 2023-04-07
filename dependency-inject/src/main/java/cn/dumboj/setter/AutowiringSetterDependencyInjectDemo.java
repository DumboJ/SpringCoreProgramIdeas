package cn.dumboj.setter;

import cn.dumboj.holder.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * xml 中配置 autowire 属性
 * */
public class AutowiringSetterDependencyInjectDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String path = "classpath:META-INF/autowiring-setter-dependency-inject-context.xml";
        reader.loadBeanDefinitions(path);
        //autowiring  byName 时，User{id=1, name='dumboj'}
        //autowiring byType 时，因为byType设置了primary  SuperUser{address='北京市'}User{id=1, name='dumboj'}
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean.getUser().toString());
    }
}
