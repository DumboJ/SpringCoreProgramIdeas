package cn.dumboj.setter;

import cn.dumboj.holder.UserHolder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class XmlSetterDependencyInjectDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String path = "classpath:META-INF/setter-dependency-inject-context.xml";
        reader.loadBeanDefinitions(path);
        UserHolder bean = beanFactory.getBean(UserHolder.class);
        System.out.println(bean.getUser().toString());
    }
}
