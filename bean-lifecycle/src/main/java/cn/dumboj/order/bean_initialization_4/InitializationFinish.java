package cn.dumboj.order.bean_initialization_4;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Spring bean 初始化完成后
 * 执行 {@link SmartInitializingSingleton#afterSingletonsInstantiated()}
 * 需要先回调 {@link DefaultListableBeanFactory} beanFactory.preInstantiateSingletons()
 *
 * 同样的
 * {@link GenericXmlApplicationContext#refresh()} 即  ApplicationContext 中在初始化完成ioc 容器后，会调用
 *  finishBeanFactoryInitialization(beanFactory);->beanFactory.preInstantiateSingletons(); 会有提前初始化
 * */
public class InitializationFinish {
}
