package cn.dumboj.beaninfo.instantiation.init;

import cn.dumboj.beaninfo.instantiation.factory.PersonFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring bean GC调用
 * */
public class BeanGCdemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InitializationBean.class);
        context.refresh();
        //初始化/销毁阶段发生在依赖查找
        context.getBean(PersonFactory.class);
        context.close();
        System.gc();
        //GC 时会调用Spring bean 的 finalize()方法执行------todo 不一定回调成功
    }
}
