package cn.dumboj.container;

import cn.dumboj.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFactory } 作为Ioc容器的案例
 *
 * 说明：
 *      不使用ApplicationContext依然可以获取实例对象
 *  but  没有事件、AOP等高级特性
 * */
public class BeanFactoryAsIocContainerDemo {
    public static void main(String[] args) {
        //创建BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //xml bean定义的读取器
        /**
         * 解释：
         * new XmlBeanDefinitionReader(BeanDefinitionRegister register)
         * &&
         * DefaultListableBeanFactory implements BeanDefinitionRegister
         * */
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载读取配置
        String location = "META-INF/dependency-lookup-context.xml";
        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
        System.out.println("该xml配置中定义的Bean数量：" + beanDefinitionsCount);

        //依赖查找
        lookUpByType(beanFactory);
    }

    private static void lookUpByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println(beansOfType);
        }
    }
}
