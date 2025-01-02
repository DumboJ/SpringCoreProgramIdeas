package cn.dumboj;

import cn.dumboj.bean.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

/**
 * BeanDefinition 的属性传递
 * {@link org.springframework.beans.BeanMetadataAttributeAccessor}
 * */
public class BeanDefinitionAttrAndMdDataDemo {
    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","origin name");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //设置属性
        beanDefinition.setAttribute("attr","test-attr");

        //设置元数据
        beanDefinition.setSource(BeanDefinitionAttrAndMdDataDemo.class);

        DefaultListableBeanFactory beanFactory = getListableBeanFactory();
        //注册bean
        beanFactory.registerBeanDefinition("user",beanDefinition);

        //查找bean
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

    private static DefaultListableBeanFactory getListableBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new BeanPostProcessor(){
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean.getClass().equals(User.class) && ObjectUtils.nullSafeEquals("user", beanName)) {
                    //取出属性
                    BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                    //取出元数据
                    if (bd.getSource().equals(BeanDefinitionAttrAndMdDataDemo.class)) {
                        String attr = (String) bd.getAttribute("attr");
                        User user = (User) bean;
                        user.setName(attr);
                    }
                }
                return bean;
            }
        });
        return beanFactory;
    }
}
