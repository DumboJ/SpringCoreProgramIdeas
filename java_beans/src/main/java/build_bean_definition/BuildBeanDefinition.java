package build_bean_definition;

import bean.Person;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * 构建BeanDefinition 方式
 * {@link BeanDefinition}
 *
 * */
public class BuildBeanDefinition {
    public static void main(String[] args) {
        /**构建的BeanDefinition并非终态。可自定义修改*/

        //1.通过BeanDefinitioinBuilder构建
        buildWithBeanDefinitionBuilder();

        //2.通过AbstractBeanDefinition及其派生类构建
        buildWithAbstractBeanDefinition();
    }

    private static void buildWithAbstractBeanDefinition() {
        GenericBeanDefinition bean = new GenericBeanDefinition();
        //设置Bean类型
        bean.setBeanClass(Person.class);
        //通过MutablePropertyValues 设置属性
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        /*propertyValues.add("name", "dumboj");
        propertyValues.add("age", 12);*/
        //链式编程
        propertyValues.add("name", "dumboj")
                      .add("age", 12);
        bean.setPropertyValues(propertyValues);

    }

    private static void buildWithBeanDefinitionBuilder() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        /*builder.addPropertyValue("name", "qianjun");
        builder.addPropertyValue("age", 18);*/
        //链式编程
        builder.addPropertyValue("name", "qianjun").addPropertyValue("age", 18);
        BeanDefinition beanDefinition = builder.getBeanDefinition();
    }
}
