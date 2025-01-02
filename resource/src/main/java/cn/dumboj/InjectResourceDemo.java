package cn.dumboj;

import cn.dumboj.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 依赖注入 Resource 对象
 * @see Resource
 * */
public class InjectResourceDemo {
    @Value("classpath:META-INF/config.properties")
    private Resource configResource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[]  configs;

    @Value("${user.dir}")
    private String currentDir;
    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(configResource));

        Stream.of(configs).map(ResourceUtils::getContent).forEach(System.out::println);

        System.out.println(currentDir);
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectResourceDemo.class);
        context.refresh();
        context.close();
    }
}