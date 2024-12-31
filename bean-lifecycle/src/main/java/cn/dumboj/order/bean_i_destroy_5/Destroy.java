package cn.dumboj.order.bean_i_destroy_5;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * {@link DefaultListableBeanFactory#destroyBean(Object)} ()} 只是销毁bean 针对 BeanFactory
 *
 * 之后就是 {@link DefaultListableBeanFactory#destroySingleton(String)} ()} 和  Java 垃圾回收
 * */
public class Destroy {
}
