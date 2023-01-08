package cn.dumboj.beaninfo;

import java.beans.*;
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
        Stream.of(info.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            //输出Person属性信息
            System.out.println(propertyDescriptor);

            //PropertyDescriptors 允许添加属性编辑器   PropertyEditor
            //GUI --> text(String) --> String
            //name --> String
            //age --> Integer
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            String propertyrName = propertyDescriptor.getName();

            if ("age".equals(propertyrName)) {//为age 字段属性增加 PropertyDeitor，把所有age的字段类型都设置为Integer
                //调用PropertyEditor转换值
                propertyDescriptor.setPropertyEditorClass(ParseTextToInteger.class);

                //为Person设置创建已经设置转换的PropertyEditor支持
                propertyDescriptor.createPropertyEditor(Person.class);
            }
        });

    }
   /**
    * 统一的PropertyEditor转换方法
    * */
    static class ParseTextToInteger extends PropertyEditorSupport {
        //重写方法
        public void setAsText(String text){
            //先转换
            Integer value = Integer.valueOf(text);
            //设置值
            setValue(value);
        }
    }
}
