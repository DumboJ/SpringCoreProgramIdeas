package cn.dumboj.bean;

import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Spring bean 作用域说明的bean
 * */
public class ScopeObj implements BeanNameAware {
    private long id;

    private String name;

    private transient String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ScopeObj{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    @PostConstruct
    public void init() {
        System.out.println("ScopeBean ["+beanName+"] init ...");
    }
    @PreDestroy
    public void destroy() {
        System.out.println("ScopeBean ["+beanName+"] destroy ...");
    }
}
