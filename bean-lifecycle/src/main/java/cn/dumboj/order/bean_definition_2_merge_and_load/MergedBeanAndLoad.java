package cn.dumboj.order.bean_definition_2_merge_and_load;
/**
 *  BeanDefinition 定义后 ，层级性的 bean（例如有继承关系的bean）会通过
 *  1. {@link org.springframework.beans.factory.config.ConfigurableBeanFactory#getMergedBeanDefinition(String)}
 *  合并解析对应关系 ，并生成对应的 BeanDefinition
 *  （{@link org.springframework.beans.factory.support.GenericBeanDefinition}
 *                          |
 *                          v
 *   {@link org.springframework.beans.factory.support.RootBeanDefinition}）
 *
 *   2. BeanDefinition 的 类加载 ，解析出 Bean String class 字符串信息并 转换成 Class对象，使用Java 的 Classloader 加载
 * */
public class MergedBeanAndLoad {
    public static void main(String[] args) {

    }
}
