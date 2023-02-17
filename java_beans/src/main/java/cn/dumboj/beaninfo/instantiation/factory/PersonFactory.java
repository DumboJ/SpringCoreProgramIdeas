package cn.dumboj.beaninfo.instantiation.factory;


import cn.dumboj.beaninfo.bean.Person;
/**
 * 利用工厂方法调用 静态工厂方法的，目的在于复用创建的bean
 * */
public interface PersonFactory {
    default Person factoryCreatePerson() {
        return Person.creatPerson();
    }
}
