package cn.dumboj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @see ResourceLoader
 *
 * 几种类型的 ResourceLoader 是否一致
 *
 * */
public class ResourceLoaderDemo implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;//方式一

    @Autowired
    private ResourceLoader resourceLoader2;//方式二

    @Resource
    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        System.out.println(resourceLoader == resourceLoader2);
        System.out.println(resourceLoader == applicationContext);
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ResourceLoaderDemo.class);
        context.refresh();
        context.close();
    }
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
