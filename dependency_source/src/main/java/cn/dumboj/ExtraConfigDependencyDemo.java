package cn.dumboj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * 外部化配置类型的依赖注入demo
 * {@link org.springframework.core.env.PropertySource}
 * */
@Configuration//必须作为spring的Configuration,才能扫描加载到外部化配置的信息
@PropertySource(value = "classpath:/META-INF/config.properties", encoding = "UTF-8")
public class ExtraConfigDependencyDemo {
    @Value("${usr.name:'root'}")
    private String name;
    @Value("${usr.id:-1}")
    private int id;
    @Value("${usr.location:'北京'}")
    private String location;
    @PostConstruct
    public void init() {
        System.out.println("name = " + name);
        System.out.println("id = " + id);
        System.out.println("location = " + location);
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ExtraConfigDependencyDemo.class);
        context.refresh();
        context.close();
    }
}
