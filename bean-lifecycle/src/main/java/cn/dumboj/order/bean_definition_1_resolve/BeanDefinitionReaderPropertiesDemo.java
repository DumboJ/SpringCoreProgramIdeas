package cn.dumboj.order.bean_definition_1_resolve;

import cn.dumboj.bean.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;


/**
 * {@link org.springframework.beans.factory.support.BeanDefinitionReader}
 * 读取 properties 文件作为 bean
 *
 * 创建 BeanDefinition 的方式：
 * 1. 读取 xml 文件
 * 2. 读取 properties 文件
 * 3. 读取 groovy 文件
 * */
public class BeanDefinitionReaderPropertiesDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 作为 BeanDefinitionRegistry
        DefaultListableBeanFactory register = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(register);
        String filepath = "/META-INF/config.properties";

        //解决中文乱码
        Resource resource = new ClassPathResource(filepath);
        EncodedResource encodedSource = new EncodedResource(resource, "UTF-8");

        int beanNums = beanDefinitionReader.loadBeanDefinitions(encodedSource);
        System.out.println("bean nums : "+beanNums);


        //依赖查找
        User user = register.getBean("user", User.class);
        System.out.println(user);
    }
}