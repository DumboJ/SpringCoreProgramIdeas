package cn.dumboj.repository;

import cn.dumboj.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户仓库
 * */
public class UserRepository {
    private Collection<User> users;
    //todo 内建非bean对象（依赖）使用setXx（）注入
    private BeanFactory beanFactory;

    private ObjectFactory<User> userObjectFactory;
    private ObjectFactory<ApplicationContext> apxObjectFactory;
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    public ObjectFactory<User> getUserObjectFactory() {
        return userObjectFactory;
    }

    public void setUserObjectFactory(ObjectFactory<User> userObjectFactory) {
        this.userObjectFactory = userObjectFactory;
    }

    public ObjectFactory<ApplicationContext> getApxObjectFactory() {
        return apxObjectFactory;
    }

    public void setApxObjectFactory(ObjectFactory<ApplicationContext> apxObjectFactory) {
        this.apxObjectFactory = apxObjectFactory;
    }
}
