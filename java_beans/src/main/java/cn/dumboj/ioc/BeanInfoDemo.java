package cn.dumboj.ioc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;
/**
 * Java Bean 信息
 * @see java.beans.BeanInfo
 * 通过spring 的自省方式或者Java bean 属性
 * */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        //getBeanInfo(Class<?> beanClass, Class<?> stopClass)  stopClass去除父类属性干扰
        BeanInfo info = Introspector.getBeanInfo(Person.class, Object.class);
        PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
            //输出Person属性信息
            System.out.println(propertyDescriptor);
        });

    }
}
