package cn.dumboj.beaninfo.instantiation.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PersonFactoryImpl implements PersonFactory, InitializingBean, DisposableBean {
    /**
     * 1.@PostContruct方式  初始化bean
     * */
    @PostConstruct
    public void initPostConstruct() {
        System.out.println("@PostConstruct init : 初始化中...");
    }
    /**
     * 2.InitializingBean#afterPropertiesSet() 初始化bean
     * */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(" InitializingBean#afterPropertiesSet(): 初始化中...");
    }
    /**
     * 3.自定义初始化方法的方式
     * */
    public void definedSelfMethodInitialization() {
        System.out.println(" 自定义初始化方法的方式 初始化中...");
    }
    /**
     * 1. @PreDestory  销毁Bean
     *
     * */
    @PreDestroy
    public void preDestoryBean() {
        System.out.println("@PreDestroy 销毁bean : 销毁中...");
    }
    /**
     * 2.DisposableBean#destroy() 销毁bean
     * */
    @Override
    public void destroy() throws Exception {
        System.out.println(" DisposableBean#destroy(): 销毁中...");
    }
    /**
     * 3.自定义销毁方法的方式
     * */
    public void doDestory() {
        System.out.println(" 自定义销毁方法的方式 初始化中...");
    }

    /**
     * Spring bean GC 回调
     * */
    @Override
    public void finalize() throws Throwable {
        System.out.println("当前PersonFactoryImpl 对象正在被垃圾回收... ...");
    }
}
