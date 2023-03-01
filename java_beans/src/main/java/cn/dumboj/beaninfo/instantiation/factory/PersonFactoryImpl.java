package cn.dumboj.beaninfo.instantiation.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class PersonFactoryImpl implements PersonFactory, InitializingBean {
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
}
