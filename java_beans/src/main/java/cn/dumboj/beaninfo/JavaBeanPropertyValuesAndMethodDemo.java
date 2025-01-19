package cn.dumboj.beaninfo;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

import cn.dumboj.beaninfo.bean.Person;

/**
 * Spring 类型转换的基础 ：通过内省的方式能获取到 普通 Java Bean 的属性值和方法信息
 * 关联的内容包含 Spring BeanInfo 和 PropertyValue 和 PropertyEditor 和 BeanInfoDescriptor 等等信息
 * */
public class JavaBeanPropertyValuesAndMethodDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

        //获取属性描述
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            propertyDescriptor.getReadMethod();//Person 的 getXxx() 方法
            propertyDescriptor.getWriteMethod();//Person 的 setXxx() 方法
            System.out.println(propertyDescriptor);//Person中的所有属性信息及定义类型
        });

        //获取Bean 中定义的所有方法
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }
}
