package cn.dumboj.order.bean_i_destroy_5;

import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DisposableBeanAdapter;

/**
 * {@link DestructionAwareBeanPostProcessor#postProcessBeforeDestruction(Object, String)}
 *
 * # Bean 销毁前回调
 *
 * 执行顺序：@PostConstruct -> DisposableBean -> 自定义销毁
 *
 * {@link DisposableBeanAdapter#destroy()}
 * */
public class BeforeDestroy {
}
