package cn.dumboj.ioc;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.stream.Stream;
/**
 *
 * */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(Person.class, Object.class);
        PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
            System.out.println(propertyDescriptor);
        });

    }
}
