package cn.dumboj.beaninfo.instantiation.factory;

import cn.dumboj.beaninfo.bean.Person;
import org.springframework.beans.factory.FactoryBean;
/**
 * 通过 {@link FactoryBean} 的方式实例化Bean xml配置时不需要指定factory-bean/factory-method
 * */
public class PersonFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return Person.creatPerson();
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }
}
